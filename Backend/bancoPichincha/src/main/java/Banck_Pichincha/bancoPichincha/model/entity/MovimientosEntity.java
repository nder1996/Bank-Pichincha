package Banck_Pichincha.bancoPichincha.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "Movimiento")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class MovimientosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMovimientos")
    private Integer idMovimientos;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha",nullable = false)
    private Date fecha;

    @ManyToOne
    @JoinColumn(name = "idTipoMovimientos", nullable = false)
    private TipoMovimientoEntity idTipoMovimientos;

    @Column(name = "valor",nullable = false)
    private Double valor;

    @Column(name = "saldo",nullable = false)
    private Double saldo;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at",nullable = false)
    private Date create_at;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_at")
    private Date update_at;

}
