package br.dev.paulowolfgang.gestao_apolices.service;

import br.dev.paulowolfgang.gestao_apolices.dto.request.ClienteRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.ClienteResponseDto;

import java.util.List;

public interface IClienteService {
    ClienteResponseDto salvar(ClienteRequestDto clienteRequest);
    ClienteResponseDto buscarPorId(Long id);
    List<ClienteResponseDto> listarTodos();
    ClienteResponseDto atualizar(Long id, ClienteRequestDto clienteAtualizado);
    void remover(Long id);
}
