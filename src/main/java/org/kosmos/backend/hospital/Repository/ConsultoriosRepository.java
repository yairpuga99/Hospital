package org.kosmos.backend.hospital.Repository;

import org.kosmos.backend.hospital.Models.ConsultoriosModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultoriosRepository extends JpaRepository<ConsultoriosModel, Long>{
    
}
