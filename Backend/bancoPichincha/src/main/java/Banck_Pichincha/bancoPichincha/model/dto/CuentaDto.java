package Banck_Pichincha.bancoPichincha.model.dto;


import Banck_Pichincha.bancoPichincha.model.entity.TipoCuentaEntity;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CuentaDto {

    private Integer idCuenta;

    private Integer idCliente;

    private String numeroCuenta;

    private Integer tipoCuenta;

    private Double saldoInicial;

    private String estado;

    private Date create_at;

    private Date update_at;

}
