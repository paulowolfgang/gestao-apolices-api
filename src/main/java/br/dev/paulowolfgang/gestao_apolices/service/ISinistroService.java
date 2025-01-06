package br.dev.paulowolfgang.gestao_apolices.service;

import br.dev.paulowolfgang.gestao_apolices.entity.Sinistro;

import java.util.List;
import java.util.Optional;

public interface ISinistroService {

    Sinistro salvar(Sinistro sinistro);
    Optional<Sinistro> buscarPorId(Long id);
    List<Sinistro> listarTodos();
    Sinistro atualizar(Long id, Sinistro sinistroAtualizado);
    void remover(Long id);
}
