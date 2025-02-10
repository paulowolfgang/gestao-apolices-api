package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.dto.mapper.ClienteMapper;
import br.dev.paulowolfgang.gestao_apolices.dto.request.ClienteRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.ClienteResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Cliente;
import br.dev.paulowolfgang.gestao_apolices.entity.Usuario;
import br.dev.paulowolfgang.gestao_apolices.exception.ClienteNaoEncontradoException;
import br.dev.paulowolfgang.gestao_apolices.repository.IClienteRepository;
import br.dev.paulowolfgang.gestao_apolices.service.IClienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements IClienteService {

    private final IClienteRepository clienteRepository;

    public ClienteServiceImpl(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public ClienteResponseDto salvar(ClienteRequestDto request) {

        // Simulação de um usuário temporário com ID fixo
        Long idUsuarioSimulado = 1L;
        Usuario usuarioSimulado = new Usuario();
        usuarioSimulado.setId(idUsuarioSimulado);

        Cliente cliente = ClienteMapper.converter(request);
        cliente.setUsuario(usuarioSimulado);
        cliente = clienteRepository.save(cliente);
        return ClienteMapper.converter(cliente);
    }

    @Override
    public ClienteResponseDto buscarPorId(Long id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(String.format("Cliente não encontrado para o ID: " + id)));
        return ClienteMapper.converter(cliente);
    }

    @Override
    public List<ClienteResponseDto> listarTodos() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(ClienteMapper::converter)
                .toList();
    }

    @Override
    public ClienteResponseDto atualizar(Long id, ClienteRequestDto clienteRequestDto) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException(String.format("Cliente não encontrado para o ID: " + id)));
        ClienteMapper.copiarParaPropriedades(clienteRequestDto, cliente);
        cliente = clienteRepository.save(cliente);

        return ClienteMapper.converter(cliente);
    }

    @Override
    public void remover(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new ClienteNaoEncontradoException(String.format("Cliente não encontrado para o ID: " + id));
        }
        clienteRepository.deleteById(id);
    }
}
