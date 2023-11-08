package br.com.douglasporto.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.douglasporto.gestao_vagas.exceptions.UserFoundExceptions;
import br.com.douglasporto.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.douglasporto.gestao_vagas.modules.candidate.repositories.CandidateRepository;

@Service
public class CreateCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public CandidateEntity execute(CandidateEntity candidateEntity) {
    this.candidateRepository.findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
        .ifPresent((user) -> {
          throw new UserFoundExceptions();
        });

    var encodedPassword = passwordEncoder.encode(candidateEntity.getPassword());
    candidateEntity.setPassword(encodedPassword);

    return this.candidateRepository.save(candidateEntity);
  }
}
