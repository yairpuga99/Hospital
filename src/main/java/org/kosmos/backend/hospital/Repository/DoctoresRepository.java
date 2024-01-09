package org.kosmos.backend.hospital.Repository;

import org.kosmos.backend.hospital.Models.DoctoresModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctoresRepository extends JpaRepository<DoctoresModel, Long> {
    boolean existsByStrNombreAndStrPaternoAndStrMaterno(String strNombre, String strPaterno, String strMaterno);
}

