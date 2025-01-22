package br.dev.paulowolfgang.gestao_apolices.controller;

import br.dev.paulowolfgang.gestao_apolices.dto.request.ApoliceRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.ApoliceResponseDto;
import br.dev.paulowolfgang.gestao_apolices.service.IApoliceService;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApoliceResponseDto> salvar(@RequestBody ApoliceRequestDto request){
        ApoliceResponseDto response = apoliceService.salvar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApoliceResponseDto> buscarPorId(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(apoliceService.buscarPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<ApoliceResponseDto>> listarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(apoliceService.listarTodos());
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<ApoliceResponseDto> buscarPorNumero(@PathVariable String numero) {
        return ResponseEntity.status(HttpStatus.OK).body(apoliceService.buscarPorNumero(numero));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApoliceResponseDto> atualizar(@PathVariable Long id, @RequestBody ApoliceRequestDto request) {
        return ResponseEntity.status(HttpStatus.OK).body(apoliceService.atualizar(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {
        apoliceService.remover(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
