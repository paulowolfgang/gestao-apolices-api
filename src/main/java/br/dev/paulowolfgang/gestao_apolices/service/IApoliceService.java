package br.dev.paulowolfgang.gestao_apolices.service;

import br.dev.paulowolfgang.gestao_apolices.dto.request.ApoliceRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.ApoliceResponseDto;

import java.util.List;

public interface IApoliceService {
    ApoliceResponseDto salvar(ApoliceRequestDto apolice);
    ApoliceResponseDto buscarPorId(Long id);
    List<ApoliceResponseDto> listarTodos();
    ApoliceResponseDto buscarPorNumero(String numero);
    ApoliceResponseDto atualizar(Long id, ApoliceRequestDto apoliceAtualizada);
    void remover(Long id);
}
