package Banck_Pichincha.bancoPichincha.model.dto;

import Banck_Pichincha.bancoPichincha.model.entity.TipoCuentaEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovimientoDto {


    private Integer idMovimientos;

    private String numCuenta;

    private Date fecha;

    private String idTipoMovimiento;

    private Double valor;

    private Double saldo;

    private String estado;

}
