package org.kosmos.backend.hospital.dtos;

import lombok.Data;

@Data
public class DoctoresDto {
    private long doctor_id;
    private String strNombre;
    private String strPaterno;
    private String strMaterno;
    private String strEspecialidad;
}
