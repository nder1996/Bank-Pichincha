package Banck_Pichincha.bancoPichincha.repository;

import Banck_Pichincha.bancoPichincha.model.dto.CuentaDto;
import Banck_Pichincha.bancoPichincha.model.dto.MovimientoDto;
import Banck_Pichincha.bancoPichincha.model.entity.CuentaEntity;
import Banck_Pichincha.bancoPichincha.model.entity.MovimientosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface MovimientosRepository extends JpaRepository<MovimientosEntity, Integer> {

    @Query(value = "SELECT * FROM Movimientos WHERE idMovimientos  = :idMovimiento AND estado = 'TRUE'", nativeQuery = true)
    MovimientosEntity getByIdMovimientos(@Param("idMovimiento") Integer idMovimientos);


    @Query(value = "SELECT * FROM Movimientos WHERE estado = 'TRUE'", nativeQuery = true)
    List<MovimientosEntity> getAllMovimientos();

    @Modifying
    @Transactional
    @Query(value = "UPDATE Movimientos SET  estado = 'FALSE', update_at = NOW() WHERE idMovimientos = :idMovimiento", nativeQuery = true)
    Integer deleteMovimiento(@Param("idMovimiento") Integer idMovimiento);


    @Modifying
    @Transactional
    @Query(value = "UPDATE Movimientos SET fecha  = :#{#movimientoDto.fecha}, " +
            "idTipoMovimientos  = :#{#movimientoDto.idTipoMovimientos}, " +
            "idCuenta  = :#{#movimientoDto.idCuenta}, " +
            "valor  = :#{#movimientoDto.valor}, " +
            "estado  = :#{#movimientoDto.estado}, " +
            "saldo  = :#{#cuentmovimientoDtoaDto.saldo} , " +
            "update_at   = NOW()" +
            "WHERE idMovimientos = :#{#movimientoDto.idMovimientos}", nativeQuery = true)
    Integer updateMoviento(@Param("movimientoDto") MovimientoDto movimientoDto);



}
