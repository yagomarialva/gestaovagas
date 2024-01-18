package br.com.yagomarialva.gestaovagas.modules.candidate.userCases;


import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.yagomarialva.gestaovagas.modules.candidate.entities.CandidateEntity;
import br.com.yagomarialva.gestaovagas.modules.candidate.repositories.CandidateRepository;
import br.com.yagomarialva.gestaovagas.modules.company.dto.AuthCompanyDTO;
import br.com.yagomarialva.gestaovagas.modules.company.entities.CompanyEntity;
import br.com.yagomarialva.gestaovagas.modules.company.repositories.CompanyRepository;
import br.com.yagomarialva.gestaovagas.modules.exceptions.UserFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;


@Service
public class CreateCandidateUseCase {
  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public CandidateEntity execute(CandidateEntity candidateEntity) {
    this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(),
        candidateEntity.getEmail()).ifPresent(user -> {
          throw new UserFoundException();
        });

    
        var password = passwordEncoder.encode(candidateEntity.getPassword());
        candidateEntity.setPassword(password);

    return this.candidateRepository.save(candidateEntity);
  }
}
