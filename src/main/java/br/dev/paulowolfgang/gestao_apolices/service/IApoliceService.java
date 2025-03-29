package br.dev.paulowolfgang.gestao_apolices.service;

import br.dev.paulowolfgang.gestao_apolices.dto.request.ApoliceRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.ApoliceResponseDto;

import java.util.List;

public interface IApoliceService
{
    ApoliceResponseDto salvar(ApoliceRequestDto apolice);
    ApoliceResponseDto atualizar(String numero, ApoliceRequestDto apoliceAtualizada);
    void remover(String id);
    List<ApoliceResponseDto> listarTodos();
    ApoliceResponseDto buscarPorNumero(String numero);
}
