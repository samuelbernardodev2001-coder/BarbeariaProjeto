package com.projeto.barbearia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.barbearia.model.Avaliacao;
import com.projeto.barbearia.repository.AvaliacaoRepository;

/**
 * Serviço para registrar e listar avaliações feitas aos barbeiros.
 */
@Service
public class AvaliacaoService {

    private final AvaliacaoRepository repo;

    public AvaliacaoService(AvaliacaoRepository repo) {
        this.repo = repo;
    }

    public Avaliacao salvar(Avaliacao a) {
        return repo.save(a);
    }

    public List<Avaliacao> porBarbeiro(Long id) {
        return repo.findByBarbeiroId(id);
    }
}