package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.entity.Apolice;
import br.dev.paulowolfgang.gestao_apolices.repository.IApoliceRepository;
import br.dev.paulowolfgang.gestao_apolices.service.IApoliceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApoliceServiceImpl implements IApoliceService {

    private final IApoliceRepository apoliceRepository;

    public ApoliceServiceImpl(IApoliceRepository apoliceRepository) {
        this.apoliceRepository = apoliceRepository;
    }

    @Override
    public Apolice salvar(Apolice apolice) {
        return apoliceRepository.save(apolice);
    }

    @Override
    public Optional<Apolice> buscarPorId(Long id) {
        return apoliceRepository.findById(id);
    }

    @Override
    public List<Apolice> listarTodas() {
        return apoliceRepository.findAll();
    }

    @Override
    public Optional<Apolice> buscarPorNumero(String numero) {
        return apoliceRepository.findByNumero(numero);
    }

    @Override
    public Apolice atualizar(Long id, Apolice apoliceAtualizada) {

        return apoliceRepository.findById(id)
                .map(apoliceExistente -> {
                    apoliceExistente.setCliente(apoliceAtualizada.getCliente());
                    apoliceExistente.setNumero(apoliceAtualizada.getNumero());
                    apoliceExistente.setValorCobertura(apoliceAtualizada.getValorCobertura());
                    apoliceExistente.setPremioMensal(apoliceAtualizada.getPremioMensal());
                    apoliceExistente.setPremioTotal(apoliceAtualizada.getPremioTotal());
                    apoliceExistente.setParcelasTotais(apoliceAtualizada.getParcelasTotais());
                    apoliceExistente.setParcelasPagas(apoliceAtualizada.getParcelasPagas());
                    apoliceExistente.setDataInicio(apoliceAtualizada.getDataInicio());
                    apoliceExistente.setDataFim(apoliceAtualizada.getDataFim());
                    apoliceExistente.setTipo(apoliceAtualizada.getTipo());
                    apoliceExistente.setStatus(apoliceAtualizada.getStatus());
                    return apoliceRepository.save(apoliceExistente);
                })
                .orElseThrow(() -> new IllegalArgumentException("Apólice não encontrada para o ID: " + id));
    }

    @Override
    public void remover(Long id) {
        apoliceRepository.deleteById(id);
    }
}
