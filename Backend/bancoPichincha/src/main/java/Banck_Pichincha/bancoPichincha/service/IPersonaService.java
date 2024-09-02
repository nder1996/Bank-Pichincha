package Banck_Pichincha.bancoPichincha.service;

import Banck_Pichincha.bancoPichincha.model.dto.PersonaDto;
import Banck_Pichincha.bancoPichincha.model.entity.PersonaEntity;

public interface IPersonaService {

    public PersonaEntity savePersona(PersonaDto personaDto);
}
