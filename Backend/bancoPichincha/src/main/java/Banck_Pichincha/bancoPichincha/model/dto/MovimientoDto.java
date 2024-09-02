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


    private Date fecha;


    private Integer IdTiposMovimientos;


    private Double valor;


    private Double saldo;


    private Date create_at;


    private Date update_at;

}
