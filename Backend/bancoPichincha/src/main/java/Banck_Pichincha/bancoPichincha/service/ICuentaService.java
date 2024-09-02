package Banck_Pichincha.bancoPichincha.service;

import Banck_Pichincha.bancoPichincha.model.dto.CuentaDto;
import Banck_Pichincha.bancoPichincha.model.entity.ClienteEntity;
import Banck_Pichincha.bancoPichincha.model.entity.CuentaEntity;
import Banck_Pichincha.bancoPichincha.model.response.ApiResponse;

public interface ICuentaService {


    public ApiResponse<String> save(CuentaDto cuenta );

    public ApiResponse<String> update(CuentaDto cuenta);

    public ApiResponse<String> getByCuenta(Integer idCuenta);

    public ApiResponse<String> getAllCuenta();

    public ApiResponse<String> delete(Integer idCuenta);
}
