package com.projeto.barbearia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.barbearia.model.Presenca;

/**
 * Interface que fornece operações de CRUD para Presença.
 */
public interface PresencaRepository extends JpaRepository<Presenca, Long> {
    List<Presenca> findByAtivoTrue(); // clientes atualmente na loja
    int countByAtivoTrue(); // número de clientes presentes
}