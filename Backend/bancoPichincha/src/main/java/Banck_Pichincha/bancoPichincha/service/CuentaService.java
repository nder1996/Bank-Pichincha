package Banck_Pichincha.bancoPichincha.service;

import Banck_Pichincha.bancoPichincha.model.dto.CuentaDto;
import Banck_Pichincha.bancoPichincha.model.entity.ClienteEntity;
import Banck_Pichincha.bancoPichincha.model.entity.CuentaEntity;
import Banck_Pichincha.bancoPichincha.model.entity.TipoCuentaEntity;
import Banck_Pichincha.bancoPichincha.model.response.ApiResponse;
import Banck_Pichincha.bancoPichincha.repository.ClienteRepository;
import Banck_Pichincha.bancoPichincha.repository.CuentaRepository;
import Banck_Pichincha.bancoPichincha.repository.TipoCuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CuentaService implements ICuentaService{



    @Autowired
    CuentaRepository cuentaRepository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ResponseApiBuilder responseApiBuilder;

    @Autowired
    TipoCuentaRepository tipoCuentaRepository;



    @Override
    public ApiResponse<String> save(CuentaDto cuenta) {
        try {
            CuentaEntity cuentaEntity = new CuentaEntity();
            TipoCuentaEntity tipoCuenta =  tipoCuentaRepository.getReferenceById(cuenta.getTipoCuenta());
            ClienteEntity clienteEntity = clienteRepository.getByIdCliente(cuenta.getIdCliente());
            if(tipoCuenta!=null && tipoCuenta.getId()!=null && clienteEntity!=null && clienteEntity.getIdCliente()!=null){
                cuentaEntity.setCliente(clienteEntity);
                cuentaEntity.setNumeroCuenta(cuenta.getNumeroCuenta());
                cuentaEntity.setTipoCuente(tipoCuenta);
                cuentaEntity.setSaldoInicial(0.0);
                cuentaEntity.setEstado("TRUE");
                cuentaEntity.setCreate_at(new Date());
                // Guardar la entidad en la base de datos
                CuentaEntity saveCuenta  = cuentaRepository.save(cuentaEntity);
                if(saveCuenta!=null && saveCuenta.getIdCuenta()!=null){
                    return responseApiBuilder.successRespuesta("Cuenta creada con éxito.","Cuenta");
                }else{
                    return this.responseApiBuilder.errorRespuesta("CLIENT_CREATION_FAILED");
                }
            }else{
                return this.responseApiBuilder.errorRespuesta("CLIENT_CREATION_FAILED");
            }
        }catch (Exception e) {
            System.err.println("Error al guardar el cliente: " + e.getMessage());
            return this.responseApiBuilder.errorRespuesta("CLIENT_CREATION_FAILED");
        }
    }

    @Override
    public ApiResponse<String> update(CuentaDto cuenta) {
        try {
            Integer row =  this.cuentaRepository.updateCuenta(cuenta);
            if(row>0){
                String creacion = "Los datos corresponden al número de cuenta  "+cuenta.getNumeroCuenta() + " Fueron actualizados con éxito";
                return this.responseApiBuilder.successRespuesta(creacion,"cliente");
            }else{
                return this.responseApiBuilder.errorRespuesta("UPDATE_RECORD_ERROR");
            }
        } catch (Exception e) {
            System.err.println("Error al actualizar los datos del nuemros de cuenta : " + e.getMessage());
            return this.responseApiBuilder.errorRespuesta("UPDATE_RECORD_ERROR");
        }
    }

    @Override
    public ApiResponse<String> getByCuenta(Integer idCuenta) {
        Map<String,Object> cuentaMaps = new HashMap<>();
        try {
           CuentaEntity cuenta = this.cuentaRepository.getByIdCuenta(idCuenta);
            if(cuenta!=null && cuenta.getIdCuenta()!=null){
                cuentaMaps.put("NUMERO_CUENTE",cuenta.getNumeroCuenta());
                cuentaMaps.put("TIPO_CUENTA",cuenta.getTipoCuente().getNombre());
                cuentaMaps.put("SALDO_INICIAL",cuenta.getSaldoInicial());
                cuentaMaps.put("ESTADO",cuenta.getEstado());
                cuentaMaps.put("CLIENTE",cuenta.getCliente().getPersona().getNombreCompleto());
                return this.responseApiBuilder.successRespuesta(cuentaMaps,"cliente");
            }else{
                return this.responseApiBuilder.errorRespuesta("ACCOUNT_SEARCH_FAILED");

            }
        }catch (Exception e) {
            System.err.println("Error al buscar la cuenta: " + e.getMessage());
            return this.responseApiBuilder.errorRespuesta("ACCOUNT_SEARCH_FAILED");
        }
    }

    @Override
    public ApiResponse<String> getAllCuenta() {
        try {
            List<CuentaEntity> cuentas = this.cuentaRepository.getAllCuenta();

            if (cuentas != null && !cuentas.isEmpty()) {
                List<Map<String, Object>> cuentasList = cuentas.stream()
                        .map(cuenta -> {
                            Map<String, Object> cuentaMap = new HashMap<>();
                            if (cuenta.getNumeroCuenta() != null) {
                                cuentaMap.put("NUMERO_CUENTA", cuenta.getNumeroCuenta());
                            }
                            if (cuenta.getTipoCuente() != null && cuenta.getTipoCuente().getNombre() != null) {
                                cuentaMap.put("TIPO_CUENTA", cuenta.getTipoCuente().getNombre());
                            }
                            if (cuenta.getSaldoInicial() != null) {
                                cuentaMap.put("SALDO_INICIAL", cuenta.getSaldoInicial());
                            }
                            if (cuenta.getEstado() != null) {
                                cuentaMap.put("ESTADO", cuenta.getEstado());
                            }
                            if (cuenta.getCliente() != null && cuenta.getCliente().getPersona() != null && cuenta.getCliente().getPersona().getNombreCompleto() != null) {
                                cuentaMap.put("CLIENTE", cuenta.getCliente().getPersona().getNombreCompleto());
                            }
                            return cuentaMap;
                        })
                        .collect(Collectors.toList());

                return this.responseApiBuilder.successRespuesta(cuentasList, "cuentas");
            } else {
                return this.responseApiBuilder.errorRespuesta("NO_ACCOUNTS_FOUND");
            }
        } catch (Exception e) {
            System.err.println("Error al buscar las cuentas: " + e.getMessage());
            return this.responseApiBuilder.errorRespuesta("ACCOUNT_SEARCH_FAILED");
        }
    }


    @Override
    public ApiResponse<String> delete(Integer idCuenta) {
        try {
            Integer row =  this.cuentaRepository.deleteCuenta(idCuenta);
            if(row>0){
                //String nombreCompleto = this.clienteRepository.nombreCliente(cuenta.getIdCliente());
                String creacion = "Los datos corresponden al número de cuenta  "+idCuenta + " Fueron eliminados con éxito";
                return this.responseApiBuilder.successRespuesta(creacion,"cliente");
            }else{
                return this.responseApiBuilder.errorRespuesta("ACCOUNT_DELETION_FAILED");
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar los datos del nuemros de cuenta : " + e.getMessage());
            return this.responseApiBuilder.errorRespuesta("ACCOUNT_DELETION_FAILED");
        }
    }
}
