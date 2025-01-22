package br.dev.paulowolfgang.gestao_apolices.service;

import br.dev.paulowolfgang.gestao_apolices.dto.request.PagamentoRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.PagamentoResponseDto;

import java.util.List;

public interface IPagamentoService {
    PagamentoResponseDto salvar(PagamentoRequestDto pagamento);
    PagamentoResponseDto buscarPorId(Long id);
    List<PagamentoResponseDto> listarTodos();
    PagamentoResponseDto atualizar(Long id, PagamentoRequestDto pagamentoAtualizado);
    void remover(Long id);
}
