package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.dto.mapper.UsuarioMapper;
import br.dev.paulowolfgang.gestao_apolices.dto.request.UsuarioRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.UsuarioResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Usuario;
import br.dev.paulowolfgang.gestao_apolices.exception.UsuarioNaoEncontradoException;
import br.dev.paulowolfgang.gestao_apolices.repository.IUsuarioRepository;
import br.dev.paulowolfgang.gestao_apolices.service.IUsuarioService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService
{
    private final IUsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(IUsuarioRepository iUsuarioRepository)
    {
        this.usuarioRepository = iUsuarioRepository;
    }

    @Override
    @Transactional
    public UsuarioResponseDto atualizar(Long id, UsuarioRequestDto usuarioAtualizado)
    {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(String.format("Usuário não encontrado para o ID: " + id)));
        UsuarioMapper.copiarParaPropriedades(usuarioAtualizado, usuario);
        usuario = usuarioRepository.save(usuario);

        return UsuarioMapper.converter(usuario);
    }

    @Override
    @Transactional
    public void remover(Long id)
    {
        if (!usuarioRepository.existsById(id))
        {
            throw new UsuarioNaoEncontradoException(String.format("Usuário não encontrado para o ID: " + id));
        }

        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsuarioResponseDto> listarTodos()
    {
        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios.stream()
                .map(UsuarioMapper::converter)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDto buscarPorId(Long id)
    {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(String.format("Usuário não encontrado para o ID: " + id)));

        return UsuarioMapper.converter(usuario);
    }
}
