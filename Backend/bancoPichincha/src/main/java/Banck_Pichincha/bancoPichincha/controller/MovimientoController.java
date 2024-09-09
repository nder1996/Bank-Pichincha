package Banck_Pichincha.bancoPichincha.controller;


import Banck_Pichincha.bancoPichincha.model.dto.MovimientoDto;
import Banck_Pichincha.bancoPichincha.model.entity.CuentaEntity;
import Banck_Pichincha.bancoPichincha.model.entity.MovimientosEntity;
import Banck_Pichincha.bancoPichincha.model.response.ApiResponse;
import Banck_Pichincha.bancoPichincha.service.CuentaService;
import Banck_Pichincha.bancoPichincha.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/movimientos")
@CrossOrigin(origins = "http://localhost:4200")
public class MovimientoController {


    @Autowired
    MovimientoService movimientoService;



    @GetMapping("/getByMovimiento")
    public ResponseEntity<ApiResponse<String>> getByMovimiento(@PathVariable Integer idMovimiento) {
        return new ResponseEntity<>(movimientoService.getByMovimiento(idMovimiento), HttpStatus.OK);
    }

    @GetMapping("/getAllMovimientos")
    public ResponseEntity<ApiResponse<String>> getAllMovimientos() {
        return new ResponseEntity<>(movimientoService.getAllMovimientos(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<String>> save(@RequestBody MovimientoDto Movimiento) {
        return new ResponseEntity<>(movimientoService.save(Movimiento), HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<String>> update(@RequestBody MovimientoDto movimiento) {
        return new ResponseEntity<>(movimientoService.update(movimiento), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idMovimiento}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer idMovimiento) {
        return new ResponseEntity<>(movimientoService.delete(idMovimiento), HttpStatus.OK);
    }

    @GetMapping("/getAllTipoMovimiento")
    public ResponseEntity<ApiResponse<String>> getAllTipoMovimiento() {
        return new ResponseEntity<>(movimientoService.getAllTipoMovimiento(), HttpStatus.OK);
    }

    @GetMapping("/getAllCuentas")
    public ResponseEntity<ApiResponse<String>> getAllCuentas() {
        return new ResponseEntity<>(movimientoService.getAllCuenta(), HttpStatus.OK);
    }

    @GetMapping("/movimientosHoyRetiro/{numeroCuenta}")
    public ResponseEntity<ApiResponse<String>> movimientosHoyRetiro(@PathVariable String numeroCuenta) {
        return new ResponseEntity<>(movimientoService.movimientosHoyRetiro(numeroCuenta), HttpStatus.OK);
    }


}
