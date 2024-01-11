package br.com.yagomarialva.gestaovagas.modules.company.UseCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.yagomarialva.gestaovagas.modules.company.entities.JobEntity;
import br.com.yagomarialva.gestaovagas.modules.company.repositories.JobRepository;

@Service
public class CreateJobUseCase {

    @Autowired
    private JobRepository jobRepository;

    public JobEntity execute(JobEntity jobEntity) {
    return this.jobRepository.save(jobEntity);
  }
    
}
