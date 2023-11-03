package br.com.douglasporto.gestao_vagas.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.douglasporto.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.douglasporto.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
    try {
      var result = createCandidateUseCase.execute(candidateEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }
}

/*
DA INICIO / PEGA, VALIDA E REPASSA OS PARAMETROS

class UsersController {
  async create(request, response) {
    const { name, email, password } = request.body;

    try {
      CHAMA O USE CASE
      return response.status(201).json("RETORNO DO USECASE");

    } catch (error) {
      if (error instanceof Error) {
        return response.status(400).json({ message: error.message })
      }
    }
  }
}





module.exports = UsersController;
*/

/*
const knex = require("../database/knex");
const { hash } = require("bcryptjs");

class UsersController {
  async create(request, response) {
    const { name, email, password } = request.body;

    try {
      const checkUserExists = await knex("users").where({ email });

      if (checkUserExists.length > 0) {
        throw new Error("Este e-mail já está em uso.");
      }

      const hashedPassword = await hash(password, 8);

      await knex("users").insert({ name, email, password: hashedPassword });

      return response.status(201).json("Usuario criado com sucesso.");
    } catch (error) {
      if (error instanceof Error) {
        return response.status(400).json({ message: error.message })
      }
    }
  }
}

module.exports = UsersController;
*/
