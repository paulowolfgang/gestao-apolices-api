package br.dev.paulowolfgang.gestao_apolices.service;

import br.dev.paulowolfgang.gestao_apolices.entity.Pagamento;

import java.util.List;
import java.util.Optional;

public interface IPagamentoService {

    Pagamento salvar(Pagamento pagamento);
    Optional<Pagamento> buscarPorId(Long id);
    List<Pagamento> listarTodos();
    Pagamento atualizar(Long id, Pagamento pagamentoAtualizado);
    void remover(Long id);
}
