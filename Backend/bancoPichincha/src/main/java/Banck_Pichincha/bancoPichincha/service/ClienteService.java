package Banck_Pichincha.bancoPichincha.service;

import Banck_Pichincha.bancoPichincha.model.dto.ClienteDto;
import Banck_Pichincha.bancoPichincha.model.dto.PersonaDto;
import Banck_Pichincha.bancoPichincha.model.entity.ClienteEntity;
import Banck_Pichincha.bancoPichincha.model.entity.PersonaEntity;
import Banck_Pichincha.bancoPichincha.model.response.ApiResponse;
import Banck_Pichincha.bancoPichincha.repository.ClienteRepository;
import Banck_Pichincha.bancoPichincha.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ClienteService implements  IClienteService{

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ResponseApiBuilder responseApiBuilder;

    @Autowired
    PersonaService personaService;

    @Override
    public ApiResponse<String> save(ClienteDto cliente) {
        try {
            ClienteEntity clienteEntity = new ClienteEntity();
            PersonaEntity persona = this.savePersona(cliente);
            if(persona!=null && persona.getIdPersona()!=null){
                clienteEntity.setPersona(persona);
                clienteEntity.setPassword(cliente.getPassword());
                clienteEntity.setEstado(cliente.getEstado());
                clienteEntity.setCreateAt(new Date());
                cliente.setCreate_at(null);
               ClienteEntity clienteSave = this.clienteRepository.save(clienteEntity);
               if(clienteSave!=null && clienteSave.getIdCliente()!=null){
                   String creacion = "El cliente "+cliente.getNombreCompleto() + " Fue creado con éxito";
                   return this.responseApiBuilder.successRespuesta(creacion,"cliente");
               }else{
                   return this.responseApiBuilder.errorRespuesta("CLIENT_CREATION_FAILED");
               }
            }else{
                return this.responseApiBuilder.errorRespuesta("CLIENT_CREATION_FAILED");
            }
        } catch (Exception e) {
            System.err.println("Error al guardar el cliente: " + e.getMessage());
            return this.responseApiBuilder.errorRespuesta("CLIENT_CREATION_FAILED");
        }
    }

    private PersonaEntity savePersona(ClienteDto cliente){
        try {
            PersonaDto persona = new PersonaDto();
            persona.setIdPersona(null);
            persona.setNombreCompleto(cliente.getNombreCompleto());
            persona.setFechaNacimiento(cliente.getFechaNacimiento());
            persona.setDireccion(cliente.getDireccion());
            persona.setTelefono(cliente.getTelefono());
            persona.setGenero(cliente.getGenero());
            persona.setTipoIdentificacion(cliente.getTipoIdentificacion());
            persona.setNumeIdentificacion(cliente.getNumeIdentificacion());
            persona.setCreate_at(new Date());
            persona.setUpdate_at(null);
            PersonaEntity savePersona = personaService.savePersona(persona);
            return savePersona;
        } catch (Exception e) {
            System.err.println("Error al guardar la persona: " + e.getMessage());
            return null;
        }

    }



    @Override
    public ApiResponse<String>  update(ClienteDto cliente) {
        try {
           Integer row =  this.clienteRepository.updateCliente(cliente);
           if(row>0){
               String nombreCompleto = this.clienteRepository.nombreCliente(cliente.getIdCliente());
               String creacion = "Los datos del cliente "+nombreCompleto + " Fueron actualizados con éxito";
               return this.responseApiBuilder.successRespuesta(creacion,"cliente");
           }else{
               return this.responseApiBuilder.errorRespuesta("UPDATE_RECORD_ERROR");
           }
        } catch (Exception e) {
            System.err.println("Error al actualizar el cliente: " + e.getMessage());
            return this.responseApiBuilder.errorRespuesta("UPDATE_RECORD_ERROR");
        }
    }

    @Override
    public ApiResponse<String>  getByCliente(Integer idCliente) {
        Map<String,Object> usuario = new HashMap<>();
        try {
            ClienteEntity cliente = this.clienteRepository.getByIdCliente(idCliente);
            if(cliente!=null && cliente.getIdCliente()!=null){
                usuario.put("ID_USUARIO",cliente.getIdCliente());
                usuario.put("Dirección",cliente.getPersona().getDireccion());
                usuario.put("Teléfono",cliente.getPersona().getTelefono());
                usuario.put("Contraseña",cliente.getPassword());
                usuario.put("Estado",cliente.getEstado());
                return this.responseApiBuilder.successRespuesta(usuario,"cliente");
            }else{
                return this.responseApiBuilder.errorRespuesta("USER_NOT_FOUND");

            }
        }catch (Exception e) {
            System.err.println("Error al buscar el cliente: " + e.getMessage());
            return this.responseApiBuilder.errorRespuesta("USER_NOT_FOUND");
        }
    }

    @Override
    public ApiResponse<String> getAllCliente() {
        try {
            List<ClienteEntity> clientes = this.clienteRepository.getAllCliente();

            if (clientes != null && !clientes.isEmpty()) {
                List<Map<String, Object>> usuarios = clientes.stream()
                        .map(cliente -> {
                            Map<String, Object> usuario = new HashMap<>();
                            if (cliente.getIdCliente() != null) {
                                usuario.put("ID_USUARIO", cliente.getIdCliente());
                            }
                            if (cliente.getPersona().getDireccion() != null) {
                                usuario.put("Dirección", cliente.getPersona().getDireccion());
                            }
                            if (cliente.getPersona().getTelefono() != null) {
                                usuario.put("Teléfono", cliente.getPersona().getTelefono());
                            }
                            if (cliente.getPassword() != null) {
                                usuario.put("Contraseña", cliente.getPassword());
                            }
                            if (cliente.getEstado() != null) {
                                usuario.put("Estado", cliente.getEstado());
                            }
                            return usuario;
                        })
                        .collect(Collectors.toList());

                return this.responseApiBuilder.successRespuesta(usuarios, "clientes");
            } else {
                return this.responseApiBuilder.errorRespuesta("USER_NOT_FOUND");
            }
        } catch (Exception e) {
            System.err.println("Error al buscar los clientes: " + e.getMessage());
            return this.responseApiBuilder.errorRespuesta("USER_NOT_FOUND");
        }
    }


    @Override
    public ApiResponse<String>  delete(Integer idCliente) {
        try {
            Integer row =  this.clienteRepository.deleteCliente(idCliente);
            if(row>0){
                String nombreCompleto = this.clienteRepository.nombreCliente(idCliente);
                String creacion = "El cliente "+nombreCompleto + " Fue eliminado con éxito";
                return this.responseApiBuilder.successRespuesta(creacion,"cliente");
            }else{
                return this.responseApiBuilder.errorRespuesta("CLIENT_DELETION_FAILED");
            }
        } catch (Exception e) {
            System.err.println("Error al eliminar el cliente: " + e.getMessage());
            return this.responseApiBuilder.errorRespuesta("CLIENT_DELETION_FAILED");
        }
    }
}
