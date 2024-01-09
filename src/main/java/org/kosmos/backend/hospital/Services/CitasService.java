package org.kosmos.backend.hospital.Services;

import java.util.List;
import java.util.Optional;

import org.kosmos.backend.hospital.Exeption.CitaNotFoundException;
import org.kosmos.backend.hospital.Mapper.CitasMapper;
import org.kosmos.backend.hospital.Models.CitasModel;
import org.kosmos.backend.hospital.Models.ConsultoriosModel;
import org.kosmos.backend.hospital.Models.DoctoresModel;
import org.kosmos.backend.hospital.Repository.CitasRepository;
import org.kosmos.backend.hospital.Repository.ConsultoriosRepository;
import org.kosmos.backend.hospital.Repository.DoctoresRepository;
import org.kosmos.backend.hospital.dtos.CitasDto;
import org.kosmos.backend.hospital.dtos.CreateCitaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitasService {

    @Autowired
    private CitasRepository repository;

    @Autowired
    private CitasMapper mapper;

    @Autowired 
    private DoctoresRepository doctoresRepository; 

    @Autowired 
    private ConsultoriosRepository consultoriosRepository; 

    public List<CitasDto> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public CitasDto newCita(CreateCitaDto createCitaDto) {

        validations(createCitaDto);

        CitasModel entity = mapper.toModel(createCitaDto);

        if (createCitaDto.getDoctor_id() != null) {
            DoctoresModel doctor = doctoresRepository.findById(createCitaDto.getDoctor_id())
                    .orElseThrow(() -> new RuntimeException("Doctor no encontrado con ID: " + createCitaDto.getDoctor_id()));
                    entity.setDoctor(doctor);
        }

        if (createCitaDto.getConsultorio_id() != null) {
            ConsultoriosModel consultorio = consultoriosRepository.findById(createCitaDto.getConsultorio_id())
                    .orElseThrow(() -> new RuntimeException("Consultorio no encontrado con ID: " + createCitaDto.getConsultorio_id()));
                    entity.setConsultorio(consultorio);
        }

        entity = repository.save(entity);

        return mapper.toDto(entity);
    }

    private void validations(CreateCitaDto createCitaDto) {
        // Validación: No se puede agendar cita en un mismo consultorio a la misma hora.
        ConsultoriosModel consultorio = consultoriosRepository.findById(createCitaDto.getConsultorio_id())
                .orElseThrow(() -> new RuntimeException("Consultorio no encontrado con ID: " + createCitaDto.getConsultorio_id()));
        if (repository.existsByConsultorioAndStrHora(consultorio, createCitaDto.getStrHora())) {
            throw new RuntimeException("No se puede agendar cita en un mismo consultorio a la misma hora");
        }
    
        // Validación: No se puede agendar cita para un mismo Dr. a la misma hora.
        DoctoresModel doctor = doctoresRepository.findById(createCitaDto.getDoctor_id())
                .orElseThrow(() -> new RuntimeException("Doctor no encontrado con ID: " + createCitaDto.getDoctor_id()));
        if (repository.existsByDoctorAndStrHora(doctor, createCitaDto.getStrHora())) {
            throw new RuntimeException("No se puede agendar cita para un mismo Dr. a la misma hora");
        }
    
        // Validación: No se puede agendar cita para un paciente a la misma hora o con menos de 2 horas de diferencia para el mismo día.
        if (repository.existsByStrPacienteAndStrHoraGreaterThanEqualAndStrHoraLessThanEqual(
                createCitaDto.getStrPaciente(),
                createCitaDto.getStrHora(),
                createCitaDto.getStrHora())) {
            throw new RuntimeException("No se puede agendar cita para un paciente a la misma hora o con menos de 2 horas de diferencia para el mismo día");
        }
    
        // Validación: Un mismo doctor no puede tener más de 8 citas en el día.
        long countCitasByDoctor = repository.countByDoctorAndStrHoraBetween(
                doctor,
                createCitaDto.getStrHora(),
                createCitaDto.getStrHora());
        if (countCitasByDoctor >= 8) {
            throw new RuntimeException("Un mismo doctor no puede tener más de 8 citas en el día");
        }
    }


    public boolean deleteCitaById(Long id) {
        Optional<CitasModel> optionalCita = repository.findById(id);

        if (optionalCita.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            throw new CitaNotFoundException("La cita con ID " + id + " no existe");
        }
    }

    public CitasDto findCitaById(Long id) {
        CitasModel cita = repository.findById(id)
                .orElseThrow(() -> new CitaNotFoundException("Cita no encontrada con ID: " + id));
        return mapper.toDto(cita);
    }

    public List<CitasDto> getCitasPorHora(String strHora) {
        List<CitasModel> citasPorHora = repository.findByStrHora(strHora);
    
        if (citasPorHora.isEmpty()) {
            throw new CitaNotFoundException("No se encontraron citas para la hora proporcionada");
        }
    
        return citasPorHora.stream().map(mapper::toDto).toList();
    }

    public List<CitasDto> getCitasPorDoctorId(Long doctor_id) {
        List<CitasModel> citasPorDoctor = repository.findByDoctor_DoctorId(doctor_id);
    
        if (citasPorDoctor.isEmpty()) {
            throw new CitaNotFoundException("No se encontraron citas para el doctor proporcionado");
        }
    
        return citasPorDoctor.stream().map(mapper::toDto).toList();
    }

    public List<CitasDto> getCitasPorFecha(String strFecha) {
        List<CitasModel> citasPorFecha = repository.findByStrFecha(strFecha);
    
        if (citasPorFecha.isEmpty()) {
            throw new CitaNotFoundException("No se encontraron citas para la fecha proporcionada");
        }
    
        return citasPorFecha.stream().map(mapper::toDto).toList();
    }

    public List<CitasDto> getCitaPorDoctorYFecha(String strFecha, Long doctor_id) {
        List<CitasModel> citasPorFecha = repository.findByStrFechaAndDoctor_DoctorId(strFecha, doctor_id);
    
        if (citasPorFecha.isEmpty()) {
            throw new CitaNotFoundException("No se encontraron citas para la fecha y el doctor proporcionados");
        }
    
        return citasPorFecha.stream().map(mapper::toDto).toList();
    }

    public CitasDto editarCita(Long cita_id, CitasDto citaDto) throws CitaNotFoundException {
        CitasModel citaExistente = repository.findById(cita_id)
                .orElseThrow(() -> new CitaNotFoundException("Cita no encontrada con ID: " + cita_id));

        citaExistente.setStrFecha(citaDto.getStrFecha());
        citaExistente.setStrHora(citaDto.getStrHora());
        citaExistente.setStrPaciente(citaDto.getStrPaciente());
    
        CitasModel citaActualizada = repository.save(citaExistente);
    
        return mapper.toDto(citaActualizada);
    }


}
