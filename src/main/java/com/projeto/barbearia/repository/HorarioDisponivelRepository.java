package com.projeto.barbearia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.barbearia.model.Funcionario;
import com.projeto.barbearia.model.HorarioDisponivel;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface que fornece operações de CRUD para horários disponíveis.
 */
public interface HorarioDisponivelRepository extends JpaRepository<HorarioDisponivel, Long> {
    List<HorarioDisponivel> findByBarbeiroAndInicioBeforeAndFimAfter(Funcionario barbeiro, LocalDateTime inicio, LocalDateTime fim);
    List<HorarioDisponivel> findByBarbeiroId(Long barbeiroId);
}