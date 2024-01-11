package br.com.yagomarialva.gestaovagas.modules.company.UseCases;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import br.com.yagomarialva.gestaovagas.modules.company.dto.AuthCompanyDTO;
import br.com.yagomarialva.gestaovagas.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Value;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class AuthCompanyUseCase {


  @Value("${security.token.secret}")
  private String secretKey;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String  execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException {
    var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(() -> {
      throw new UsernameNotFoundException("Company not found");
    });

    var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());

    if (!passwordMatches) {
      throw new UsernameNotFoundException("Username/password incorrect");
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);

    var token = JWT.create().withIssuer("javagas")
        .withSubject(company.getId().toString()).sign(algorithm);

    return token;
  }
  }
