package com.projeto.barbearia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.barbearia.model.Servico;

/**
 * Interface que fornece operações de CRUD para Serviço.
 */
public interface ServicoRepository extends JpaRepository<Servico, Long> {}
