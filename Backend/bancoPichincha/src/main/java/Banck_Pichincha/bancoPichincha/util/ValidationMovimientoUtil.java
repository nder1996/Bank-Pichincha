package Banck_Pichincha.bancoPichincha.util;

import Banck_Pichincha.bancoPichincha.model.dto.MovimientoDto;
import Banck_Pichincha.bancoPichincha.model.entity.TipoMovimientoEntity;
import org.springframework.stereotype.Component;

@Component

public class ValidationMovimientoUtil {


    public boolean esMovimientoValido(MovimientoDto movimiento) {
        return movimiento != null &&
                movimiento.getValor() != null &&
                movimiento.getValor() > 0 &&
                movimiento.getNumCuenta() != null;
    }

    public boolean esTipoMovimientoValido(TipoMovimientoEntity tipoMovimiento, MovimientoDto movimiento) {
        return tipoMovimiento.getIdTipoMovimientos() != null &&
                tipoMovimiento.getIdTipoMovimientos().equals(movimiento.getIdMovimientos());
    }


    public boolean procesarMovimiento(MovimientoDto movimiento, TipoMovimientoEntity tipoMovimiento) {
        if ("RETIRO".equals(tipoMovimiento.getNombre())) {
            return procesarRetiro(movimiento);
        } else if ("DEPÓSITO".equals(tipoMovimiento.getNombre())) {
            procesarDeposito(movimiento);
            return true;
        } else {
            return false;
        }
    }

    public boolean procesarRetiro(MovimientoDto movimiento) {
        if (movimiento.getSaldo() < movimiento.getValor()) {
            return false;
        }
        movimiento.setSaldo(movimiento.getSaldo() - movimiento.getValor());
        return true;
    }

    public void procesarDeposito(MovimientoDto movimiento) {
        movimiento.setSaldo(movimiento.getSaldo() + movimiento.getValor());
    }







}
