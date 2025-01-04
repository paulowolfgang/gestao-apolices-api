package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.entity.Pagamento;
import br.dev.paulowolfgang.gestao_apolices.repository.IPagamentoRepository;
import br.dev.paulowolfgang.gestao_apolices.service.IPagamentoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagamentoServiceImpl implements IPagamentoService {

    private final IPagamentoRepository pagamentoRepository;

    public PagamentoServiceImpl(IPagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @Override
    public Pagamento salvar(Pagamento pagamento) {
        return pagamentoRepository.save(pagamento);
    }

    @Override
    public Optional<Pagamento> buscarPorId(Long id) {
        return pagamentoRepository.findById(id);
    }

    @Override
    public List<Pagamento> listarTodos() {
        return pagamentoRepository.findAll();
    }

    @Override
    public Pagamento atualizar(Long id, Pagamento pagamentoAtualizado) {
        return pagamentoRepository.findById(id)
                .map(pagamentoExistente -> {
                    pagamentoExistente.setApolice(pagamentoAtualizado.getApolice());
                    pagamentoExistente.setValor(pagamentoAtualizado.getValor());
                    pagamentoExistente.setDataVencimento(pagamentoAtualizado.getDataVencimento());
                    pagamentoExistente.setDataPagamento(pagamentoAtualizado.getDataPagamento());
                    pagamentoExistente.setStatus(pagamentoAtualizado.getStatus());
                    return pagamentoRepository.save(pagamentoExistente);
                }).orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado para o ID: " + id));
    }

    @Override
    public void remover(Long id) {
        pagamentoRepository.deleteById(id);
    }
}
