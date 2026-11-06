package com.projeto.barbearia.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.projeto.barbearia.dto.FuncionarioDTO;
import com.projeto.barbearia.model.Funcionario;
import com.projeto.barbearia.service.FuncionarioService;

import jakarta.validation.Valid;

/**
 * Controller para operações de cadastro e consulta de funcionários.
 */
@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) {
        this.service = service;
    }

    /**
     * Cadastra um novo funcionário.
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public Funcionario salvar(@RequestBody @Valid FuncionarioDTO dto) {
        return service.salvar(dto);
    }

    /**
     * Retorna todos os funcionários cadastrados.
     */
    @GetMapping
    public List<Funcionario> listar() {
        return service.listar();
    }

    /**
     * Retorna um funcionário pelo ID.
     */
    @GetMapping("/{id}")
    public Funcionario buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
}