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
        return sinistroRepository.save(sinistro);
    }

    @Override
    public Optional<Sinistro> buscarPorId(Long id) {
        return sinistroRepository.findById(id);
    }

    @Override
    public List<Sinistro> listarTodos() {
        return sinistroRepository.findAll();
    }

    @Override
    public Sinistro atualizar(Long id, Sinistro sinistroAtualizado) {
        return sinistroRepository.findById(id)
                .map(sinistroExistente -> {
                    sinistroExistente.setApolice(sinistroAtualizado.getApolice());
                    sinistroExistente.setNumero(sinistroAtualizado.getNumero());
                    sinistroExistente.setDescricao(sinistroAtualizado.getDescricao());
                    sinistroExistente.setDataOcorrido(sinistroAtualizado.getDataOcorrido());
                    sinistroExistente.setValorEstimado(sinistroAtualizado.getValorEstimado());
                    sinistroExistente.setStatus(sinistroAtualizado.getStatus());
                    return sinistroRepository.save(sinistroExistente);
                }).orElseThrow(() -> new IllegalArgumentException("Sinistro não encontrado para o ID: " + id));
    }

    @Override
    public void remover(Long id) {
        sinistroRepository.deleteById(id);
    }
}
