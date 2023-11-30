package br.com.douglasporto.gestao_vagas.modules.candidate.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity(name = "candidate")
public class CandidateEntity {
  
  @Id 
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Schema(example = "Maria Fernanda Porto")
  private String name;

  @Pattern(regexp = "\\S+", message = "O campo não pode ter espaços em branco")
  @Schema(example = "MaFe")
  private String username;

  @Email(message ="O campo deve conter um e-mail válido")
  @Schema(example = "MaFe@email.com")
  private String email;

  @Length( min = 5, max = 200, message = "A senha deve ter no minimo 5 caracteres e no maximo 20 caracteres")
  private String password;

  @Schema(example = "Desenvolvedora UX/UI")
  private String description;
  private String curriculum;

  @CreationTimestamp
  private LocalDateTime createdAt;
}


