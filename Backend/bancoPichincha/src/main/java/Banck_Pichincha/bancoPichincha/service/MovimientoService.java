package Banck_Pichincha.bancoPichincha.service;


import Banck_Pichincha.bancoPichincha.model.dto.MovimientoDto;
import Banck_Pichincha.bancoPichincha.model.entity.CuentaEntity;
import Banck_Pichincha.bancoPichincha.model.entity.MovimientosEntity;
import Banck_Pichincha.bancoPichincha.model.entity.TipoMovimientoEntity;
import Banck_Pichincha.bancoPichincha.model.response.ApiResponse;
import Banck_Pichincha.bancoPichincha.repository.CuentaRepository;
import Banck_Pichincha.bancoPichincha.repository.MovimientosRepository;
import Banck_Pichincha.bancoPichincha.repository.TipoMovimientoRepository;
import Banck_Pichincha.bancoPichincha.util.ValidationMovimientoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MovimientoService implements IMovimientoService{

    @Autowired
    MovimientosRepository movimientosRepository;


    @Autowired
    TipoMovimientoRepository tipoMovimientoRepository;

    @Autowired
    CuentaRepository cuentaRepository;

    @Autowired
    ResponseApiBuilder responseApiBuilder;

    @Autowired
    CuentaService cuentaService;


    private final ValidationMovimientoUtil movimientoUtil;

    @Autowired
    public MovimientoService(ValidationMovimientoUtil movimientoUtil) {
        this.movimientoUtil = movimientoUtil;
    }


    @Override
    public ApiResponse<String> save(MovimientoDto movimiento) {
        // Usando el validador para validar el movimiento
        if (!this.movimientoUtil.esMovimientoValido(movimiento)) {
            return responseApiBuilder.errorRespuesta("INVALID_MOVEMENT");
        }

        // Lógica adicional del servicio
        List<TipoMovimientoEntity> tiposMovimiento = tipoMovimientoRepository.getAllTipoMovimiento();
        CuentaEntity cuenta = cuentaRepository.getByIdCuentaxNumCuenta(movimiento.getNumCuenta());

        if (cuenta == null && cuenta.getIdCuenta()!=null) {
            return responseApiBuilder.errorRespuesta("INSUFFICIENT_FUNDS_DEBIT");
        }

                movimiento.setIdCuenta(cuenta.getIdCuenta());

        for (TipoMovimientoEntity tipoMovimiento : tiposMovimiento) {
            if (movimiento.getIdTipoMovimiento() == tipoMovimiento.getIdTipoMovimientos()) {
                if ("RETIRO".equals(tipoMovimiento.getNombre())) {
                    if (movimiento.getSaldo() < movimiento.getValor()) {
                        return responseApiBuilder.errorRespuesta("INSUFFICIENT_FUNDS_DEBIT");
                    }
                    Object saldoRetiradoHoy = this.movimientosHoyRetiro(cuenta.getNumeroCuenta()).getData().get("total_movimiento_hoy");
                    Double saldoRetiradoHoyDouble = (saldoRetiradoHoy instanceof Number)
                            ? ((Number) saldoRetiradoHoy).doubleValue()
                            : 0.0;
                    if(movimiento.getValor()<=1000 && saldoRetiradoHoyDouble<=1000){
                        movimiento.setSaldo(cuenta.getSaldoInicial()- movimiento.getValor());
                    }else{
                        return responseApiBuilder.errorRespuesta("DAILY_WITHDRAWAL_LIMIT_EXCEEDED");
                    }
                } else if ("DEPÓSITO".equals(tipoMovimiento.getNombre())) {
                    movimiento.setSaldo(cuenta.getSaldoInicial() + movimiento.getValor());
                } else {
                    return responseApiBuilder.errorRespuesta("INVALID_MOVEMENT_TYPE");
                }
                cuenta.setSaldoInicial(movimiento.getSaldo());
                CuentaEntity cuenta1 = this.cuentaRepository.save(cuenta);
                Integer saveResult = movimientosRepository.insert(movimiento);
                if (saveResult != null && saveResult > 0 && cuenta1.getIdCuenta() != null) {
                    return responseApiBuilder.successRespuesta("Movimiento Creado", "Movimiento");
                } else {
                    return responseApiBuilder.errorRespuesta("MOVEMENT_NOT_CREATED");
                }
            }
        }

        return responseApiBuilder.errorRespuesta("MOVEMENT_NOT_CREATED");
    }


    @Override
    public ApiResponse<String> update(MovimientoDto movimiento) {
        try {
            Integer row =  this.movimientosRepository.updateMoviento(movimiento);
            if(row>0){
                String creacion = "El Movimiento del número de cuenta  "+movimiento.getNumCuenta() + " Fueron actualizados con éxito";
                return this.responseApiBuilder.successRespuesta(creacion,"movimiento");
            }else{
                return this.responseApiBuilder.errorRespuesta("UPDATE_RECORD_ERROR");
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar los datos del movimiento: " + e.getMessage());
            return this.responseApiBuilder.errorRespuesta("UPDATE_RECORD_ERROR");
        }
    }

    @Override
    public ApiResponse<String> getByMovimiento(Integer idMovimiento) {
        Map<String, Object> movimientoMap = new HashMap<>();
        try {
            MovimientosEntity movimiento = this.movimientosRepository.getByIdMovimientos(idMovimiento);
            if (movimiento != null && movimiento.getIdMovimientos() != null) {
                movimientoMap.put("numCuenta", movimiento.getIdCuenta().getNumeroCuenta());
                movimientoMap.put("tipoCuenta", movimiento.getIdCuenta().getTipoCuente().getNombre());
                movimientoMap.put("saldo", movimiento.getSaldo());
                movimientoMap.put("estado", movimiento.getEstado());
                movimientoMap.put("valor", movimiento.getValor());
                String msgMovimiento = "";
                DecimalFormat df = new DecimalFormat("#.##");
                String valorComoString = df.format(movimiento.getValor());
                if (movimiento.getIdTipoMovimientos().getNombre().equals("RETIRO")) {
                    msgMovimiento = "RETIRO DE " + valorComoString;
                } else if (movimiento.getIdTipoMovimientos().getNombre().equals("DEPÓSITO")) {
                    msgMovimiento = "DEPÓSITO DE " + valorComoString;
                }
                movimientoMap.put("tipoMovimiento", msgMovimiento);
                return this.responseApiBuilder.successRespuesta(movimientoMap, "MOVIMIENTO");
            } else {
                return this.responseApiBuilder.errorRespuesta("ACCOUNT_SEARCH_FAILED");
            }
        } catch (Exception e) {
            System.err.println("Error al buscar la cuenta: " + e.getMessage());
            return this.responseApiBuilder.errorRespuesta("ACCOUNT_SEARCH_FAILED");
        }
    }

    @Override
    public ApiResponse<String> getAllMovimientos() {
        try {
            List<MovimientosEntity> movimientos = this.movimientosRepository.getAllMovimientos();
            if (movimientos != null && !movimientos.isEmpty()) {
                List<Map<String, Object>> movimientoList = movimientos.stream()
                        .map(movimiento -> {
                            Map<String, Object> movimientoMap = new HashMap<>();
                            if (movimiento.getIdMovimientos() != null) {
                                movimientoMap.put("idMovimientos", movimiento.getIdMovimientos());
                            }
                            if (movimiento.getIdCuenta() != null) {
                                movimientoMap.put("numCuenta", movimiento.getIdCuenta().getNumeroCuenta());
                                movimientoMap.put("tipoCuenta", movimiento.getIdCuenta().getTipoCuente().getNombre());
                            }
                            if (movimiento.getSaldo() != null) {
                                movimientoMap.put("saldo", movimiento.getSaldo());
                            }
                            if (movimiento.getValor() != null) {
                                movimientoMap.put("valor", movimiento.getValor());
                            }
                            if (movimiento.getEstado() != null) {
                                movimientoMap.put("estado", movimiento.getEstado());
                            }

                            // Formatear el valor
                            DecimalFormat df = new DecimalFormat("#.##");
                            String valorComoString = df.format(movimiento.getValor());

                            // Definir el tipo de movimiento
                            if (movimiento.getIdTipoMovimientos().getNombre().equals("RETIRO")) {
                                movimientoMap.put("tipoMovimiento", "RETIRO DE " + valorComoString);
                            } else if (movimiento.getIdTipoMovimientos().getNombre().equals("DEPÓSITO")) {
                                movimientoMap.put("tipoMovimiento", "DEPÓSITO DE " + valorComoString);
                            }

                            return movimientoMap;
                        })
                        .collect(Collectors.toList());

                return this.responseApiBuilder.successRespuesta(movimientoList, "Movimientos");
            } else {
                return this.responseApiBuilder.errorRespuesta("MOVEMENTS_NOT_FOUND");
            }
        } catch (Exception e) {
            System.err.println("Error al buscar las cuentas: " + e.getMessage());
            return this.responseApiBuilder.errorRespuesta("MOVEMENTS_NOT_FOUND");
        }
    }


    @Override
    public ApiResponse<String> delete(Integer idMovimiento) {
        try {
            Integer row =  this.movimientosRepository.deleteMovimiento(idMovimiento);
            if(row>0){
                //String nombreCompleto = this.clienteRepository.nombreCliente(cuenta.getIdCliente());
                String movimiento = "Los datos corresponden al movimiento "+idMovimiento + " Fueron eliminados con éxito";
                return this.responseApiBuilder.successRespuesta(movimiento,"movimiento");
            }else{
                return this.responseApiBuilder.errorRespuesta("MOVEMENT_NOT_DELETED");
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar los datos del nuemros de cuenta : " + e.getMessage());
            return this.responseApiBuilder.errorRespuesta("MOVEMENT_NOT_DELETED");
        }
    }

    @Override
    public ApiResponse<String> getAllTipoMovimiento() {
        try {
          List<TipoMovimientoEntity> tipoMovimiento = this.tipoMovimientoRepository.getAllTipoMovimiento();
          if(tipoMovimiento!=null && !tipoMovimiento.isEmpty()){
              return this.responseApiBuilder.successRespuesta(tipoMovimiento,"tipoMovimiento");
          }else{
              return this.responseApiBuilder.errorRespuesta("NO_DATA_AVAILABLE");
          }
        }catch (Exception e) {
            System.err.println("Error al buscar al tipo de cuenta: " + e.getMessage());
            return this.responseApiBuilder.errorRespuesta("NO_DATA_AVAILABLE");
        }
    }

    @Override
    public ApiResponse<String> getAllCuenta() {
        try {
            ApiResponse<String> cuenta = this.cuentaService.getAllCuenta();
            return cuenta;
        }catch (Exception e) {
            System.err.println("Error al buscar todas la cuentas: " + e.getMessage());
            return this.responseApiBuilder.errorRespuesta("NO_DATA_AVAILABLE");
        }
    }


    public ApiResponse<String> movimientosHoyRetiro(String numeroCuenta){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            String fecha_formateada = sdf.format(new Date());
            double SumaTotal = 0.0;
            CuentaEntity cuenta = this.cuentaRepository.getByIdCuentaxNumCuenta(numeroCuenta);
            if(cuenta!=null && cuenta.getIdCuenta()!=null){
                List<MovimientosEntity> movimientoHoy = this.movimientosRepository.getAllMovimientoXHoy(fecha_formateada,cuenta.getIdCuenta());
                if(movimientoHoy!=null && !movimientoHoy.isEmpty()){
                    for (MovimientosEntity entity : movimientoHoy){
                        if(entity!=null && entity.getIdMovimientos()!=null && entity.getValor()!=null){
                            SumaTotal = entity.getValor() + SumaTotal;
                        }
                    }
                }
            }
            return this.responseApiBuilder.successRespuesta(SumaTotal,"total_movimiento_hoy");
        }catch (Exception e) {
            System.err.println("Error al buscar todas la cuentas: " + e.getMessage());
            return this.responseApiBuilder.errorRespuesta("NO_DATA_AVAILABLE");
        }
    }




}
