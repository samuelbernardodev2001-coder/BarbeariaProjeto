package com.projeto.barbearia.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.projeto.barbearia.dto.ClienteDTO;
import com.projeto.barbearia.model.Cliente;
import com.projeto.barbearia.service.ClienteService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;

/**
 * Controller responsável pelas operações de cliente, como cadastro e consulta.
 */
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    /**
     * Cadastra um novo cliente.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@RequestBody @Valid ClienteDTO dto) {
        return service.salvar(dto);
    }

    /**
     * Retorna todos os clientes cadastrados.
     */
    @GetMapping
    public List<Cliente> listar() {
        return service.listar();
    }

    /**
     * Retorna um cliente específico pelo ID.
     */
    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }
}