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
public class PagamentoController
{
    private final IPagamentoService pagamentoService;

    public PagamentoController(IPagamentoService pagamentoService)
    {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<PagamentoResponseDto> salvar(@RequestBody PagamentoRequestDto request)
    {
        PagamentoResponseDto response = pagamentoService.salvar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{numero}")
    public ResponseEntity<PagamentoResponseDto> atualizar(@PathVariable String numero, @RequestBody PagamentoRequestDto request)
    {
         return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.atualizar(numero, request));
    }

    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> remover(@PathVariable String numero)
    {
        pagamentoService.remover(numero);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<PagamentoResponseDto>> listarTodos()
    {
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.listarTodos());
    }

    @GetMapping("/{numero}")
    public ResponseEntity<PagamentoResponseDto> buscarPorNumero(@PathVariable String numero)
    {
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.buscarPorNumero(numero));
    }
}
