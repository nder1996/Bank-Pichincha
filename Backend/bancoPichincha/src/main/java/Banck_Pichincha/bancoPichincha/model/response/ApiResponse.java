package Banck_Pichincha.bancoPichincha.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiResponse<T>  {

    private Map<String,Object> data;  // Cambiado de Map<String, Object> a T
    private Meta meta;
    private ErrorDetails error;


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Meta {
        private String message;
        private int status;
    }



    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorDetails {
        private String code;
        private String details;
    }

}
