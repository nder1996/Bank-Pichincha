package Banck_Pichincha.bancoPichincha.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorDetailResponse {


    private  Integer codeHttp;
    private  String codeName;
    private  String codeDescripcion;

    public static final List<ErrorDetailResponse> ERRORS = Arrays.asList(

            new ErrorDetailResponse(400, "CLIENT_CREATION_FAILED", "Hubo un error al crear el cliente. Por favor, verifica los datos proporcionados e inténtalo de nuevo."),
            new ErrorDetailResponse(400, "NO_DATA_AVAILABLE", "No se encontraron registros que coincidan con los datos proporcionados."),
            new ErrorDetailResponse(400, "UPDATE_RECORD_ERROR", "Hubo un error al intentar actualizar la información solicitada"),
            new ErrorDetailResponse(500, "INTERNAL_SERVER_ERROR", "Ocurrió un error en el servidor"),
            new ErrorDetailResponse(400, "INSUFFICIENT_FUNDS_DEBIT", "El saldo en tu cuenta de débito es cero o insuficiente para realizar la transacción."),
            new ErrorDetailResponse(400, "DAILY_LIMIT_EXCEEDED_DEBIT", "La transacción supera el límite diario de 1000 para tu cuenta de débito."),
            new ErrorDetailResponse(400, "INSUFFICIENT_FUNDS_DEBIT", "Saldo insuficiente en la cuenta de ahorros para completar la transacción."),
            new ErrorDetailResponse(400, "ACCOUNT_CREATION_FAILED", "Hubo un error al intentar crear la cuenta. Por favor, verifica los datos e intenta nuevamente."),
            new ErrorDetailResponse(400, "BANK_TRANSACTION_CREATION_FAILED", "No se pudo crear el movimiento bancario. Inténtalo nuevamente más tarde."),
            new ErrorDetailResponse(400, "CLIENT_DELETION_FAILED", "No se pudo borrar el cliente. Por favor, verifica los datos e inténtalo de nuevo."),
            new ErrorDetailResponse(400, "BANK_TRANSACTION_DELETION_FAILED", "No se pudo borrar el movimiento bancario. Por favor, verifica los datos e inténtalo de nuevo."),
            new ErrorDetailResponse(400, "ACCOUNT_DELETION_FAILED", "No se pudo borrar la cuenta. Por favor, verifica los datos e inténtalo de nuevo."),
            new ErrorDetailResponse(404, "USER_NOT_FOUND", "El usuario no existe. Por favor, verifica el identificador proporcionado e inténtalo de nuevo."),
            new ErrorDetailResponse(404, "NO_DATA_FOUND", "No se encontraron datos para la solicitud realizada. Por favor, verifica los parámetros e inténtalo de nuevo."),
            new ErrorDetailResponse(400, "ACCOUNT_SEARCH_FAILED", "Hubo un error al buscar la cuenta. Por favor, verifica los datos proporcionados e inténtalo de nuevo.")



    );
}
