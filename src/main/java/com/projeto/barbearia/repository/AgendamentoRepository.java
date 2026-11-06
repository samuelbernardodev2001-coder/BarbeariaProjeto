package com.projeto.barbearia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.barbearia.model.Agendamento;

/**
 * Interface que fornece operações de CRUD para Agendamento.
 */
public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    List<Agendamento> findByClienteId(Long clienteId); // histórico do cliente
}