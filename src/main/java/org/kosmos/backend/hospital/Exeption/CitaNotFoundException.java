package org.kosmos.backend.hospital.Exeption;

public class CitaNotFoundException extends RuntimeException {

    public CitaNotFoundException(String message) {
        super(message);
    }
}