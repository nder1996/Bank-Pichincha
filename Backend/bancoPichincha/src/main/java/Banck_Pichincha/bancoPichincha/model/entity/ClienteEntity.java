package Banck_Pichincha.bancoPichincha.model.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;

@Entity
@Table(name = "Cliente")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private Integer idCliente;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "estado", nullable = false)
    private String estado;


    @Temporal(TemporalType.DATE)
    @Column(name = "create_at", nullable = false)
    private Date createAt;

    @Temporal(TemporalType.DATE)
    @Column(name = "update_at")
    private Date updateAt;

    @ManyToOne
    @JoinColumn(name = "idPersona", nullable = false)
    private PersonaEntity persona;

}
