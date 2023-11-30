package br.com.douglasporto.gestao_vagas.modules.candidate.useCases;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.douglasporto.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.douglasporto.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;
import br.com.douglasporto.gestao_vagas.modules.candidate.repositories.CandidateRepository;

import java.time.Instant;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class AuthCandidateUseCase {

  @Value("${security.token.secret.candidate}")
  private String secretkey;

  @Autowired
  private CandidateRepository candidateRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO)
      throws AuthenticationException {
    var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
        .orElseThrow(() -> {
          throw new UsernameNotFoundException("username/password incorret");
        });

    var passwordMatches = this.passwordEncoder.matches(authCandidateRequestDTO.password(), candidate.getPassword());

    if (!passwordMatches) {
      throw new AuthenticationException();
    }

    Algorithm algorithm = Algorithm.HMAC256(secretkey);
    var token = JWT.create().withIssuer("javagas").withSubject(candidate.getId().toString())
        .withClaim("roles", Arrays.asList("CANDIDATE"))
        .withExpiresAt(Instant.now().plus(java.time.Duration.ofMinutes(10))).sign(algorithm);

    var AuthCandidateResponse = AuthCandidateResponseDTO.builder().acess_token(token).build();

    return AuthCandidateResponse;
  }
}
