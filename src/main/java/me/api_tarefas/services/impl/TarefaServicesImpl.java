package me.api_tarefas.services.impl;

import me.api_tarefas.domain.model.Tarefa;
import me.api_tarefas.domain.repository.TarefasRepository;
import me.api_tarefas.services.TarefaServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TarefaServicesImpl implements TarefaServices {

    private final TarefasRepository tarefasRepository;

    public TarefaServicesImpl(TarefasRepository tarefasRepository) {
        this.tarefasRepository = tarefasRepository;
    }

    @Override
    public Tarefa criar(Tarefa novaTarefa) {
        return tarefasRepository.save(novaTarefa);
    }

    @Override
    public List<Tarefa> buscarTodos() {
        return tarefasRepository.findAll();
    }

    @Override
    public Tarefa buscarPorId(Long id) {
        return tarefasRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Tarefa atualizar(Long id, Tarefa tarefaAtualizada ) {
        Optional<Tarefa> tarefabanco = tarefasRepository.findById(id);
        if(!tarefabanco.isPresent()){
            throw new RuntimeException("Tarefa nao achada");
        }

        Tarefa tarefa = tarefabanco.get();

        tarefa.setNome(tarefaAtualizada.getNome());
        tarefa.setDescricao(tarefaAtualizada.getDescricao());
        tarefa.setCompleta(tarefaAtualizada.isCompleta());

        return tarefasRepository.save(tarefa);
    }

    @Override
    public void deletar(Long id) {
        Optional<Tarefa> tarefa = tarefasRepository.findById(id);
        if(!tarefa.isPresent()){

        }
        tarefasRepository.deleteById(id);

    }
}
