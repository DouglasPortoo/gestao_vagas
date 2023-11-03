package br.com.douglasporto.gestao_vagas.modules.company.useCases;

import java.time.Instant;

import javax.naming.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.douglasporto.gestao_vagas.modules.company.dto.AuthcompanyDto;
import br.com.douglasporto.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUsecase {

  @Value("${security.token.secret}")
  private String secretkey;

  @Autowired
  private CompanyRepository companyRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  public String execute(AuthcompanyDto authcompanyDto) throws AuthenticationException {
    var company = this.companyRepository.findByUsername(authcompanyDto.getUsername()).orElseThrow(
        () -> {
          throw new UsernameNotFoundException("company not found");
        });

    var passwordMatches = this.passwordEncoder.matches(authcompanyDto.getPassword(), company.getPassword());

    if (!passwordMatches) {
      throw new AuthenticationException();
    }
    Algorithm algorithm = Algorithm.HMAC256(secretkey);
    var token = JWT.create().withIssuer("javagas").withExpiresAt(Instant.now().plus(java.time.Duration.ofHours(2)))
        .withSubject(company.getId().toString()).sign(algorithm);
    return token;
  };

}
