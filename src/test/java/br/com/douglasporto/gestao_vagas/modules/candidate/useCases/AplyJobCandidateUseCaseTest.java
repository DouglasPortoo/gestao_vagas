package br.com.douglasporto.gestao_vagas.modules.candidate.useCases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.douglasporto.gestao_vagas.exceptions.JobNotFoundExeception;
import br.com.douglasporto.gestao_vagas.exceptions.UserNotFoundExeception;
import br.com.douglasporto.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import br.com.douglasporto.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.douglasporto.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import br.com.douglasporto.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.com.douglasporto.gestao_vagas.modules.company.entities.JobEntity;
import br.com.douglasporto.gestao_vagas.modules.company.repositories.JobRepository;

@ExtendWith(MockitoExtension.class)
public class AplyJobCandidateUseCaseTest {

  @InjectMocks
  private AplyJobCandidateUseCase aplyJobCandidateUseCase;

  @Mock
  private CandidateRepository candidateRepository;

  @Mock
  private JobRepository jobRepository;

  @Mock
  private ApplyJobRepository applyJobRepository;

  @Test
  @DisplayName("Should not be able to apply job with candidate not found")
  public void should_not_be_able_to_apply_job_with_candidate_not_found() {

    try {
      aplyJobCandidateUseCase.execute(null, null);
    } catch (Exception e) {
      assertThat(e).isInstanceOf(UserNotFoundExeception.class);
    }
  }

  @Test
  @DisplayName("Should not be able to apply job with job not found")
  public void should_not_be_able_to_apply_job_with_job_not_found(){

    var idCandidate= UUID.randomUUID();

    var candidate = new CandidateEntity();
    candidate.setId(idCandidate);

    when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(candidate));

    try {
      aplyJobCandidateUseCase.execute(idCandidate, null);
    } catch (Exception e) {
      assertThat(e).isInstanceOf(JobNotFoundExeception.class);
    } 
  }

  @Test
    @DisplayName("Should not be able to create a new apply job")

  public void should_be_able_to_create_a_new_apply_job(){

    var idCandidate= UUID.randomUUID();
    var idJob= UUID.randomUUID();

    var applyJob = ApplyJobEntity.builder().candidateId(idCandidate).jobId(idJob).build();

    var applyJobCreted = ApplyJobEntity.builder().id(UUID.randomUUID()).build();

    when(candidateRepository.findById(idCandidate)).thenReturn(Optional.of(new CandidateEntity()));
    when(jobRepository.findById(idJob)).thenReturn(Optional.of(new JobEntity()));

    when(applyJobRepository.save(applyJob)).thenReturn(applyJobCreted);

    var result = aplyJobCandidateUseCase.execute(idCandidate, idJob);

    assertThat(result).hasFieldOrProperty("id");
    assertNotNull(result.getId());

  }
}
