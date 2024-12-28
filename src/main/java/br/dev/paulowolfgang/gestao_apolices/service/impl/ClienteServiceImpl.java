package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.entity.Cliente;
import br.dev.paulowolfgang.gestao_apolices.entity.ClienteFisico;
import br.dev.paulowolfgang.gestao_apolices.entity.ClienteJuridico;
import br.dev.paulowolfgang.gestao_apolices.repository.IClienteRepository;
import br.dev.paulowolfgang.gestao_apolices.service.IClienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements IClienteService {

    private final IClienteRepository clienteRepository;

    public ClienteServiceImpl(IClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    @Override
    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    @Override
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente atualizar(Long id, Cliente clienteAtualizado) {
        return clienteRepository.findById(id)
                .map(clienteExistente -> {

                    clienteExistente.setEmail(clienteAtualizado.getEmail());
                    clienteExistente.setEndereco(clienteAtualizado.getEndereco());
                    clienteExistente.setTelefone(clienteAtualizado.getTelefone());

                    if (clienteExistente instanceof ClienteFisico && clienteAtualizado instanceof ClienteFisico) {
                        ClienteFisico clienteFisicoExistente = (ClienteFisico) clienteExistente;
                        ClienteFisico clienteFisicoAtualizado = (ClienteFisico) clienteAtualizado;

                        clienteFisicoExistente.setNome(clienteFisicoAtualizado.getNome());
                        clienteFisicoExistente.setCpf(clienteFisicoAtualizado.getCpf());
                        clienteFisicoExistente.setDataNascimento(clienteFisicoAtualizado.getDataNascimento());
                    } else if (clienteExistente instanceof ClienteJuridico && clienteAtualizado instanceof ClienteJuridico) {
                        ClienteJuridico clienteJuridicoExistente = (ClienteJuridico) clienteExistente;
                        ClienteJuridico clienteJuridicoAtualizado = (ClienteJuridico) clienteAtualizado;

                        clienteJuridicoExistente.setNomeFantasia(clienteJuridicoAtualizado.getNomeFantasia());
                        clienteJuridicoExistente.setRazaoSocial(clienteJuridicoAtualizado.getRazaoSocial());
                        clienteJuridicoExistente.setCnpj(clienteJuridicoAtualizado.getCnpj());
                        clienteJuridicoExistente.setDataAbertura(clienteJuridicoAtualizado.getDataAbertura());
                    } else {
                        throw new IllegalArgumentException("Tipo de cliente inválido ou incompatível para atualização.");
                    }

                    return IClienteRepository.save(clienteExistente);
                }).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    @Override
    public void remover(Long id) {
        clienteRepository.deleteById(id);
    }
}
