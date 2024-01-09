package org.kosmos.backend.hospital.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "citas")
public class CitasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cita_id;
    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private DoctoresModel doctor;
    @ManyToOne
    @JoinColumn(name = "consultorio_id")
    private ConsultoriosModel consultorio;
    @NotNull
    private String strHora;
    @NotNull
    private String strPaciente;
}
