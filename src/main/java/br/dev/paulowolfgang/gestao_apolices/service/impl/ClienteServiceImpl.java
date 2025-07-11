package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.dto.mapper.ClienteMapper;
import br.dev.paulowolfgang.gestao_apolices.dto.request.ClienteRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.ClienteResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Cliente;
import br.dev.paulowolfgang.gestao_apolices.entity.Usuario;
import br.dev.paulowolfgang.gestao_apolices.exception.ClienteNaoEncontradoException;
import br.dev.paulowolfgang.gestao_apolices.repository.IClienteRepository;
import br.dev.paulowolfgang.gestao_apolices.service.IClienteService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService
{
    private final IClienteRepository clienteRepository;

    public ClienteServiceImpl(IClienteRepository clienteRepository)
    {
        this.clienteRepository = clienteRepository;
    }

    @Override
    @Transactional
    public ClienteResponseDto salvar(ClienteRequestDto request)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("Usuário não autenticado no sistema.");
        }

        Usuario usuarioLogado = (Usuario) authentication.getPrincipal();

        Cliente cliente = ClienteMapper.converter(request);
        cliente.setUsuario(usuarioLogado);
        cliente = clienteRepository.save(cliente);

        return ClienteMapper.converter(cliente);
    }

    @Override
    @Transactional
    public ClienteResponseDto atualizar(Long id, ClienteRequestDto clienteRequestDto)
    {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(String.format("Cliente não encontrado para o ID: " + id)));
        ClienteMapper.copiarParaPropriedades(clienteRequestDto, cliente);
        cliente = clienteRepository.save(cliente);

        return ClienteMapper.converter(cliente);
    }

    @Override
    @Transactional
    public void remover(Long id)
    {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNaoEncontradoException(String.format("Cliente não encontrado para o ID: " + id));
        }

        clienteRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDto> listarTodos()
    {
        List<Cliente> clientes = clienteRepository.findAll();

        return clientes.stream()
                .map(ClienteMapper::converter)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponseDto buscarPorId(Long id)
    {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(String.format("Cliente não encontrado para o ID: " + id)));

        return ClienteMapper.converter(cliente);
    }
}
