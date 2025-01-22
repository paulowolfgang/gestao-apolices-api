package br.dev.paulowolfgang.gestao_apolices.controller;

import br.dev.paulowolfgang.gestao_apolices.dto.request.PagamentoRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.PagamentoResponseDto;
import br.dev.paulowolfgang.gestao_apolices.service.IPagamentoService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<PagamentoResponseDto> salvar(@RequestBody PagamentoRequestDto request){
        PagamentoResponseDto response = pagamentoService.salvar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagamentoResponseDto> buscarPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<PagamentoResponseDto>> listarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagamentoResponseDto> atualizar(@PathVariable Long id, @RequestBody PagamentoRequestDto request){
         return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        pagamentoService.remover(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
