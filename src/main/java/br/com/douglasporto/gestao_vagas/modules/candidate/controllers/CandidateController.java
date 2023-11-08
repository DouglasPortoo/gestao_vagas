package br.com.douglasporto.gestao_vagas.modules.candidate.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.douglasporto.gestao_vagas.modules.candidate.entities.CandidateEntity;
import br.com.douglasporto.gestao_vagas.modules.candidate.useCases.CreateCandidateUseCase;
import br.com.douglasporto.gestao_vagas.modules.candidate.useCases.ProfileCandidateUseCase;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

  @Autowired
  private CreateCandidateUseCase createCandidateUseCase;

  @Autowired
  private ProfileCandidateUseCase profileCandidateUseCase;

  @PostMapping("/")
  public ResponseEntity<Object> create(@Valid @RequestBody CandidateEntity candidateEntity) {
    try {
      var result = createCandidateUseCase.execute(candidateEntity);
      return ResponseEntity.ok().body(result);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }

  }

  @GetMapping("/")
  public ResponseEntity<Object> get(HttpServletRequest request){

    var candidateId = request.getAttribute("candidate_id")
  try {
      var profile = this.profileCandidateUseCase.execute(UUID.fromString(candidateId.toString()));
  return ResponseEntity.ok().body(profile);
    } catch (Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }
}

/*
 * const knex = require("../database/knex");
 * const { hash } = require("bcryptjs");
 * 
 * class UsersController {
 * async create(request, response) {
 * const { name, email, password } = request.body;
 * 
 * try {
 * const checkUserExists = await knex("users").where({ email });
 * 
 * if (checkUserExists.length > 0) {
 * throw new Error("Este e-mail já está em uso.");
 * }
 * 
 * const hashedPassword = await hash(password, 8);
 * 
 * await knex("users").insert({ name, email, password: hashedPassword });
 * 
 * return response.status(201).json("Usuario criado com sucesso.");
 * } catch (error) {
 * if (error instanceof Error) {
 * return response.status(400).json({ message: error.message })
 * }
 * }
 * }
 * }
 * 
 * module.exports = UsersController;
 */
