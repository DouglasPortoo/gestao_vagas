package br.com.douglasporto.gestao_vagas.modules.candidate.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.douglasporto.gestao_vagas.modules.candidate.entities.CandidateEntity;

public interface CandidateRepository extends JpaRepository<CandidateEntity,UUID> {
  Optional<CandidateEntity> findByUsernameOrEmail(String username, String email);
}

/*
CHAMADA NO BANCO DE DADOS

const checkUserExists = await knex("users").where({ email });
await knex("users").insert({ name, email, password: hashedPassword });
*/
