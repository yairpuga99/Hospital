package org.kosmos.backend.hospital.dtos;

import lombok.Data;

@Data
public class ConsultorioDto {
    private Long consultorio_id;
    private String strNumeroConsultorio;
    private String strPiso;
}
