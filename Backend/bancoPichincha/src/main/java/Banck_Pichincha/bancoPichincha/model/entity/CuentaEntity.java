package Banck_Pichincha.bancoPichincha.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "Cuenta")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CuentaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCuenta")
    private Integer idCuenta;

    @Column(name = "numeroCuenta", nullable = false)
    private String numeroCuenta;


    @ManyToOne
    @JoinColumn(name = "idTipoCuenta", nullable = false)
    private TipoCuentaEntity tipoCuente;

    @ManyToOne
    @JoinColumn(name = "idCliente", nullable = false)
    private ClienteEntity cliente;


    @Column(name = "saldoInicial", nullable = false)
    private Double saldoInicial;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at",nullable = false)
    private Date create_at;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_at")
    private Date update_at;


}
