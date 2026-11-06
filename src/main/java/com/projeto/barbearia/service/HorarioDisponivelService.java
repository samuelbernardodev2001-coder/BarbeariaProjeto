package com.projeto.barbearia.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.barbearia.model.Funcionario;
import com.projeto.barbearia.model.HorarioDisponivel;
import com.projeto.barbearia.repository.FuncionarioRepository;
import com.projeto.barbearia.repository.HorarioDisponivelRepository;

/**
 * Controla os horários disponíveis por barbeiro.
 */
@Service
public class HorarioDisponivelService {

    private final HorarioDisponivelRepository repo;
    private final FuncionarioRepository funcRepo;

    public HorarioDisponivelService(HorarioDisponivelRepository repo, FuncionarioRepository funcRepo) {
        this.repo = repo;
        this.funcRepo = funcRepo;
    }

    public boolean isDisponivel(Long barbeiroId, LocalDateTime dataHora) {
        Funcionario barbeiro = funcRepo.findById(barbeiroId).orElseThrow();
        List<HorarioDisponivel> horarios = repo.findByBarbeiroAndInicioBeforeAndFimAfter(barbeiro, dataHora, dataHora);
        return !horarios.isEmpty();
    }

    public HorarioDisponivel salvar(HorarioDisponivel horario) {
        return repo.save(horario);
    }

    public List<HorarioDisponivel> listarPorBarbeiro(Long id) {
        return repo.findByBarbeiroId(id);
    }
}