package br.com.douglasporto.gestao_vagas.modules.candidate.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfileCandidateDTO {
  
  @Schema(example = "Desenvolvedora Java", requiredMode = RequiredMode.REQUIRED)
  private String description;

  @Schema(example = "MaJu", requiredMode = RequiredMode.REQUIRED)
  private String username;

  @Schema(example = "MaJu@email.com", requiredMode = RequiredMode.REQUIRED)
  private String email;

  private UUID id;

  @Schema(example = "Maria Julia Porto", requiredMode = RequiredMode.REQUIRED)
  private String name;
}
