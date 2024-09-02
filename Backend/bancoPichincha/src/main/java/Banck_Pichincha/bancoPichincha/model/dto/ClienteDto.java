package Banck_Pichincha.bancoPichincha.model.dto;


import Banck_Pichincha.bancoPichincha.model.entity.PersonaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDto extends PersonaDto{


    private Integer idCliente;

    private String password;

    private String estado;

    private Date createAt;

    private Date updateAt;


}
