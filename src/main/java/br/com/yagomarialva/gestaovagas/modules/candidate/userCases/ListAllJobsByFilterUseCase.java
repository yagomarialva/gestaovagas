package br.com.yagomarialva.gestaovagas.modules.candidate.userCases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.yagomarialva.gestaovagas.modules.company.entities.JobEntity;
import br.com.yagomarialva.gestaovagas.modules.company.repositories.JobRepository;


@Service
public class ListAllJobsByFilterUseCase {

  @Autowired
  private JobRepository jobRepository;

  public List<JobEntity> execute(String filter) {
    return this.jobRepository.findByDescriptionContaining(filter);
  }
}