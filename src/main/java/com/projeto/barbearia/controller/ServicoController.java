package com.projeto.barbearia.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.barbearia.model.Servico;
import com.projeto.barbearia.service.ServicoService;

import jakarta.validation.Valid;

/**
 * Controller para operações com os serviços (cortes).
 */
@RestController
@RequestMapping("/api/servicos")
public class ServicoController {

    private final ServicoService service;

    public ServicoController(ServicoService service) {
        this.service = service;
    }

    /**
     * Cadastra um novo serviço.
     */
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public Servico salvar(@RequestBody @Valid Servico servico) {
        return service.salvar(servico);
    }

    /**
     * Retorna todos os serviços disponíveis.
     */
    @GetMapping
    public List<Servico> listar() {
        return service.listar();
    }

    /**
     * Retorna um serviço pelo ID.
     */
    @GetMapping("/{id}")
    public Servico buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
    
    @GetMapping("/")
    public String index() {
        return "index.html";
    }
}