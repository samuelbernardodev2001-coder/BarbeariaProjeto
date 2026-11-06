package com.projeto.barbearia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.barbearia.model.Cliente;

/**
 * Interface que fornece operações de CRUD para Cliente.
 */
public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	Cliente findByEmail(String email); // usado para login
}
