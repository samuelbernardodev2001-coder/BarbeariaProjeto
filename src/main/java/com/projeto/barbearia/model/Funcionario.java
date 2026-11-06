package com.projeto.barbearia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

/**
 * Entidade que representa um funcionário (atendente, barbeiro ou admin).
 */
@Entity
public class Funcionario extends Pessoa {

	@Enumerated(EnumType.STRING)
    private Cargo cargo;

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}
	
	
}
