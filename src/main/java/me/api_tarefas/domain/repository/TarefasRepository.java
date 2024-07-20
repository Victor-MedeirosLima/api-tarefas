package me.api_tarefas.domain.repository;

import me.api_tarefas.domain.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefa, Long> {

}
