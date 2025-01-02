package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.entity.Apolice;
import br.dev.paulowolfgang.gestao_apolices.service.IApoliceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApoliceService implements IApoliceService {
    @Override
    public Apolice salvar(Apolice apolice) {
        return null;
    }

    @Override
    public Optional<Apolice> buscarPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Apolice> listarTodas() {
        return null;
    }

    @Override
    public Optional<Apolice> buscarPorNumero(String numero) {
        return Optional.empty();
    }

    @Override
    public Apolice atualizar(Long id, Apolice apoliceAtualizada) {
        return null;
    }

    @Override
    public void remover(Long id) {

    }
}
