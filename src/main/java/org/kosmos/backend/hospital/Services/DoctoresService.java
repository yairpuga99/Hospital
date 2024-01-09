package org.kosmos.backend.hospital.Services;

import java.util.List;
import java.util.Optional;

import org.kosmos.backend.hospital.Exeption.DoctorExistsException;
import org.kosmos.backend.hospital.Exeption.DoctorNotFoundException;
import org.kosmos.backend.hospital.Mapper.DoctoresMapper;
import org.kosmos.backend.hospital.Models.DoctoresModel;
import org.kosmos.backend.hospital.Repository.DoctoresRepository;
import org.kosmos.backend.hospital.dtos.CreateNewDoctorDto;
import org.kosmos.backend.hospital.dtos.DoctoresDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctoresService {
    @Autowired
    private DoctoresRepository repository;

    @Autowired
    private DoctoresMapper mapper;

    public List<DoctoresDto> findAll(){
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public DoctoresDto newDoctor(CreateNewDoctorDto data){
        if(repository.existsByStrNombreAndStrPaternoAndStrMaterno(data.getStrNombre(), data.getStrPaterno(), data.getStrMaterno())){
            throw new DoctorExistsException("El doctor ya existe");
        }else{
        DoctoresModel entity = mapper.toModel(data);
        entity = repository.save(entity);
        return mapper.toDto(entity);
        }
    }

    public boolean deleteById(Long id) {
        Optional<DoctoresModel> optionalDoctor = repository.findById(id);

        if (optionalDoctor.isPresent()) {
            repository.deleteById(id);
            return true;
        } else {
            throw new DoctorNotFoundException("El doctor con ID " + id + " no existe");
        }
    }

}
