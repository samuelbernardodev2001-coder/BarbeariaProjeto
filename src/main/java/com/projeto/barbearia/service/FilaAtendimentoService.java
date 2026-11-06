package com.projeto.barbearia.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.stereotype.Service;

import com.projeto.barbearia.model.Agendamento;

/**
 * Controla a fila de atendimento.
 */
@Service
public class FilaAtendimentoService {

    private final Queue<Agendamento> fila = new LinkedList<>();

    // Adiciona agendamento na fila
    public void entrarNaFila(Agendamento agendamento) {
        fila.add(agendamento);
    }

    // Retorna todos na fila
    public List<Agendamento> listarFila() {
        return new ArrayList<>(fila);
    }

    // Remove e retorna o próximo da fila
    public Agendamento chamarProximo() {
        return fila.poll();
    }

    public boolean estaVazia() {
        return fila.isEmpty();
    }
}