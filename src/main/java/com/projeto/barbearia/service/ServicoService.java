package com.projeto.barbearia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.barbearia.model.Servico;
import com.projeto.barbearia.repository.ServicoRepository;

/**
 * Serviço para manipulação dos tipos de corte (serviços).
 */
@Service
public class ServicoService {

    private final ServicoRepository repo;

    public ServicoService(ServicoRepository repo) {
        this.repo = repo;
    }

    public Servico salvar(Servico servico) {
        return repo.save(servico);
    }

    public List<Servico> listar() {
        return repo.findAll();
    }

    public Servico buscarPorId(Long id) {
        return repo.findById(id).orElseThrow();
    }
}