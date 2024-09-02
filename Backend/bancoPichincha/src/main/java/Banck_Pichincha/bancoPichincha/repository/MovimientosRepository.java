package Banck_Pichincha.bancoPichincha.repository;

import Banck_Pichincha.bancoPichincha.model.entity.MovimientosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovimientosRepository extends JpaRepository<MovimientosEntity, Integer> {
}
