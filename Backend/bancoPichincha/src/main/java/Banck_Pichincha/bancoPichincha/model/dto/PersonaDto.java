package Banck_Pichincha.bancoPichincha.model.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonaDto {


    private Integer idPersona;


    private String nombreCompleto;

    private Date fechaNacimiento;


    private String direccion;


    private String telefono;


    private Integer tipoIdentificacion;


    private Integer genero;

    private String numeIdentificacion;


    private Date create_at;


    private Date update_at;
}
