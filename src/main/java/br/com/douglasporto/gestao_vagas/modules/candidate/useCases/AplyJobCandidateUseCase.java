package br.com.douglasporto.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.douglasporto.gestao_vagas.exceptions.JobNotFoundExeception;
import br.com.douglasporto.gestao_vagas.exceptions.UserNotFoundExeception;
import br.com.douglasporto.gestao_vagas.modules.candidate.entities.ApplyJobEntity;
import br.com.douglasporto.gestao_vagas.modules.candidate.repositories.ApplyJobRepository;
import br.com.douglasporto.gestao_vagas.modules.candidate.repositories.CandidateRepository;
import br.com.douglasporto.gestao_vagas.modules.company.repositories.JobRepository;

@Service
public class AplyJobCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private JobRepository jobRepository;

  @Autowired
  private ApplyJobRepository applyJobRepository;
  
  public ApplyJobEntity execute(UUID idCandidate, UUID idJob){
    this.candidateRepository.findById(idCandidate).orElseThrow(()->{
      throw new UserNotFoundExeception();
    });

    this.jobRepository.findById(idJob).orElseThrow(()->{
      throw new JobNotFoundExeception();
    });

    var applyJob = ApplyJobEntity.builder()
        .candidateId(idCandidate)
        .jobId(idJob).build();

        applyJob = applyJobRepository.save(applyJob);
        return applyJob;
  }



}
