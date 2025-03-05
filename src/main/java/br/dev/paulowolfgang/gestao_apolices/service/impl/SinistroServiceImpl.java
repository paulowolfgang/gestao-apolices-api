package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.dto.mapper.SinistroMapper;
import br.dev.paulowolfgang.gestao_apolices.dto.request.SinistroRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.SinistroResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Apolice;
import br.dev.paulowolfgang.gestao_apolices.entity.Sinistro;
import br.dev.paulowolfgang.gestao_apolices.exception.ApoliceNaoEncontradaException;
import br.dev.paulowolfgang.gestao_apolices.exception.SinistroNaoEncontradoException;
import br.dev.paulowolfgang.gestao_apolices.repository.IApoliceRepository;
import br.dev.paulowolfgang.gestao_apolices.repository.ISinistroRepository;
import br.dev.paulowolfgang.gestao_apolices.service.ISinistroService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SinistroServiceImpl implements ISinistroService {

    private final ISinistroRepository sinistroRepository;
    private final IApoliceRepository apoliceRepository;

    public SinistroServiceImpl(ISinistroRepository sinistroRepository, IApoliceRepository apoliceRepository) {
        this.sinistroRepository = sinistroRepository;
        this.apoliceRepository = apoliceRepository;
    }

    @Override
    public SinistroResponseDto salvar(SinistroRequestDto request) {
        Apolice apolice = apoliceRepository.findByNumero(request.getApoliceNumero())
                .orElseThrow(() -> new ApoliceNaoEncontradaException("Apólice não encontrada com o número: " + request.getApoliceNumero()));
        Sinistro sinistro = SinistroMapper.converter(request);
        sinistro.setApolice(apolice);
        sinistro = sinistroRepository.save(sinistro);
        return SinistroMapper.converter(sinistro);
    }

    @Override
    public SinistroResponseDto buscarPorNumero(String numero) {
        Sinistro sinistro = sinistroRepository.findByNumero(numero)
                .orElseThrow(() -> new SinistroNaoEncontradoException(String.format("Sinistro não encontrado para o número: " + numero)));
        return SinistroMapper.converter(sinistro);
    }

    @Override
    public List<SinistroResponseDto> listarTodos() {
        List<Sinistro> sinistros = sinistroRepository.findAll();
        return sinistros.stream()
                .map(SinistroMapper::converter)
                .toList();
    }

    @Override
    public SinistroResponseDto atualizar(Long id, SinistroRequestDto sinistroAtualizado) {
        Sinistro sinistro = sinistroRepository.findById(id)
                .orElseThrow(() -> new SinistroNaoEncontradoException(String.format("Sinistro não encontrado para o ID: " + id)));
        SinistroMapper.copiarParaPropriedades(sinistroAtualizado, sinistro);
        return SinistroMapper.converter(sinistro);
    }

    @Override
    public void remover(Long id) {
        if(!sinistroRepository.existsById(id)){
            throw new SinistroNaoEncontradoException(String.format("Sinistro não encontrado para o ID: " + id));
        }
        sinistroRepository.deleteById(id);
    }
}
