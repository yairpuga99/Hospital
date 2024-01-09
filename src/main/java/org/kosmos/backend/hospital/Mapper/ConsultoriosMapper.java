package org.kosmos.backend.hospital.Mapper;

import org.kosmos.backend.hospital.Models.ConsultoriosModel;
import org.kosmos.backend.hospital.dtos.ConsultorioDto;
import org.kosmos.backend.hospital.dtos.CreateNewConsultorio;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ConsultoriosMapper {
    ConsultorioDto toDto(ConsultoriosModel model);

    @Mapping(target = "consultorio_id", ignore = true)
    ConsultoriosModel toModel(CreateNewConsultorio dto);
}
