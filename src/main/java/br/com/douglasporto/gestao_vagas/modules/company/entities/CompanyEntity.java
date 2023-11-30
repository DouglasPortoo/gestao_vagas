package br.com.douglasporto.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "company")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEntity {
  
  @Id 
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  private String name;

  @Pattern(regexp = "\\S+", message = "O campo não pode ter espaços em branco")
  private String username;

  @Email(message ="O campo deve conter um e-mail válido")
  private String email;

  @Length( min = 5, max = 200, message = "A senha deve ter no minimo 5 caracteres e no maximo 20 caracteres")
  private String password;
  private String website;
  private String description;
  
  @CreationTimestamp
  private LocalDateTime createdAt;
}
