package Banck_Pichincha.bancoPichincha.controller;


import Banck_Pichincha.bancoPichincha.model.dto.ClienteDto;
import Banck_Pichincha.bancoPichincha.model.entity.ClienteEntity;
import Banck_Pichincha.bancoPichincha.model.response.ApiResponse;
import Banck_Pichincha.bancoPichincha.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    ClienteService clienteService;



    @GetMapping("/getByCliente/{idCliente}")
    public ResponseEntity<ApiResponse<String>> getByCliente(@PathVariable Integer idCliente) {
        return new ResponseEntity<>(clienteService.getByCliente(idCliente), HttpStatus.OK);
    }

    @GetMapping("/getAllCliente")
    public ResponseEntity<ApiResponse<String>> getAllCliente() {
        return new ResponseEntity<>(clienteService.getAllCliente(), HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<String>> save(@RequestBody ClienteDto cliente) {
        return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.OK);
    }

    @PatchMapping("/update")
    public ResponseEntity<ApiResponse<String>> update(@RequestBody ClienteDto cliente) {
        return new ResponseEntity<>(clienteService.update(cliente), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idCliente}")
    public ResponseEntity<ApiResponse<String>> delete(@PathVariable Integer idCliente) {
        return new ResponseEntity<>(clienteService.delete(idCliente), HttpStatus.OK);
    }







}
