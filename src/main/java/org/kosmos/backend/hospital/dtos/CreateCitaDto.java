package org.kosmos.backend.hospital.dtos;

import lombok.Data;

@Data
public class CreateCitaDto {
    private Long doctor_id;
    private Long consultorio_id;
    private String strHora;
    private String strPaciente;
}
