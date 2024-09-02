package Banck_Pichincha.bancoPichincha.service;

import Banck_Pichincha.bancoPichincha.model.dto.PersonaDto;
import Banck_Pichincha.bancoPichincha.model.entity.GenerosEntity;
import Banck_Pichincha.bancoPichincha.model.entity.PersonaEntity;
import Banck_Pichincha.bancoPichincha.model.entity.TipoIdentificacionEntity;
import Banck_Pichincha.bancoPichincha.repository.GenerosRepository;
import Banck_Pichincha.bancoPichincha.repository.PersonaRepository;
import Banck_Pichincha.bancoPichincha.repository.TipoIndentificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonaService implements IPersonaService{

    @Autowired
    GenerosRepository generosRepository;

    @Autowired
    TipoIndentificacionRepository  tipoIndentificacionRepository;

    @Autowired
    PersonaRepository  personaRepository;


    @Override
    public PersonaEntity savePersona(PersonaDto personaDto) {

        PersonaEntity personaEntity = new PersonaEntity();
        GenerosEntity generosEntity = new GenerosEntity();
        TipoIdentificacionEntity tipoIdentificacion = new TipoIdentificacionEntity();

        personaEntity.setCreate_at(personaDto.getCreate_at());
        personaEntity.setNombreCompleto(personaDto.getNombreCompleto());
        personaEntity.setFechaNacimiento(personaDto.getFechaNacimiento());
        personaEntity.setDireccion(personaDto.getDireccion());
        personaEntity.setTelefono(personaDto.getTelefono());
        generosEntity = this.generosRepository.getReferenceById(personaDto.getGenero());
        tipoIdentificacion = this.tipoIndentificacionRepository.getReferenceById(personaDto.getTipoIdentificacion());

        personaEntity.setGenero(generosEntity);
        personaEntity.setTipoIdentificacion(tipoIdentificacion);
        personaEntity.setNumeIdentificacion(personaDto.getNumeIdentificacion());
        personaEntity.setCreate_at(personaDto.getCreate_at());
        personaEntity.setUpdate_at(personaDto.getUpdate_at());
        PersonaEntity persona = personaRepository.save(personaEntity);
        return persona;
    }
}
