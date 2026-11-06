package com.projeto.barbearia.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


/**
 * Representa um horário disponível cadastrado por um barbeiro.
 */
@Entity
public class HorarioDisponivel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Funcionario barbeiro;

    private LocalDateTime inicio;

    private LocalDateTime fim;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Funcionario getBarbeiro() {
		return barbeiro;
	}

	public void setBarbeiro(Funcionario barbeiro) {
		this.barbeiro = barbeiro;
	}

	public LocalDateTime getInicio() {
		return inicio;
	}

	public void setInicio(LocalDateTime inicio) {
		this.inicio = inicio;
	}

	public LocalDateTime getFim() {
		return fim;
	}

	public void setFim(LocalDateTime fim) {
		this.fim = fim;
	}
    
    

}
