package br.dev.paulowolfgang.gestao_apolices.repository;

import br.dev.paulowolfgang.gestao_apolices.entity.Apolice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IApoliceRepository extends JpaRepository<Apolice, Long>
{
    Optional<Apolice> findByNumero(String numero);
}
