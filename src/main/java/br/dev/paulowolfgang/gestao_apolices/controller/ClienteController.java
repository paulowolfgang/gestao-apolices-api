package br.dev.paulowolfgang.gestao_apolices.controller;

import br.dev.paulowolfgang.gestao_apolices.controller.docs.ClienteControllerDoc;
import br.dev.paulowolfgang.gestao_apolices.dto.request.ClienteRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.ClienteResponseDto;
import br.dev.paulowolfgang.gestao_apolices.service.IClienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController implements ClienteControllerDoc
{
    private final IClienteService clienteService;

    public ClienteController(IClienteService clienteService)
    {
        this.clienteService = clienteService;
    }

    @Override
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<ClienteResponseDto> salvar(@RequestBody ClienteRequestDto request)
    {
        ClienteResponseDto response = clienteService.salvar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ClienteResponseDto> atualizar(@PathVariable Long id, @RequestBody ClienteRequestDto request)
    {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.atualizar(id, request));
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id)
    {
        clienteService.remover(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<ClienteResponseDto>> listarTodos()
    {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.listarTodos());
    }

    @Override
    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<ClienteResponseDto> buscarPorId(@PathVariable Long id)
    {
        return ResponseEntity.status(HttpStatus.OK).body(clienteService.buscarPorId(id));
    }
}
