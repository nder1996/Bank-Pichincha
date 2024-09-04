package Banck_Pichincha.bancoPichincha.service;

import Banck_Pichincha.bancoPichincha.model.dto.MovimientoDto;
import Banck_Pichincha.bancoPichincha.model.entity.ClienteEntity;
import Banck_Pichincha.bancoPichincha.model.entity.CuentaEntity;
import Banck_Pichincha.bancoPichincha.model.entity.MovimientosEntity;
import Banck_Pichincha.bancoPichincha.model.entity.TipoMovimientoEntity;
import Banck_Pichincha.bancoPichincha.model.response.ApiResponse;
import Banck_Pichincha.bancoPichincha.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
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



    @Override
    public ApiResponse<String> save(MovimientoDto movimiento) {
        MovimientosEntity  movimientosEntity = new MovimientosEntity();
        try {
            MovimientosEntity  finbyMovimiento = movimientosRepository.getByIdMovimientos(movimientosEntity.getIdMovimientos());
            if(finbyMovimiento!=null && finbyMovimiento.getIdCuenta()!=null){
                movimientosEntity.setIdTipoMovimientos(finbyMovimiento.getIdTipoMovimientos());
                movimientosEntity.setIdCuenta(finbyMovimiento.getIdCuenta());
                movimientosEntity.setFecha(movimiento.getFecha());
                movimientosEntity.setValor(movimiento.getValor());
                movimientosEntity.setEstado(movimiento.getEstado());
                movimientosEntity.setSaldo(movimiento.getSaldo());
                movimientosEntity.setCreate_at(new Date());
                MovimientosEntity save = movimientosRepository.save(movimientosEntity);
                if(save!=null && save.getIdMovimientos()!=null){
                    return responseApiBuilder.successRespuesta("Movimiento Creado","Movimiento");
                }else{
                    return this.responseApiBuilder.errorRespuesta("MOVEMENT_NOT_CREATED");
                }
            }else{
                return this.responseApiBuilder.errorRespuesta("MOVEMENT_NOT_CREATED");
            }
        }catch (Exception e) {
            System.err.println("Error al guardar el cliente: " + e.getMessage());
            return this.responseApiBuilder.errorRespuesta("MOVEMENT_NOT_CREATED");
        }
    }

    @Override
    public ApiResponse<String> update(MovimientoDto movimiento) {
        try {
            Integer row =  this.movimientosRepository.updateMoviento(movimiento);
            if(row>0){
                String creacion = "El Movimiento del número de cuenta  "+movimiento.getCuenta() + " Fueron actualizados con éxito";
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
        MovimientoDto movimientoDto = new MovimientoDto();
        try {
            MovimientosEntity movimiento = this.movimientosRepository.getByIdMovimientos(idMovimiento);
            if(movimiento!=null && movimiento.getIdMovimientos()!=null){
                movimientoDto.setCuenta(movimiento.getIdCuenta().getNumeroCuenta());
                //movimientoDto.setIdTipoCuenta(movimiento.getIdCuenta().getTipoCuente().getId());
                movimientoDto.setTipoCuenta(movimiento.getIdCuenta().getTipoCuente().getNombre());
               // movimientoDto.setIdTiposMovimientos(movimiento.getIdTipoMovimientos().getIdTipoMovimientos());
                movimientoDto.setSaldo(movimiento.getSaldo());
                movimiento.setEstado(movimiento.getEstado());
                movimiento.setValor(movimiento.getValor());
                String msg_movimiento = "";
                DecimalFormat df = new DecimalFormat("#.##");
                String valorComoString = df.format(movimiento.getValor());
                if(movimiento.getIdTipoMovimientos().getNombre().equals("RETIRO")){
                    msg_movimiento = "RETIRO DE "+valorComoString ;
                    movimientoDto.setTipoMovimiento(msg_movimiento);
                }  if(movimiento.getIdTipoMovimientos().getNombre().equals("DEPÓSITO")){
                    msg_movimiento = "DEPÓSITO DE "+valorComoString ;
                    movimientoDto.setTipoMovimiento(msg_movimiento);
                }
                return this.responseApiBuilder.successRespuesta(movimientoDto,"MOVIMIENTO");
            }else{
                return this.responseApiBuilder.errorRespuesta("ACCOUNT_SEARCH_FAILED");

            }
        }catch (Exception e) {
            System.err.println("Error al buscar la cuenta: " + e.getMessage());
            return this.responseApiBuilder.errorRespuesta("ACCOUNT_SEARCH_FAILED");
        }
    }

    @Override
    public ApiResponse<String> getAllMovimientos() {
        try {
            List<MovimientosEntity> movimientos = this.movimientosRepository.getAllMovimientos();
            if (movimientos != null && !movimientos.isEmpty()) {
                List<MovimientoDto> movimientoList = movimientos.stream()
                        .map(movimiento -> {
                            MovimientoDto movimientoDto = new MovimientoDto();
                            if (movimiento.getIdMovimientos() != null) {
                                movimientoDto.setIdMovimientos(movimiento.getIdMovimientos());
                            }
                            if (movimiento.getIdCuenta() != null) {
                             //   movimientoDto.setIdCuenta(movimiento.getIdCuenta().getIdCuenta());
                                movimientoDto.setCuenta(movimiento.getIdCuenta().getNumeroCuenta());
                             //   movimientoDto.setIdTipoCuenta(movimiento.getIdCuenta().getTipoCuente().getId());
                                movimientoDto.setTipoCuenta(movimiento.getIdCuenta().getTipoCuente().getNombre());
                            }
                            if (movimiento.getIdTipoMovimientos() != null) {
                               /// movimientoDto.setIdTiposMovimientos(movimiento.getIdTipoMovimientos().getIdTipoMovimientos());
                            }
                            if (movimiento.getSaldo() != null) {
                                movimientoDto.setSaldo(movimiento.getSaldo());
                            }
                            if (movimiento.getValor() != null) {
                                movimientoDto.setValor(movimiento.getValor());
                            }
                            if (movimiento.getEstado() != null) {
                                movimientoDto.setEstado(movimiento.getEstado());
                            }
                            // Formatear el mensaje de movimiento
                            DecimalFormat df = new DecimalFormat("#.##");
                            String valorComoString = df.format(movimiento.getValor());
                            if (movimiento.getIdTipoMovimientos().getNombre().equals("RETIRO")) {
                                movimientoDto.setTipoMovimiento("RETIRO DE " + valorComoString);
                            } else if (movimiento.getIdTipoMovimientos().getNombre().equals("DEPÓSITO")) {
                                movimientoDto.setTipoMovimiento("DEPÓSITO DE " + valorComoString);
                            }

                            return movimientoDto;
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
}
