package br.dev.paulowolfgang.gestao_apolices.controller;

import br.dev.paulowolfgang.gestao_apolices.entity.Pagamento;
import br.dev.paulowolfgang.gestao_apolices.service.IPagamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pagamentos")
public class PagamentoController {

    private final IPagamentoService pagamentoService;

    public PagamentoController(IPagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<Pagamento> criar(@RequestBody Pagamento pagamento){
        Pagamento novoPagamento = pagamentoService.salvar(pagamento);
        return ResponseEntity.ok(novoPagamento);
    }

    @GetMapping
    public ResponseEntity<List<Pagamento>> listarTodos(){
        List<Pagamento> pagamentos = pagamentoService.listarTodos();
        return ResponseEntity.ok(pagamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pagamento> buscarPorId(@PathVariable Long id){
        return pagamentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pagamento> atualizar(@PathVariable Long id, @RequestBody Pagamento pagamentoAtualizado){
        try {
            Pagamento pagamento = pagamentoService.atualizar(id, pagamentoAtualizado);
            return ResponseEntity.ok(pagamento);
        } catch(IllegalArgumentException ex){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        try {
            pagamentoService.remover(id);
            return ResponseEntity.noContent().build();
        } catch(IllegalArgumentException ex){
            return ResponseEntity.notFound().build();
        }
    }
}
