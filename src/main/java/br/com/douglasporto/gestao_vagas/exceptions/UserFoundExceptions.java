package br.com.douglasporto.gestao_vagas.exceptions;

public class UserFoundExceptions extends RuntimeException {
  public UserFoundExceptions(){
    super("Usuario já existe");
  }
}
