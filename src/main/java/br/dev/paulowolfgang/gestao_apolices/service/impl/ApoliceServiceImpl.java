package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.dto.mapper.ApoliceMapper;
import br.dev.paulowolfgang.gestao_apolices.dto.request.ApoliceRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.ApoliceResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Apolice;
import br.dev.paulowolfgang.gestao_apolices.repository.IApoliceRepository;
import br.dev.paulowolfgang.gestao_apolices.service.IApoliceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApoliceServiceImpl implements IApoliceService {

    private final IApoliceRepository apoliceRepository;

    public ApoliceServiceImpl(IApoliceRepository apoliceRepository) {
        this.apoliceRepository = apoliceRepository;
    }

    @Override
    public ApoliceResponseDto salvar(ApoliceRequestDto request) {
        Apolice apolice = ApoliceMapper.converter(request);
        apolice = apoliceRepository.save(apolice);
        return ApoliceMapper.converter(apolice);
    }

    @Override
    public ApoliceResponseDto buscarPorId(Long id) {
        Apolice apolice = apoliceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Apólice não encontrada para o ID: " + id));
        return ApoliceMapper.converter(apolice);
    }

    @Override
    public List<ApoliceResponseDto> listarTodos() {
        List<Apolice> apolices = apoliceRepository.findAll();
        return apolices.stream()
                .map(ApoliceMapper::converter)
                .toList();
    }

    @Override
    public ApoliceResponseDto buscarPorNumero(String numero) {
        Apolice apolice = apoliceRepository.findByNumero(numero)
                .orElseThrow(() -> new IllegalArgumentException("Apólice não encontrada para o número: " + numero));
        return ApoliceMapper.converter(apolice);
    }

    @Override
    public ApoliceResponseDto atualizar(Long id, ApoliceRequestDto apoliceAtualizada) {
        Apolice apolice = apoliceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Apólice não encontrada para o ID: " + id));
        ApoliceMapper.copiarParaPropriedades(apoliceAtualizada, apolice);
        apolice = apoliceRepository.save(apolice);
        return ApoliceMapper.converter(apolice);
    }

    @Override
    public void remover(Long id) {
        if(!apoliceRepository.existsById(id)){
            throw new IllegalArgumentException("Apólice não encontrada para o ID: " + id);
        }
        apoliceRepository.deleteById(id);
    }
}
