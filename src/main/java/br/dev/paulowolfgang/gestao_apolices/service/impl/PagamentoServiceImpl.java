package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.dto.mapper.PagamentoMapper;
import br.dev.paulowolfgang.gestao_apolices.dto.request.PagamentoRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.PagamentoResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Apolice;
import br.dev.paulowolfgang.gestao_apolices.entity.Pagamento;
import br.dev.paulowolfgang.gestao_apolices.exception.ApoliceNaoEncontradaException;
import br.dev.paulowolfgang.gestao_apolices.exception.PagamentoNaoEncontradoException;
import br.dev.paulowolfgang.gestao_apolices.repository.IApoliceRepository;
import br.dev.paulowolfgang.gestao_apolices.repository.IPagamentoRepository;
import br.dev.paulowolfgang.gestao_apolices.service.IPagamentoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoServiceImpl implements IPagamentoService {

    private final IPagamentoRepository pagamentoRepository;
    private final IApoliceRepository apoliceRepository;

    public PagamentoServiceImpl(IPagamentoRepository pagamentoRepository, IApoliceRepository apoliceRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.apoliceRepository = apoliceRepository;
    }

    @Override
    public PagamentoResponseDto salvar(PagamentoRequestDto request) {
        Apolice apolice = apoliceRepository.findByNumero(request.getApoliceNumero())
                .orElseThrow(() -> new ApoliceNaoEncontradaException("Apólice não encontrada com o número: " + request.getApoliceNumero()));
        Pagamento pagamento = PagamentoMapper.converter(request);
        pagamento.setApolice(apolice);
        pagamento = pagamentoRepository.save(pagamento);
        return PagamentoMapper.converter(pagamento);
    }

    @Override
    public PagamentoResponseDto buscarPorNumero(String numero) {
        Pagamento pagamento = pagamentoRepository.findByNumero(numero)
                .orElseThrow(() -> new PagamentoNaoEncontradoException(String.format("Pagamento não encontrado para o número: " + numero)));
        return PagamentoMapper.converter(pagamento);
    }

    @Override
    public List<PagamentoResponseDto> listarTodos() {
        List<Pagamento> pagamentos = pagamentoRepository.findAll();
        return pagamentos.stream()
                .map(PagamentoMapper::converter)
                .toList();
    }

    @Override
    public PagamentoResponseDto atualizar(String numero, PagamentoRequestDto pagamentoAtualizado) {
        Pagamento pagamento = pagamentoRepository.findByNumero(numero)
                .orElseThrow(() -> new PagamentoNaoEncontradoException(String.format("Pagamento não encontrado para o número: " + numero)));
        PagamentoMapper.copiarParaPropriedades(pagamentoAtualizado, pagamento);
        return PagamentoMapper.converter(pagamento);
    }

    @Override
    public void remover(String numero) {
        Pagamento pagamento = pagamentoRepository.findByNumero(numero)
                .orElseThrow(() -> new PagamentoNaoEncontradoException(String.format("Pagamento não encontrado para o número: " + numero)));
        pagamentoRepository.deleteById(pagamento.getId());
    }
}
