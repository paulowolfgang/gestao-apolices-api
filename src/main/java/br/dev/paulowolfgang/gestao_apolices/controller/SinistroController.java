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
public class SinistroController {

    private final ISinistroService sinistroService;

    public SinistroController(ISinistroService sinistroService) {
        this.sinistroService = sinistroService;
    }

    @PostMapping
    public ResponseEntity<SinistroResponseDto> salvar(@RequestBody SinistroRequestDto request){
        SinistroResponseDto response = sinistroService.salvar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SinistroResponseDto> buscarPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(sinistroService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<SinistroResponseDto>> listarTodos(){
         return ResponseEntity.status(HttpStatus.OK).body(sinistroService.listarTodos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SinistroResponseDto> atualizar(@PathVariable Long id, @RequestBody SinistroRequestDto request){
        return ResponseEntity.status(HttpStatus.OK).body(sinistroService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id){
        sinistroService.remover(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
