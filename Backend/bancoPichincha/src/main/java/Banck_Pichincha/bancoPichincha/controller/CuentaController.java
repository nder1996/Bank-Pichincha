package Banck_Pichincha.bancoPichincha.controller;


import Banck_Pichincha.bancoPichincha.model.dto.CuentaDto;
import Banck_Pichincha.bancoPichincha.model.entity.CuentaEntity;
import Banck_Pichincha.bancoPichincha.model.response.ApiResponse;
import Banck_Pichincha.bancoPichincha.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
@CrossOrigin(origins = "http://localhost:4200")
public class CuentaController {

    @Autowired
   CuentaService cuentaService;



    @GetMapping("/getByCuenta/{idCuenta}")
    public ResponseEntity<ApiResponse<String>> getByCuenta(@PathVariable Integer idCuenta) {
        return new ResponseEntity<>(cuentaService.getByCuenta(idCuenta), HttpStatus.OK);
    }

    @GetMapping("/getAllCuentas")
    public ResponseEntity<ApiResponse<String>> getAllCuentas() {
        return new ResponseEntity<>(cuentaService.getAllCuenta(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<String>> save(@RequestBody CuentaDto cuenta) {
        return new ResponseEntity<>(cuentaService.save(cuenta), HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<String>> update(@RequestBody CuentaDto cuenta) {
        return new ResponseEntity<>(cuentaService.update(cuenta), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idCuenta}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer idCuenta) {
        return new ResponseEntity<>(cuentaService.delete(idCuenta), HttpStatus.OK);
    }
}
