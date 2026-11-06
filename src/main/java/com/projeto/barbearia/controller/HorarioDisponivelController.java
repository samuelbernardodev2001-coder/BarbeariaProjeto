package com.projeto.barbearia.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projeto.barbearia.model.HorarioDisponivel;
import com.projeto.barbearia.service.HorarioDisponivelService;

/**
 * Controller para gerenciar horários disponíveis de barbeiros.
 */
@RestController
@RequestMapping("/api/horarios")
public class HorarioDisponivelController {

    private final HorarioDisponivelService service;

    public HorarioDisponivelController(HorarioDisponivelService service) {
        this.service = service;
    }

    /**
     * Cadastra um novo horário disponível.
     */
    @PreAuthorize("hasAuthority('BARBEIRO')")
    @PostMapping
    public HorarioDisponivel salvar(@RequestBody HorarioDisponivel horario) {
        return service.salvar(horario);
    }

    /**
     * Lista horários disponíveis de um barbeiro.
     */
    @GetMapping("/barbeiro/{barbeiroId}")
    public List<HorarioDisponivel> listar(@PathVariable Long barbeiroId) {
        return service.listarPorBarbeiro(barbeiroId);
    }
}