package br.dev.paulowolfgang.gestao_apolices.controller;

import br.dev.paulowolfgang.gestao_apolices.entity.Sinistro;
import br.dev.paulowolfgang.gestao_apolices.service.ISinistroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sinistros")
public class SinistroController {

    private final ISinistroService sinistroService;

    public SinistroController(ISinistroService sinistroService) {
        this.sinistroService = sinistroService;
    }

    @PostMapping
    public ResponseEntity<Sinistro> criar(@RequestBody Sinistro sinistro){
        Sinistro novoSinistro = sinistroService.salvar(sinistro);
        return ResponseEntity.ok(novoSinistro);
    }

    @GetMapping
    public ResponseEntity<List<Sinistro>> listarTodos(){
        List<Sinistro> sinistros = sinistroService.listarTodos();
        return ResponseEntity.ok(sinistros);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sinistro> buscarPorId(@PathVariable Long id){
        return sinistroService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Sinistro> atualizar(@PathVariable Long id, @RequestBody Sinistro sinistroAtualizado){
        try {
            Sinistro sinistro = sinistroService.atualizar(id, sinistroAtualizado);
            return ResponseEntity.ok(sinistro);
        } catch (IllegalArgumentException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        try{
            sinistroService.remover(id);
            return ResponseEntity.noContent().build();
        } catch(IllegalArgumentException ex){
            return ResponseEntity.notFound().build();
        }
    }
}
