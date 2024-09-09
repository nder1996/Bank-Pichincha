package Banck_Pichincha.bancoPichincha.service;

import Banck_Pichincha.bancoPichincha.model.dto.MovimientoDto;
import Banck_Pichincha.bancoPichincha.model.entity.ClienteEntity;
import Banck_Pichincha.bancoPichincha.model.entity.MovimientosEntity;
import Banck_Pichincha.bancoPichincha.model.response.ApiResponse;

import java.util.Date;
import java.util.Map;

public interface IMovimientoService {

    public ApiResponse<String> save(MovimientoDto movimiento );

    public ApiResponse<String> update(MovimientoDto movimiento);

    public ApiResponse<String> getByMovimiento(Integer idMovimiento);

    public ApiResponse<String> getAllMovimientos();

    public ApiResponse<String> delete(Integer idMovimiento);

    public ApiResponse<String> getAllTipoMovimiento();

    public ApiResponse<String> getAllCuenta();

    public ApiResponse<String> movimientosHoyRetiro(String numeroCuenta);

}
