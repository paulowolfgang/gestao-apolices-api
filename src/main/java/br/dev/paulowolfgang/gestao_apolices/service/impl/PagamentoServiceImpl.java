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
        return null;
    }

    @Override
    public Optional<Pagamento> buscarPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Pagamento> listarTodos() {
        return null;
    }

    @Override
    public Pagamento atualizar(Long id, Pagamento pagamentoAtualizado) {
        return null;
    }

    @Override
    public void remover(Long id) {

    }
}
