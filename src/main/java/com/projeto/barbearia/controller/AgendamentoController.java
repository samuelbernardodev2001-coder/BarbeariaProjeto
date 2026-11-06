package com.projeto.barbearia.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.projeto.barbearia.model.Agendamento;
import com.projeto.barbearia.service.AgendamentoService;

/**
 * Controller para manipulação dos agendamentos de clientes.
 */
@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(AgendamentoService service) {
        this.service = service;
    }

    /**
     * Cria um novo agendamento para um cliente.
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Agendamento salvar(@RequestBody Agendamento agendamento) {
        return service.salvar(agendamento);
    }

    /**
     * Retorna todos os agendamentos realizados.
     */
    @GetMapping
    public List<Agendamento> listar() {
        return service.listar();
    }

    /**
     * Retorna agendamentos de um cliente específico.
     */
    @GetMapping("/cliente/{clienteId}")
    public List<Agendamento> porCliente(@PathVariable Long clienteId) {
        return service.porCliente(clienteId);
    }

    /**
     * Realiza o check-in de um agendamento.
     */
    @PatchMapping("/{id}/check-in")
    public void checkIn(@PathVariable Long id) {
        service.checkIn(id);
    }

    /**
     * Reagenda um agendamento.
     */
    @PatchMapping("/{id}/reagendar")
    public void reagendar(@PathVariable Long id, @RequestBody String novaDataHora) {
        service.reagendar(id, LocalDateTime.parse(novaDataHora));
    }

    /**
     * Cancela um agendamento.
     */
    @DeleteMapping("/{id}")
    public void cancelar(@PathVariable Long id) {
        service.cancelar(id);
    }
}