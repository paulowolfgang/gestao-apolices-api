package br.dev.paulowolfgang.gestao_apolices.controller;

import br.dev.paulowolfgang.gestao_apolices.entity.Apolice;
import br.dev.paulowolfgang.gestao_apolices.service.IApoliceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/apolices")
public class ApoliceController {

    private final IApoliceService apoliceService;

    public ApoliceController(IApoliceService apoliceService) {
        this.apoliceService = apoliceService;
    }

    @PostMapping
    public ResponseEntity<Apolice> criar(@RequestBody Apolice apolice)
    {
        Apolice novaApolice = apoliceService.salvar(apolice);
        return ResponseEntity.ok(novaApolice);
    }

    @GetMapping
    public ResponseEntity<List<Apolice>> listarTodas()
    {
        List<Apolice> apolices = apoliceService.listarTodas();
        return ResponseEntity.ok(apolices);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apolice> buscarPorId(@PathVariable Long id){
        return apoliceService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());

    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<Apolice> buscarPorNumero(@PathVariable String numero) {
        return apoliceService.buscarPorNumero(numero)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apolice> atualizar(@PathVariable Long id, @RequestBody Apolice apoliceAtualizada) {
        try {
            Apolice apolice = apoliceService.atualizar(id, apoliceAtualizada);
            return ResponseEntity.ok(apolice);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        try {
            apoliceService.remover(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.notFound().build();
        }
    }
}
