package br.com.douglasporto.gestao_vagas.modules.company.dto;

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
public class CreateJobDTO {
  
  @Schema(example = "vaga para pessoa desenvolvedora Java/Node", requiredMode = RequiredMode.REQUIRED)
  private String description;

  @Schema(example = "Gympass, plano de saude, dayof no aniversario", requiredMode = RequiredMode.REQUIRED)
  private String benefits;

  @Schema(example = "junior", requiredMode = RequiredMode.REQUIRED)
  private String level;
}
