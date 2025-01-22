package br.dev.paulowolfgang.gestao_apolices.service;

import br.dev.paulowolfgang.gestao_apolices.dto.request.SinistroRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.SinistroResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Sinistro;

import java.util.List;
import java.util.Optional;

public interface ISinistroService {
    SinistroResponseDto salvar(SinistroRequestDto request);
    SinistroResponseDto buscarPorId(Long id);
    List<SinistroResponseDto> listarTodos();
    SinistroResponseDto atualizar(Long id, SinistroRequestDto sinistroAtualizado);
    void remover(Long id);
}
