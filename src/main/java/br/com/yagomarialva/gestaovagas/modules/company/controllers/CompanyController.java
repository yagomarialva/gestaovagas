package br.com.yagomarialva.gestaovagas.modules.company.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.yagomarialva.gestaovagas.modules.company.UseCases.CreateCompanyUseCase;
import br.com.yagomarialva.gestaovagas.modules.company.entities.CompanyEntity;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CreateCompanyUseCase createCompanyUseCase;

    @PostMapping("/")
    public ResponseEntity<Object> create(@Valid @RequestBody CompanyEntity companyEntity) {
        try {
        var result = this.createCompanyUseCase.execute(companyEntity);
        return ResponseEntity.ok().body(result);
        } catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
