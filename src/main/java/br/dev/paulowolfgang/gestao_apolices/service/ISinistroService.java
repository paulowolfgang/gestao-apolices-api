package br.dev.paulowolfgang.gestao_apolices.service;

import br.dev.paulowolfgang.gestao_apolices.dto.request.SinistroRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.SinistroResponseDto;

import java.util.List;

public interface ISinistroService
{
    SinistroResponseDto salvar(SinistroRequestDto request);
    SinistroResponseDto atualizar(String numero, SinistroRequestDto sinistroAtualizado);
    void remover(String numero);
    List<SinistroResponseDto> listarTodos();
    SinistroResponseDto buscarPorNumero(String numero);
}
