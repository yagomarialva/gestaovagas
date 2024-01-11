package br.com.yagomarialva.gestaovagas.modules.company.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.yagomarialva.gestaovagas.modules.company.entities.JobEntity;

public interface JobRepository  extends JpaRepository<JobEntity, UUID> {
    
}
