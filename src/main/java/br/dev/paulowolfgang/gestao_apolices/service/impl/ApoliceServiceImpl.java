package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.dto.mapper.ApoliceMapper;
import br.dev.paulowolfgang.gestao_apolices.dto.request.ApoliceRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.ApoliceResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Apolice;
import br.dev.paulowolfgang.gestao_apolices.entity.Cliente;
import br.dev.paulowolfgang.gestao_apolices.exception.ApoliceNaoEncontradaException;
import br.dev.paulowolfgang.gestao_apolices.exception.ClienteNaoEncontradoException;
import br.dev.paulowolfgang.gestao_apolices.repository.IApoliceRepository;
import br.dev.paulowolfgang.gestao_apolices.repository.IClienteRepository;
import br.dev.paulowolfgang.gestao_apolices.service.IApoliceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApoliceServiceImpl implements IApoliceService {

    private final IApoliceRepository apoliceRepository;
    private final IClienteRepository clienteRepository;

    public ApoliceServiceImpl(IApoliceRepository apoliceRepository, IClienteRepository clienteRepository) {
        this.apoliceRepository = apoliceRepository;
        this.clienteRepository = clienteRepository;
    }

//    @Override
//    public ApoliceResponseDto salvar(ApoliceRequestDto request) {
//        Apolice apolice = ApoliceMapper.converter(request);
//        apolice = apoliceRepository.save(apolice);
//        return ApoliceMapper.converter(apolice);
//    }

    @Override
    public ApoliceResponseDto salvar(ApoliceRequestDto request) {
        // Log para verificar os dados de entrada
        System.out.println("Dados de entrada: " + request);

        // Busca o cliente com o ID fornecido no DTO
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com o ID: " + request.getClienteId()));

        // Log para verificar se o cliente foi encontrado
        System.out.println("Cliente encontrado: " + cliente);

        // Converte o DTO para a entidade Apolice
        Apolice apolice = ApoliceMapper.converter(request);

        // Log para verificar a apólice após a conversão
        System.out.println("Apólice após conversão: " + apolice);

        // Associa o cliente à apólice antes de salvar
        apolice.setCliente(cliente);

        // Log para verificar a apólice antes de salvar
        System.out.println("Apólice antes de salvar: " + apolice);

        // Salva a apólice
        apolice = apoliceRepository.save(apolice);

        // Log para verificar a apólice após salvar
        System.out.println("Apólice após salvar: " + apolice);

        // Retorna a apólice convertida para DTO
        return ApoliceMapper.converter(apolice);
    }

    @Override
    public ApoliceResponseDto buscarPorId(Long id) {
        Apolice apolice = apoliceRepository.findById(id)
                .orElseThrow(() -> new ApoliceNaoEncontradaException(String.format("Apólice não encontrada para o ID: " + id)));
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
                .orElseThrow(() -> new ApoliceNaoEncontradaException(String.format("Apólice não encontrada para o número: " + numero)));
        return ApoliceMapper.converter(apolice);
    }

    @Override
    public ApoliceResponseDto atualizar(Long id, ApoliceRequestDto apoliceAtualizada) {
        Apolice apolice = apoliceRepository.findById(id)
                .orElseThrow(() -> new ApoliceNaoEncontradaException(String.format("Apólice não encontrada para o ID: " + id)));
        ApoliceMapper.copiarParaPropriedades(apoliceAtualizada, apolice);
        apolice = apoliceRepository.save(apolice);
        return ApoliceMapper.converter(apolice);
    }

    @Override
    public void remover(Long id) {
        if(!apoliceRepository.existsById(id)){
            throw new ApoliceNaoEncontradaException(String.format("Apólice não encontrada para o ID: " + id));
        }
        apoliceRepository.deleteById(id);
    }
}
