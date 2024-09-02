package Banck_Pichincha.bancoPichincha.service;

import Banck_Pichincha.bancoPichincha.model.entity.ClienteEntity;
import Banck_Pichincha.bancoPichincha.model.entity.MovimientosEntity;
import Banck_Pichincha.bancoPichincha.model.response.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public class MovimientoService implements IMovimientoService{


    @Override
    public ApiResponse<String> save(MovimientosEntity cliente) {
        return null;
    }

    @Override
    public ApiResponse<String> update(MovimientosEntity cliente) {
        return null;
    }

    @Override
    public ApiResponse<String> getByMovimiento(Integer idMovimiento) {
        return null;
    }

    @Override
    public ApiResponse<String> getAllMovimientos() {
        return null;
    }

    @Override
    public ApiResponse delete(Integer idMovimiento) {
        return null;
    }
}
