package br.com.douglasporto.gestao_vagas.modules.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.douglasporto.gestao_vagas.modules.company.dto.AuthcompanyDto;
import br.com.douglasporto.gestao_vagas.modules.company.useCases.AuthCompanyUsecase;

@RestController
@RequestMapping("/auth")
public class AuthCompanyController {

  @Autowired
  private AuthCompanyUsecase authCompanyUsecase;

  @PostMapping("/company")
  public ResponseEntity<Object> create(@RequestBody AuthcompanyDto authcompanyDto) {
    try {
      var result = this.authCompanyUsecase.execute(authcompanyDto);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }
  }
}
