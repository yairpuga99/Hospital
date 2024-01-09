package org.kosmos.backend.hospital.Controller;

import java.util.List;

import org.kosmos.backend.hospital.Exeption.DoctorNotFoundException;
import org.kosmos.backend.hospital.Services.DoctoresService;
import org.kosmos.backend.hospital.dtos.CreateNewDoctorDto;
import org.kosmos.backend.hospital.dtos.DoctoresDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("doctores")
public class DoctoresController {

    @Autowired
    private DoctoresService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<DoctoresDto> findAll() {
        return service.findAll();
    }

    @PostMapping("/newDoctor")
    public ResponseEntity<Object> newDoctor(@Valid @RequestBody CreateNewDoctorDto data) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.newDoctor(data));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteInventario(@PathVariable Long id) {
        try {
            boolean deleted = service.deleteById(id);

            if (deleted) {
                return ResponseEntity.ok(true);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
        } catch (DoctorNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }


}
