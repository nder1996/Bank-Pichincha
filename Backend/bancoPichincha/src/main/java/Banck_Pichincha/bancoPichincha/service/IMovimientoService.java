package Banck_Pichincha.bancoPichincha.service;

import Banck_Pichincha.bancoPichincha.model.entity.ClienteEntity;
import Banck_Pichincha.bancoPichincha.model.entity.MovimientosEntity;
import Banck_Pichincha.bancoPichincha.model.response.ApiResponse;

public interface IMovimientoService {

    public ApiResponse<String> save(MovimientosEntity cliente );

    public ApiResponse<String> update(MovimientosEntity cliente);

    public ApiResponse<String> getByMovimiento(Integer idMovimiento);

    public ApiResponse<String> getAllMovimientos();

    public ApiResponse<String> delete(Integer idMovimiento);
}
