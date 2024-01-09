package org.kosmos.backend.hospital.dtos;


import org.kosmos.backend.hospital.Models.ConsultoriosModel;
import org.kosmos.backend.hospital.Models.DoctoresModel;

import lombok.Data;

@Data
public class CitasDto {
    private Long cita_id;
    private DoctoresModel doctor;
    private ConsultoriosModel consultorio;
    private String strFecha;
    private String strHora;
    private String strPaciente;
}
