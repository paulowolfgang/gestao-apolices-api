package br.dev.paulowolfgang.gestao_apolices.service;

import br.dev.paulowolfgang.gestao_apolices.dto.request.UsuarioRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.UsuarioResponseDto;

import java.util.List;

public interface IUsuarioService {

    UsuarioResponseDto salvar(UsuarioRequestDto usuarioRequest);
    UsuarioResponseDto buscarPorId(Long id);
    List<UsuarioResponseDto> listarTodos();
    UsuarioResponseDto atualizar(Long id, UsuarioRequestDto usuarioAtualizado);
    void remover(Long id);
}
