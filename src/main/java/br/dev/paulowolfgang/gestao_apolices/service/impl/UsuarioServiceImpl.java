package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.entity.Usuario;
import br.dev.paulowolfgang.gestao_apolices.service.IUsuarioService;

import java.util.List;
import java.util.Optional;

public class UsuarioServiceImpl implements IUsuarioService {


    @Override
    public Usuario salvar(Usuario usuario) {
        return null;
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return Optional.empty();
    }

    @Override
    public List<Usuario> listarTodos() {
        return null;
    }

    @Override
    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        return null;
    }

    @Override
    public void remover(Long id) {

    }
}
