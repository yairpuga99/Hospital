package org.kosmos.backend.hospital.Mapper;

import org.kosmos.backend.hospital.Models.CitasModel;
import org.kosmos.backend.hospital.dtos.CitasDto;
import org.kosmos.backend.hospital.dtos.CreateCitaDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CitasMapper {
    CitasDto toDto(CitasModel model);

    @Mapping(target = "cita_id", ignore = true)
    CitasModel toModel(CreateCitaDto dto);

}
