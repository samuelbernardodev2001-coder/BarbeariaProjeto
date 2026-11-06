package com.projeto.barbearia.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.projeto.barbearia.model.Agendamento;
import com.projeto.barbearia.repository.AgendamentoRepository;
import com.projeto.barbearia.repository.ClienteRepository;
import com.projeto.barbearia.repository.FuncionarioRepository;
import com.projeto.barbearia.repository.ServicoRepository;

/**
 * Serviço que controla os agendamentos da barbearia.
 */
@Service
public class AgendamentoService {

    private final AgendamentoRepository repo;
    private final ClienteRepository clienteRepo;
    private final FuncionarioRepository funcRepo;
    private final ServicoRepository servicoRepo;
    private final HorarioDisponivelService horarioService;

    public AgendamentoService(
        AgendamentoRepository repo,
        ClienteRepository clienteRepo,
        FuncionarioRepository funcRepo,
        ServicoRepository servicoRepo,
        HorarioDisponivelService horarioService
    ) {
        this.repo = repo;
        this.clienteRepo = clienteRepo;
        this.funcRepo = funcRepo;
        this.servicoRepo = servicoRepo;
        this.horarioService = horarioService;
    }

    public Agendamento salvar(Agendamento ag) {
        // valida se barbeiro tem disponibilidade no horário
        if (!horarioService.isDisponivel(ag.getBarbeiro().getId(), ag.getDataHora())) {
            throw new RuntimeException("Barbeiro indisponível neste horário.");
        }

        return repo.save(ag);
    }

    public Agendamento buscarPorId(Long id) {
        return repo.findById(id).orElseThrow();
    }

    public List<Agendamento> listar() {
        return repo.findAll();
    }

    public List<Agendamento> porCliente(Long clienteId) {
        return repo.findByClienteId(clienteId);
    }

    public void checkIn(Long id) {
        Agendamento ag = buscarPorId(id);
        ag.setCheckIn(true);
        repo.save(ag);
    }

    public void reagendar(Long id, LocalDateTime novaData) {
        Agendamento ag = buscarPorId(id);
        ag.setDataHora(novaData);
        repo.save(ag);
    }

    public void cancelar(Long id) {
        Agendamento ag = buscarPorId(id);
        if (ag.getDataHora().isBefore(LocalDateTime.now().plusHours(1))) {
            throw new RuntimeException("Cancelamento só com 1h de antecedência.");
        }
        ag.setStatus("CANCELADO");
        repo.save(ag);
    }
}