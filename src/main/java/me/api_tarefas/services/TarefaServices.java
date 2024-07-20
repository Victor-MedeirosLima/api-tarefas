package me.api_tarefas.services;

import me.api_tarefas.domain.model.Tarefa;


import java.util.List;


public interface TarefaServices {

    Tarefa criar(Tarefa novaTarefa);
    List<Tarefa> buscarTodos();
    Tarefa buscarPorId(Long id);
    Tarefa atualizar(Long id, Tarefa tarefaAtualizada);
    void deletar(Long id);


}
