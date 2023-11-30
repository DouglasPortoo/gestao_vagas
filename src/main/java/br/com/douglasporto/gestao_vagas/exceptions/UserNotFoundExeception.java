package br.com.douglasporto.gestao_vagas.exceptions;

public class UserNotFoundExeception extends RuntimeException {
  public UserNotFoundExeception(){
    super("Usuario nao encontrado");
  }
}
