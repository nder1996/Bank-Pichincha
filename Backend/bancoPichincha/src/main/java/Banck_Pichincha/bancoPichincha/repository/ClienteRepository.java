package Banck_Pichincha.bancoPichincha.repository;

import Banck_Pichincha.bancoPichincha.model.dto.ClienteDto;
import Banck_Pichincha.bancoPichincha.model.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {



    @Modifying
    @Transactional
    @Query(value = "UPDATE Cliente SET password = :#{#clienteDto.password}, estado = :#{#clienteDto.estado}, update_at = :#{#clienteDto.updateAt} WHERE idCliente = :#{#clienteDto.idCliente}", nativeQuery = true)
    Integer updateCliente(@Param("clienteDto") ClienteDto clienteDto);


    @Modifying
    @Transactional
    @Query(value = "UPDATE Cliente SET  estado = 'FALSE', update_at = NOW() WHERE idCliente = :idCliente", nativeQuery = true)
    Integer deleteCliente(@Param("idCliente") Integer idCliente);


    @Query(value = "SELECT * FROM Cliente WHERE idCliente = :idCliente AND estado = 'TRUE'", nativeQuery = true)
    ClienteEntity getByIdCliente(@Param("idCliente") Integer idCliente);



    @Query(value = "SELECT * FROM Cliente WHERE estado = 'TRUE'", nativeQuery = true)
    List<ClienteEntity> getAllCliente();

    @Query(value = "SELECT person.nombreCompleto FROM Cliente as cliente  " +
            "left join Persona as person on cliente.idPersona = person.idPersona " +
            "where idCliente = :idCliente", nativeQuery = true)
    String nombreCliente(@Param("idCliente") Integer idCliente);




}
