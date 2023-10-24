package br.com.douglasporto.gestao_vagas.modules.candidate;

import java.util.UUID;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CandidateEntity {
  
  private UUID id;
  private String name;

  @Pattern(regexp = "^(?!\\s*$).+", message = "O campo não pode ter espaços em branco")
  private String username;

  @Email(message ="O campo deve conter um e-mail válido")
  private String email;

  @Length( min = 5, max = 20)
  private String password;
  private String description;
  private String curriculum;

}
