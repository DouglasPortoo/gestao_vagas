package br.com.douglasporto.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.douglasporto.gestao_vagas.modules.candidate.dto.ProfileCandidateDTO;
import br.com.douglasporto.gestao_vagas.modules.candidate.repositories.CandidateRepository;

@Service
public class ProfileCandidateUseCase {

  @Autowired
  private CandidateRepository candidateRepository;
  
  public ProfileCandidateDTO execute(UUID idCandadate){
    var candidate = this.candidateRepository.findById(idCandadate).orElseThrow(()->{
      throw new UsernameNotFoundException("User not found");
    });

    var candidateDTO = ProfileCandidateDTO.builder().description(candidate.getDescription()).username(candidate.getUsername()).email(candidate.getEmail()).name(candidate.getName()).id(candidate.getId()).build();

    return candidateDTO;
  }
}
