package br.com.douglasporto.gestao_vagas.exceptions;

public class JobNotFoundExeception extends RuntimeException {
  public JobNotFoundExeception(){
    super("Vaga nao encontrada");
  }
}
