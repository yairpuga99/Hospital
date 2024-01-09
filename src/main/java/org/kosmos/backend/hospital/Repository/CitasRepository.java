package org.kosmos.backend.hospital.Repository;

import java.util.List;

import org.kosmos.backend.hospital.Models.CitasModel;
import org.kosmos.backend.hospital.Models.ConsultoriosModel;
import org.kosmos.backend.hospital.Models.DoctoresModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitasRepository extends JpaRepository<CitasModel, Long> {

    boolean existsByConsultorioAndStrHora(ConsultoriosModel consultorio, String strHora);

    boolean existsByDoctorAndStrHora(DoctoresModel doctor, String strHora);

    boolean existsByStrPacienteAndStrHoraGreaterThanEqualAndStrHoraLessThanEqual(String strPaciente, String start, String end);

    long countByDoctorAndStrHoraBetween(DoctoresModel doctor, String start, String end);

    List<CitasModel> findByStrHora(String strHora);

    List<CitasModel> findByDoctor_DoctorId(Long doctorId);

    List<CitasModel> findByStrFecha(String strFecha);

    List<CitasModel> findByStrFechaAndDoctor_DoctorId(String strFecha, Long doctorId);
}
