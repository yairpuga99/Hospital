package org.kosmos.backend.hospital.Exeption;

public class DoctorExistsException extends RuntimeException {

    public DoctorExistsException(String message) {
        super(message);
    }

    public DoctorExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}

