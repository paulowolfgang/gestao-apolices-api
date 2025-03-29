package br.dev.paulowolfgang.gestao_apolices.controller;

import br.dev.paulowolfgang.gestao_apolices.dto.request.SinistroRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.SinistroResponseDto;
import br.dev.paulowolfgang.gestao_apolices.service.ISinistroService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/sinistros")
public class SinistroController
{

    private final ISinistroService sinistroService;

    public SinistroController(ISinistroService sinistroService)
    {
        this.sinistroService = sinistroService;
    }

    @PostMapping
    public ResponseEntity<SinistroResponseDto> salvar(@RequestBody SinistroRequestDto request)
    {
        SinistroResponseDto response = sinistroService.salvar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{numero}")
    public ResponseEntity<SinistroResponseDto> atualizar(@PathVariable String numero, @RequestBody SinistroRequestDto request)
    {
        return ResponseEntity.status(HttpStatus.OK).body(sinistroService.atualizar(numero, request));
    }

    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> remover(@PathVariable String numero)
    {
        sinistroService.remover(numero);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<SinistroResponseDto>> listarTodos()
    {
        return ResponseEntity.status(HttpStatus.OK).body(sinistroService.listarTodos());
    }

    @GetMapping("/{numero}")
    public ResponseEntity<SinistroResponseDto> buscarPorNumero(@PathVariable String numero)
    {
        return ResponseEntity.status(HttpStatus.OK).body(sinistroService.buscarPorNumero(numero));
    }
}
