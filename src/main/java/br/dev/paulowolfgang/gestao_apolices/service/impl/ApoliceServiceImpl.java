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
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ApoliceServiceImpl implements IApoliceService
{
    private final IApoliceRepository apoliceRepository;
    private final IClienteRepository clienteRepository;

    public ApoliceServiceImpl(IApoliceRepository apoliceRepository, IClienteRepository clienteRepository)
    {
        this.apoliceRepository = apoliceRepository;
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public ApoliceResponseDto salvar(ApoliceRequestDto request)
    {
        Cliente cliente = clienteRepository.findById(request.getClienteId())
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado com o ID: " + request.getClienteId()));
        Apolice apolice = ApoliceMapper.converter(request);
        apolice.setCliente(cliente);
        apolice = apoliceRepository.save(apolice);

        return ApoliceMapper.converter(apolice);
    }

    @Override
    @Transactional
    public ApoliceResponseDto atualizar(String numero, ApoliceRequestDto apoliceAtualizada)
    {
        Apolice apolice = apoliceRepository.findByNumero(numero)
                .orElseThrow(() -> new ApoliceNaoEncontradaException(String.format("Apólice não encontrada para o número: " + numero)));
        ApoliceMapper.copiarParaPropriedades(apoliceAtualizada, apolice);
        apolice = apoliceRepository.save(apolice);

        return ApoliceMapper.converter(apolice);
    }

    @Override
    @Transactional
    public void remover(String numero)
    {
        Apolice apolice = apoliceRepository.findByNumero(numero)
                .orElseThrow(() -> new ApoliceNaoEncontradaException(String.format("Apólice não encontrada para o número: " + numero)));

        apoliceRepository.deleteById(apolice.getId());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ApoliceResponseDto> listarTodos()
    {
        List<Apolice> apolices = apoliceRepository.findAll();

        return apolices.stream()
                .map(ApoliceMapper::converter)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ApoliceResponseDto buscarPorNumero(String numero)
    {
        Apolice apolice = apoliceRepository.findByNumero(numero)
                .orElseThrow(() -> new ApoliceNaoEncontradaException(String.format("Apólice não encontrada para o número: " + numero)));

        return ApoliceMapper.converter(apolice);
    }
}
