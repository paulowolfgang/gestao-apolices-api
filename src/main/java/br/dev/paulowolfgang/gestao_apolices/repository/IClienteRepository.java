package br.dev.paulowolfgang.gestao_apolices.repository;

import br.dev.paulowolfgang.gestao_apolices.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClienteRepository extends JpaRepository<Cliente, Long> {}
