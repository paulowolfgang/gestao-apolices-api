package br.dev.paulowolfgang.gestao_apolices.service;

import br.dev.paulowolfgang.gestao_apolices.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    Cliente salvar(Cliente cliente);
    Optional<Cliente> buscarPorId(Long id);
    List<Cliente> listarTodos();
    Cliente atualizar(Long id, Cliente clienteAtualizado);
    void remover(Long id);
}
