package br.dev.paulowolfgang.gestao_apolices.controller;

import br.dev.paulowolfgang.gestao_apolices.controller.docs.ApoliceControllerDoc;
import br.dev.paulowolfgang.gestao_apolices.dto.request.ApoliceRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.ApoliceResponseDto;
import br.dev.paulowolfgang.gestao_apolices.service.IApoliceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/apolices")
public class ApoliceController implements ApoliceControllerDoc
{
    private final IApoliceService apoliceService;

    public ApoliceController(IApoliceService apoliceService)
    {
        this.apoliceService = apoliceService;
    }

    @Override
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApoliceResponseDto> salvar(@RequestBody ApoliceRequestDto request)
    {
        ApoliceResponseDto response = apoliceService.salvar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @PutMapping(value = "/{numero}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ApoliceResponseDto> atualizar(@PathVariable String numero,
                                                        @RequestBody ApoliceRequestDto request)
    {
        return ResponseEntity.status(HttpStatus.OK).body(apoliceService.atualizar(numero, request));
    }

    @Override
    @DeleteMapping("/{numero}")
    public ResponseEntity<Void> remover(@PathVariable String numero)
    {
        apoliceService.remover(numero);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ApoliceResponseDto>> listarTodos()
    {
        return ResponseEntity.status(HttpStatus.OK).body(apoliceService.listarTodos());
    }

    @Override
    @GetMapping(value = "/numero/{numero}", produces = "application/json")
    public ResponseEntity<ApoliceResponseDto> buscarPorNumero(@PathVariable String numero)
    {
        return ResponseEntity.status(HttpStatus.OK).body(apoliceService.buscarPorNumero(numero));
    }
}
