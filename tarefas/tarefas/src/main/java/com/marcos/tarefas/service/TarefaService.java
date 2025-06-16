package com.marcos.tarefas.service;

import com.marcos.tarefas.model.Tarefa;
import com.marcos.tarefas.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TarefaService {

    private final TarefaRepository repository;

    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    public List<Tarefa> listarTodas() {
        return repository.findAll();
    }

    public Optional<Tarefa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Tarefa salvar(Tarefa tarefa) {
        return repository.save(tarefa);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Tarefa atualizar(Long id, Tarefa novaTarefa) {
        return repository.findById(id)
            .map(tarefa -> {
                tarefa.setTitulo(novaTarefa.getTitulo());
                tarefa.setDescricao(novaTarefa.getDescricao());
                tarefa.setConcluida(novaTarefa.isConcluida());
                return repository.save(tarefa);
            }).orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));
    }
}
