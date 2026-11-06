package com.projeto.barbearia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.barbearia.dto.ClienteDTO;
import com.projeto.barbearia.model.Cliente;
import com.projeto.barbearia.repository.ClienteRepository;

/**
 * Serviço responsável por operações relacionadas aos clientes.
 */
@Service
public class ClienteService {

    private final ClienteRepository repo;

    public ClienteService(ClienteRepository repo) {
        this.repo = repo;
    }

    public Cliente salvar(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefone(dto.getTelefone());
        cliente.setSenha(dto.getSenha()); // em sistema real, criptografar
        return repo.save(cliente);
    }

    public List<Cliente> listar() {
        return repo.findAll();
    }

    public Cliente buscarPorId(Long id) {
        return repo.findById(id).orElseThrow();
    }
}

