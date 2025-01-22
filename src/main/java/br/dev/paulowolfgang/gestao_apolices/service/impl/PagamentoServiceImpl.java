package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.dto.mapper.PagamentoMapper;
import br.dev.paulowolfgang.gestao_apolices.dto.request.PagamentoRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.PagamentoResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Pagamento;
import br.dev.paulowolfgang.gestao_apolices.repository.IPagamentoRepository;
import br.dev.paulowolfgang.gestao_apolices.service.IPagamentoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PagamentoServiceImpl implements IPagamentoService {

    private final IPagamentoRepository pagamentoRepository;

    public PagamentoServiceImpl(IPagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @Override
    public PagamentoResponseDto salvar(PagamentoRequestDto request) {
        Pagamento pagamento = PagamentoMapper.converter(request);
        pagamento = pagamentoRepository.save(pagamento);
        return PagamentoMapper.converter(pagamento);
    }

    @Override
    public PagamentoResponseDto buscarPorId(Long id) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado para o ID: " + id));
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
    public PagamentoResponseDto atualizar(Long id, PagamentoRequestDto pagamentoAtualizado) {
        Pagamento pagamento = pagamentoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pagamento não encontrado para o ID: " + id));
        PagamentoMapper.copiarParaPropriedades(pagamentoAtualizado, pagamento);
        return PagamentoMapper.converter(pagamento);
    }

    @Override
    public void remover(Long id) {
        if(!pagamentoRepository.existsById(id)){
            throw new IllegalArgumentException("Pagamento não encontrado para o ID: " + id);
        }
        pagamentoRepository.deleteById(id);
    }
}
