package Banck_Pichincha.bancoPichincha.repository;

import Banck_Pichincha.bancoPichincha.model.entity.TipoIdentificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoIndentificacionRepository extends JpaRepository<TipoIdentificacionEntity, Integer> {
}
