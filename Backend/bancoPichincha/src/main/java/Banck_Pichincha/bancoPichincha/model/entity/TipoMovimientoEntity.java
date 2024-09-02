package Banck_Pichincha.bancoPichincha.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Entity
@Table(name = "TipoMovimiento")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TipoMovimientoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoMovimientos")
    private String idTipoMovimientos;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "estado", nullable = false)
    private String estado;
}
