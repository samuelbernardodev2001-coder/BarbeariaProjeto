package com.projeto.barbearia.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.barbearia.model.Agendamento;
import com.projeto.barbearia.model.Cliente;
import com.projeto.barbearia.model.Presenca;
import com.projeto.barbearia.repository.AgendamentoRepository;
import com.projeto.barbearia.repository.ClienteRepository;
import com.projeto.barbearia.repository.PresencaRepository;

/**
 * Serviço que controla a entrada e saída dos clientes na loja.
 */
@Service
public class PresencaService {

    private static final int LIMITE_CLIENTES = 10;

    private final PresencaRepository repo;
    private final ClienteRepository clienteRepo;
    private final AgendamentoRepository agendamentoRepo;

    public PresencaService(PresencaRepository repo, ClienteRepository clienteRepo, AgendamentoRepository agendamentoRepo) {
        this.repo = repo;
        this.clienteRepo = clienteRepo;
        this.agendamentoRepo = agendamentoRepo;
    }

    public String checkIn(Long clienteId, Long agendamentoId) {
        if (repo.countByAtivoTrue() >= LIMITE_CLIENTES) {
            return "Capacidade máxima atingida.";
        }

        Cliente cliente = clienteRepo.findById(clienteId).orElseThrow();
        Agendamento agendamento = agendamentoRepo.findById(agendamentoId).orElseThrow();

        Presenca p = new Presenca();
        p.setCliente(cliente);
        p.setAgendamento(agendamento);
        p.setEntrada(LocalDateTime.now());
        p.setAtivo(true);
        repo.save(p);

        return "Check-in realizado com sucesso.";
    }

    public String checkOut(Long presencaId) {
        Presenca p = repo.findById(presencaId).orElseThrow();
        p.setSaida(LocalDateTime.now());
        p.setAtivo(false);
        repo.save(p);
        return "Check-out realizado.";
    }

    public List<Presenca> listarPresentes() {
        return repo.findByAtivoTrue();
    }
}