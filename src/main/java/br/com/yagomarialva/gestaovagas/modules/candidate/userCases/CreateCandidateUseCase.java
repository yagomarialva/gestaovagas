package br.com.yagomarialva.gestaovagas.modules.candidate.userCases;


import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.yagomarialva.gestaovagas.modules.candidate.CandidateEntity;
import br.com.yagomarialva.gestaovagas.modules.candidate.CandidateRepository;
import br.com.yagomarialva.gestaovagas.modules.candidate.UserFoundException;
import br.com.yagomarialva.gestaovagas.modules.company.dto.AuthCompanyDTO;
import br.com.yagomarialva.gestaovagas.modules.company.repositories.CompanyRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;


@Service
public class CreateCandidateUseCase {
    @Autowired
  private CompanyRepository companyRepository;

  @Value("${security.token.secret}")
  private String secretKey;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String execute(@Valid CandidateEntity candidateEntity) throws AuthenticationException {
    var company = this.companyRepository.findByUsername(candidateEntity.getUsername()).orElseThrow(() -> {
      throw new UsernameNotFoundException("Company not found");
    });

    var passwordMatches = this.passwordEncoder.matches(candidateEntity.getPassword(), company.getPassword());

    if (!passwordMatches) {
      throw new AuthenticationException();
    }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

    var token = JWT.create().withIssuer("javagas")
        .withSubject(company.getId().toString()).sign(algorithm);

    return token;
  }

}
