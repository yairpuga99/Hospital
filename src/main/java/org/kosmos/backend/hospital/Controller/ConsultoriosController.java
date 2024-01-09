package org.kosmos.backend.hospital.Controller;

import java.util.List;

import org.kosmos.backend.hospital.Services.ConsultoriosService;
import org.kosmos.backend.hospital.dtos.ConsultorioDto;
import org.kosmos.backend.hospital.dtos.CreateNewConsultorio;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("consultorios")
public class ConsultoriosController {

    @Autowired
    private ConsultoriosService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ConsultorioDto>findAll(){
        return service.findAll();
    }

    @PostMapping("/newConsultorio")
    @ResponseStatus(HttpStatus.CREATED)
    public ConsultorioDto newConsultorio(@Valid @RequestBody CreateNewConsultorio data){
        return service.newConsultorio(data);
    }

}
