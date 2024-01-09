package org.kosmos.backend.hospital.dtos;

import lombok.Data;

@Data
public class CreateNewDoctorDto {
    private String strNombre;
    private String strPaterno;
    private String strMaterno;
    private String strEspecialidad;
}
