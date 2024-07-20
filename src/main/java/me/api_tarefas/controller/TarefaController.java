package me.api_tarefas.controller;


import me.api_tarefas.domain.model.Tarefa;
import me.api_tarefas.services.TarefaServices;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

    private final TarefaServices tarefaServices;

    public TarefaController(TarefaServices tarefaServices) {
        this.tarefaServices = tarefaServices;
    }


    @PostMapping
    public ResponseEntity<Tarefa> criar(@RequestBody Tarefa novaTarefa){

        var tarefaCriada = tarefaServices.criar(novaTarefa);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tarefaCriada.getId())
                .toUri();

        return ResponseEntity.created(location).body(tarefaCriada);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscarId(@PathVariable Long id){
        var  tarefa = tarefaServices.buscarPorId(id);
        return ResponseEntity.ok(tarefa);
    }

    @GetMapping
    public List<Tarefa> buscartodos(){
        return tarefaServices.buscarTodos();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> updateTask(@PathVariable Long id, @RequestBody Tarefa tarefanova) {
        try {
            Tarefa tarefaAtualizada = tarefaServices.atualizar(id, tarefanova);
            return ResponseEntity.ok(tarefaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPorId(@PathVariable Long id){

        tarefaServices.deletar(id);

        return ResponseEntity.noContent().build();

    }



}
