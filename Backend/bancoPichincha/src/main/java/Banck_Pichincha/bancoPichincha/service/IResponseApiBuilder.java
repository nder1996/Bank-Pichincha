package Banck_Pichincha.bancoPichincha.service;

import Banck_Pichincha.bancoPichincha.model.response.ApiResponse;

import java.util.Map;

public interface IResponseApiBuilder {


    public ApiResponse<String> successRespuesta(Object data,String key);

    public ApiResponse<String> errorRespuesta(String code);


}
