package br.dev.paulowolfgang.gestao_apolices.service;

import br.dev.paulowolfgang.gestao_apolices.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    Usuario salvar(Usuario usuario);
    Optional<Usuario> buscarPorId(Long id);
    List<Usuario> listarTodos();
    Usuario atualizar(Long id, Usuario usuarioAtualizado);
    void remover(Long id);
}
