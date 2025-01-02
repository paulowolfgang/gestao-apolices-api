package br.dev.paulowolfgang.gestao_apolices.service;


import br.dev.paulowolfgang.gestao_apolices.entity.Apolice;

import java.util.List;
import java.util.Optional;

public interface IApoliceService {

    Apolice salvar(Apolice apolice);
    Optional<Apolice> buscarPorId(Long id);
    List<Apolice> listarTodas();
    Optional<Apolice> buscarPorNumero(String numero);
    Apolice atualizar(Long id, Apolice apoliceAtualizada);
    void remover(Long id);
}
