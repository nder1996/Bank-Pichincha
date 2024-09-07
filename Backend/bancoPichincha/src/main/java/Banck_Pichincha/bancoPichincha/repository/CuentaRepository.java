package Banck_Pichincha.bancoPichincha.repository;

import Banck_Pichincha.bancoPichincha.model.dto.CuentaDto;
import Banck_Pichincha.bancoPichincha.model.entity.ClienteEntity;
import Banck_Pichincha.bancoPichincha.model.entity.CuentaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CuentaRepository extends JpaRepository<CuentaEntity, Integer> {



    @Query(value = "SELECT * FROM Cuenta WHERE idCuenta = :idCuenta AND estado = 'TRUE'", nativeQuery = true)
    CuentaEntity getByIdCuenta(@Param("idCuenta") Integer idCuenta);

    @Query(value = "select * from Cuenta as cuenta " +
            "left join Movimientos as movimiento on cuenta.idCuenta = movimiento.idMovimientos " +
            "where cuenta.estado = 'TRUE' and movimiento.estado = 'TRUE' and cuenta.numeroCuenta = :numCuenta", nativeQuery = true)
    CuentaEntity getByIdCuentaxNumCuenta(@Param("numCuenta") Integer numCuenta);



    @Query(value = "SELECT * FROM Cuenta WHERE estado = 'TRUE'", nativeQuery = true)
    List<CuentaEntity> getAllCuenta();


    @Modifying
    @Transactional
    @Query(value = "UPDATE Cuenta SET  estado = 'FALSE', update_at = NOW() WHERE idCuenta = :idCuenta", nativeQuery = true)
    Integer deleteCuenta(@Param("idCuenta") Integer idCuenta);



    @Modifying
    @Transactional
    @Query(value = "UPDATE Cuenta SET numeroCuenta = :#{#cuentaDto.numeroCuenta}, " +
            "idTipoCuenta = :#{#cuentaDto.tipoCuenta}, " +
            "saldoInicial = :#{#cuentaDto.saldoInicial}, " +
            "estado = :#{#cuentaDto.estado}, " +
            "update_at = :#{#cuentaDto.update_at} " +
            "WHERE idCuenta = :#{#cuentaDto.idCuenta}", nativeQuery = true)
    Integer updateCuenta(@Param("cuentaDto") CuentaDto cuentaDto);






}
