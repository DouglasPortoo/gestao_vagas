package br.com.douglasporto.gestao_vagas.providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Service
public class JWTProvider {

  @Value("${security.token.secret}")
  private String secretkey;

  public DecodedJWT valideteToken(String token) {
    token = token.replace("Bearer ", "");

    Algorithm algorithm = Algorithm.HMAC256(secretkey);

    try {
      var tokenDecoded = JWT.require(algorithm).build().verify(token);
      return tokenDecoded;
    } catch (JWTVerificationException ex) {
      ex.printStackTrace();
      return null;
    }
  }

}

/*
 * const [, token] = authHeader.cookie.split("token=");
 * 
 * const { role,sub: user_id } = verify(token, authConfig.secret)
 * 
 */
