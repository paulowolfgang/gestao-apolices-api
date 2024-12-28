package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.entity.Usuario;
import br.dev.paulowolfgang.gestao_apolices.repository.IUsuarioRepository;
import br.dev.paulowolfgang.gestao_apolices.service.IUsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    private final IUsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(IUsuarioRepository iUsuarioRepository) {
        this.usuarioRepository = iUsuarioRepository;
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        return usuarioRepository.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setNome(usuarioAtualizado.getNome());
                    usuarioExistente.setEmail(usuarioAtualizado.getEmail());
                    usuarioExistente.setSenha(usuarioAtualizado.getSenha());
                    usuarioExistente.setPapel(usuarioAtualizado.getPapel());
                    return usuarioRepository.save(usuarioExistente);
                }).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado para o ID: " + id));
    }

    @Override
    public void remover(Long id) {
        usuarioRepository.deleteById(id);
    }
}
