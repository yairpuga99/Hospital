package org.kosmos.backend.hospital.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "doctor")
@SequenceGenerator(name = "doctores_sequence", sequenceName = "doctores_sequence", initialValue = 1000)
public class DoctoresModel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doctores_sequence")
    private long doctor_id;
    @NotNull
    private String strNombre;
    @NotNull
    private String strPaterno;
    @NotNull
    private String strMaterno;
    @NotNull
    private String strEspecialidad;
}
