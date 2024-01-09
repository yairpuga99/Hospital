package org.kosmos.backend.hospital.Mapper;

import org.kosmos.backend.hospital.Models.DoctoresModel;
import org.kosmos.backend.hospital.dtos.CreateNewDoctorDto;
import org.kosmos.backend.hospital.dtos.DoctoresDto;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface DoctoresMapper {

    DoctoresDto toDto(DoctoresModel model);

    @Mapping(target = "doctor_id", ignore = true)
    DoctoresModel toModel(CreateNewDoctorDto dto);
}
