package Banck_Pichincha.bancoPichincha.repository;

import Banck_Pichincha.bancoPichincha.model.entity.CuentaEntity;
import Banck_Pichincha.bancoPichincha.model.entity.TipoMovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TipoMovimientoRepository extends JpaRepository<TipoMovimientoEntity, Integer> {


    @Query(value = "SELECT * FROM TipoMovimiento WHERE estado = 'TRUE'", nativeQuery = true)
    List<TipoMovimientoEntity> getAllTipoMovimiento();
}
