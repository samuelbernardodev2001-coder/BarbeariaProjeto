package com.projeto.barbearia.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.barbearia.model.Presenca;
import com.projeto.barbearia.service.PresencaService;

/**
 * Controller para gerenciar a presença de clientes na loja.
 */
@RestController
@RequestMapping("/api/presenca")
public class PresencaController {

    private final PresencaService service;

    public PresencaController(PresencaService service) {
        this.service = service;
    }

    /**
     * Realiza o check-in de um cliente.
     */
    @PostMapping("/check-in")
    public Map<String, String> checkIn(@RequestParam Long clienteId, @RequestParam Long agendamentoId) {
        String msg = service.checkIn(clienteId, agendamentoId);
        return Map.of("mensagem", msg);
    }

    /**
     * Realiza o check-out de um cliente.
     */
    @PostMapping("/check-out/{id}")
    public Map<String, String> checkOut(@PathVariable Long id) {
        String msg = service.checkOut(id);
        return Map.of("mensagem", msg);
    }

    /**
     * Lista os clientes presentes na loja.
     */
    @GetMapping("/ativos")
    public List<Presenca> ativos() {
        return service.listarPresentes();
    }
}