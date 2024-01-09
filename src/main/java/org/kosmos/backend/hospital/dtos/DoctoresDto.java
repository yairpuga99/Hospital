package org.kosmos.backend.hospital.dtos;

import lombok.Data;

@Data
public class DoctoresDto {
    private long doctorId;
    private String strNombre;
    private String strPaterno;
    private String strMaterno;
    private String strEspecialidad;
}
