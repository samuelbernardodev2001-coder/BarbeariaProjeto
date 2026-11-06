package com.projeto.barbearia.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.barbearia.dto.FuncionarioDTO;
import com.projeto.barbearia.model.Funcionario;
import com.projeto.barbearia.repository.FuncionarioRepository;

/**
 * Serviço para operações com funcionários.
 */
@Service
public class FuncionarioService {

    private final FuncionarioRepository repo;

    public FuncionarioService(FuncionarioRepository repo) {
        this.repo = repo;
    }

    public Funcionario salvar(FuncionarioDTO dto) {
        Funcionario func = new Funcionario();
        func.setNome(dto.getNome());
        func.setEmail(dto.getEmail());
        func.setTelefone(dto.getTelefone());
        func.setSenha(dto.getSenha());
        func.setCargo(dto.getCargo());
        return repo.save(func);
    }

    public List<Funcionario> listar() {
        return repo.findAll();
    }

    public Funcionario buscarPorId(Long id) {
        return repo.findById(id).orElseThrow();
    }
}
