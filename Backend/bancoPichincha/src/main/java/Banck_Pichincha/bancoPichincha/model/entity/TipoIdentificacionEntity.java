package Banck_Pichincha.bancoPichincha.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "TipoIdentificacion")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TipoIdentificacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "estado", nullable = false)
    private String estado;

    @Column(name = "descripcion", nullable = false)
    private String descripcion;

}
