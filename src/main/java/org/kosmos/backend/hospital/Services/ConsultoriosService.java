package org.kosmos.backend.hospital.Services;

import java.util.List;

import org.kosmos.backend.hospital.Mapper.ConsultoriosMapper;
import org.kosmos.backend.hospital.Models.ConsultoriosModel;
import org.kosmos.backend.hospital.Repository.ConsultoriosRepository;
import org.kosmos.backend.hospital.dtos.ConsultorioDto;
import org.kosmos.backend.hospital.dtos.CreateNewConsultorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsultoriosService {

    @Autowired
    private ConsultoriosRepository repository;

    @Autowired
    private ConsultoriosMapper mapper;

    public List<ConsultorioDto> findAll(){
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public ConsultorioDto newConsultorio(CreateNewConsultorio data){
        ConsultoriosModel entity = mapper.toModel(data);
        entity = repository.save(entity);
        return mapper.toDto(entity);
    }
    
}
