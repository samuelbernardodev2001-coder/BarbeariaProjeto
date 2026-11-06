package com.projeto.barbearia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.barbearia.model.Avaliacao;

import java.util.List;

/**
 * Interface que fornece operações de CRUD para Avaliação.
 */
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    List<Avaliacao> findByBarbeiroId(Long barbeiroId); // buscar avaliações por barbeiro
}