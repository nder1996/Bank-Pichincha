package Banck_Pichincha.bancoPichincha.repository;

import Banck_Pichincha.bancoPichincha.model.entity.TipoMovimientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMovimientoRepository extends JpaRepository<TipoMovimientoEntity, Integer> {
}
