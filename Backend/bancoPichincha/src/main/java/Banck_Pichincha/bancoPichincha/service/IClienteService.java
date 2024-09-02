package Banck_Pichincha.bancoPichincha.service;

import Banck_Pichincha.bancoPichincha.model.dto.ClienteDto;
import Banck_Pichincha.bancoPichincha.model.entity.ClienteEntity;
import Banck_Pichincha.bancoPichincha.model.response.ApiResponse;

public interface IClienteService {

    public ApiResponse<String> save( ClienteDto cliente );

    public ApiResponse<String> update(ClienteDto cliente);

    public ApiResponse<String> getByCliente(Integer idCliente);

    public ApiResponse<String> getAllCliente();

    public ApiResponse<String> delete(Integer idCliente);

}
