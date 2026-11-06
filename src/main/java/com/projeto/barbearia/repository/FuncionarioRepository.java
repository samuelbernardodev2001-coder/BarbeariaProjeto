package com.projeto.barbearia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projeto.barbearia.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	Funcionario findByEmail(String email); // usado pra login
}
