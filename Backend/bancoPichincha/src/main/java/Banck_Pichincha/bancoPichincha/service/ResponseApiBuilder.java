package Banck_Pichincha.bancoPichincha.service;

import Banck_Pichincha.bancoPichincha.model.response.ApiResponse;
import Banck_Pichincha.bancoPichincha.model.response.ErrorDetailResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ResponseApiBuilder implements  IResponseApiBuilder{


    public List<ErrorDetailResponse> getErrorDetails() {
        return ErrorDetailResponse.ERRORS;
    }

    public ErrorDetailResponse byErrorDetailsListXCode(String codeName , List<ErrorDetailResponse> errorDetailsList){
        try {
            Optional<ErrorDetailResponse> optionalErrorDetail = errorDetailsList.stream()
                    .filter(errorDetail -> errorDetail.getCodeName().equals(codeName))
                    .findFirst();
            if (optionalErrorDetail.isPresent()) {
                ErrorDetailResponse errorDetail = optionalErrorDetail.get();
                return errorDetail;
            }
        } catch (Exception ex) {
            System.out.println("Error al crear la respuesta del servidor : "+ex.getMessage());
            return null;
        }
        return null;
    }



    @Override
    public ApiResponse<String> successRespuesta(Object data,String key) {
        ApiResponse<String>  response = new ApiResponse<>();
        JsonObject jsonObject = new JsonObject();
        try{
            response.setMeta(new ApiResponse.Meta("Operación Exitosa",200));
            response.setData(Collections.singletonMap(key, data));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return response;
    }

    @Override
    public ApiResponse<String> errorRespuesta(String code) {
        ApiResponse<String>  response = new ApiResponse<>();
        try{
            List<ErrorDetailResponse> errorDetailsList = getErrorDetails();
            ErrorDetailResponse errorDetail = this.byErrorDetailsListXCode(code,errorDetailsList);
            response.setData(null);
            response.setMeta(new ApiResponse.Meta("Solicitud Incorrecta",errorDetail.getCodeHttp()));
            response.setError(new ApiResponse.ErrorDetails(errorDetail.getCodeName(),errorDetail.getCodeDescripcion()));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return response;
    }
}
