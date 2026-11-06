package com.projeto.barbearia.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

/**
 * Representa uma avaliação feita por um cliente a um barbeiro.
 */
@Entity
public class Avaliacao {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int nota; // de 1 a 5

	private String comentario;

	@ManyToOne
	private Cliente cliente;

	@ManyToOne
	private Funcionario barbeiro;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getBarbeiro() {
		return barbeiro;
	}

	public void setBarbeiro(Funcionario barbeiro) {
		this.barbeiro = barbeiro;
	}
	
	

}
