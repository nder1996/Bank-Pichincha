package Banck_Pichincha.bancoPichincha.repository;

import Banck_Pichincha.bancoPichincha.model.entity.TipoCuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCuentaRepository extends JpaRepository<TipoCuentaEntity, Integer> {
}
