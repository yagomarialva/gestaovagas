package br.com.yagomarialva.gestaovagas.modules.candidate.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.yagomarialva.gestaovagas.modules.candidate.entities.CandidateEntity;
import br.com.yagomarialva.gestaovagas.modules.candidate.repositories.CandidateRepository;
import br.com.yagomarialva.gestaovagas.modules.candidate.userCases.CreateCandidateUseCase;
import br.com.yagomarialva.gestaovagas.modules.candidate.userCases.ListAllJobsByFilterUseCase;
import br.com.yagomarialva.gestaovagas.modules.candidate.userCases.ProfileCandidateUseCase;
import br.com.yagomarialva.gestaovagas.modules.company.entities.JobEntity;
import br.com.yagomarialva.gestaovagas.modules.exceptions.UserFoundException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
@PreAuthorize("hasRole('CANDIDATE')")
public class CandidateController {
  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @Autowired
  private ListAllJobsByFilterUseCase listAllJobsByFilterUseCase;

  @Autowired
  private ProfileCandidateUseCase profileCandidateUseCase;


  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
    try {
      var result = createCandidateUseCase.execute(candidateEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

   @GetMapping("/")
  public ResponseEntity<Object> get(HttpServletRequest request) {
    var idCandidate = request.getAttribute("candidate_id");

    try {
      var profile = this.profileCandidateUseCase
          .execute(UUID.fromString(idCandidate.toString()));
      return ResponseEntity.ok().body(profile);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/job")
  @PreAuthorize("hasRole('CANDIDATE')")
  public List<JobEntity> findJobByFilter(@RequestParam String filter) {
    return this.listAllJobsByFilterUseCase.execute(filter);
  }
}
