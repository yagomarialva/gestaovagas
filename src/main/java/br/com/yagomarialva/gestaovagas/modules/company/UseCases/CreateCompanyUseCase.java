package br.com.yagomarialva.gestaovagas.modules.company.UseCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.yagomarialva.gestaovagas.modules.company.entities.CompanyEntity;
import br.com.yagomarialva.gestaovagas.modules.company.repositories.CompanyRepository;
import br.com.yagomarialva.gestaovagas.modules.exceptions.UserFoundException;

@Service
public class CreateCompanyUseCase {
@Autowired
  private PasswordEncoder passwordEncoder;
    @Autowired
    private CompanyRepository companyRepository;

    public CompanyEntity execute(CompanyEntity companyEntity) {
        this.companyRepository
            .findByUsernameOrEmail(companyEntity.getUsername(), companyEntity.getEmail())
            .ifPresent(user -> {
            throw new UserFoundException();
            });

            var password = passwordEncoder.encode(companyEntity.getPassword());
            companyEntity.setPassword(password);
        
        return this.companyRepository.save(companyEntity);
    }
}
