package com.projeto.barbearia.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.projeto.barbearia.model.Agendamento;
import com.projeto.barbearia.repository.AgendamentoRepository;
import com.projeto.barbearia.repository.ServicoRepository;

/**
 * Serviço para gerar dados estatísticos simples da barbearia.
 */
@Service
public class DashboardService {

    private final AgendamentoRepository agendamentoRepo;
    private final ServicoRepository servicoRepo;

    public DashboardService(AgendamentoRepository agendamentoRepo, ServicoRepository servicoRepo) {
        this.agendamentoRepo = agendamentoRepo;
        this.servicoRepo = servicoRepo;
    }

    public Map<String, Object> getResumo() {
        List<Agendamento> ags = agendamentoRepo.findAll();

        BigDecimal total = ags.stream()
                .map(a -> a.getServico().getPreco())
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, Object> resumo = new HashMap<>();
        resumo.put("totalAgendamentos", ags.size());
        resumo.put("receitaTotal", total);
        resumo.put("servicosDisponiveis", servicoRepo.findAll().size());
        return resumo;
    }
}