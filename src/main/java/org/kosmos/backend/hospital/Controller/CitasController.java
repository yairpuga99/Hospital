package org.kosmos.backend.hospital.Controller;

import java.util.List;

import org.kosmos.backend.hospital.Exeption.CitaNotFoundException;
import org.kosmos.backend.hospital.Services.CitasService;
import org.kosmos.backend.hospital.dtos.CitasDto;
import org.kosmos.backend.hospital.dtos.CreateCitaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("citas")
public class CitasController {

    @Autowired
    private CitasService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CitasDto>findAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getCitaById(@PathVariable Long id) {
        try {
            CitasDto citaDto = service.findCitaById(id);
            return ResponseEntity.ok(citaDto);
        } catch (CitaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @GetMapping("/hora/{strHora}")
    @ResponseStatus(HttpStatus.OK)
    public List<CitasDto>findByHora(@PathVariable String strHora){
        return service.getCitasPorHora(strHora);
    }

    @GetMapping("/doctor/{doctor_id}")
    @ResponseStatus(HttpStatus.OK)
    public List<CitasDto>findByDoctor(@PathVariable Long doctor_id){
        return service.getCitasPorDoctorId(doctor_id);
    }

    @GetMapping("/fecha/{strFecha}")
    @ResponseStatus(HttpStatus.OK)
    public List<CitasDto>findByFecha(@PathVariable String strFecha){
        return service.getCitasPorFecha(strFecha);
    }

    @GetMapping("doctor/{doctor_id}/fecha/{strFecha}")
    @ResponseStatus(HttpStatus.OK)
    public List<CitasDto>findByDoctorAndFecha(@PathVariable String strFecha, @PathVariable Long doctor_id){
        return service.getCitaPorDoctorYFecha(strFecha, doctor_id);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CitasDto newCita(@Valid @RequestBody CreateCitaDto data){
        return service.newCita(data);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCita(@PathVariable Long id) {
        try {
            service.deleteCitaById(id);
            return ResponseEntity.ok().build();
        } catch (CitaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor");
        }
    }

    @PutMapping("/editar/{cita_id}")
    public ResponseEntity<CitasDto> editCita(@PathVariable Long cita_id, @RequestBody CitasDto citaDto) {
        try {
            CitasDto citaEditada = service.editarCita(cita_id, citaDto);
            return ResponseEntity.ok(citaEditada);
        } catch (CitaNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    
}
