package org.kosmos.backend.hospital.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "consultorio")
public class ConsultoriosModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long consultorio_id;
    @NotNull
    private String strNumeroConsultorio;
    @NotNull
    private String strPiso;
}
