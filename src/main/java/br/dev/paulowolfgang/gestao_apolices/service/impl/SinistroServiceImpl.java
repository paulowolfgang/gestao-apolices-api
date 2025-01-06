package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.entity.Sinistro;
import br.dev.paulowolfgang.gestao_apolices.repository.ISinistroRepository;
import br.dev.paulowolfgang.gestao_apolices.service.ISinistroService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SinistroServiceImpl implements ISinistroService {

    private final ISinistroRepository sinistroRepository;

    public SinistroServiceImpl(ISinistroRepository sinistroRepository) {
        this.sinistroRepository = sinistroRepository;
    }


    @Override
    public Sinistro salvar(Sinistro sinistro) {
        return null;
    }

    @Override
    public Optional<Sinistro> buscarPorId(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Sinistro> listarTodos() {
        return null;
    }

    @Override
    public Sinistro atualizar(Long id, Sinistro sinistroAtualizado) {
        return null;
    }

    @Override
    public void remover(Long id) {

    }
}
