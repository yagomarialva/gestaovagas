package br.com.yagomarialva.gestaovagas.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yagomarialva.gestaovagas.modules.candidate.entities.CandidateEntity;
import br.com.yagomarialva.gestaovagas.modules.candidate.repositories.CandidateRepository;
import br.com.yagomarialva.gestaovagas.modules.candidate.userCases.CreateCandidateUseCase;
import br.com.yagomarialva.gestaovagas.modules.exceptions.UserFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {
  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
    try {
      var result = createCandidateUseCase.execute(candidateEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}
