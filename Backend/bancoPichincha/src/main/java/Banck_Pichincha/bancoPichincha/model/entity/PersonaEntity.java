package Banck_Pichincha.bancoPichincha.model.entity;


/*import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;
import java.util.Date;



@Data
@Entity
@Table(name = "Persona")
*/


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;


@Entity
@Table(name = "Persona")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PersonaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idPersona")
    private Integer idPersona;

    @Column(name = "nombreCompleto",nullable = false)
    private String nombreCompleto;

    @Column(name = "fechaNacimiento",nullable = false)
    private Date fechaNacimiento;

    @Column(name = "direccion",nullable = false)
    private String direccion;

    @Column(name = "telefono",nullable = false)
    private String telefono;

    @ManyToOne
    @JoinColumn(name = "IdIdentificacion")
    private TipoIdentificacionEntity tipoIdentificacion;

    @ManyToOne
    @JoinColumn(name = "idGenero")
    private GenerosEntity genero;

    @Column(name = "numIdentificacion",nullable = false)
    private String numeIdentificacion;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at",nullable = false)
    private Date create_at;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_at")
    private Date update_at;


}
