package br.com.douglasporto.gestao_vagas.modules.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthcompanyDto {
  
  private String username;
  private String password;
}
