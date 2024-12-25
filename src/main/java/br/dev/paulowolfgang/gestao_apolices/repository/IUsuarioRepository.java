package br.dev.paulowolfgang.gestao_apolices.repository;

import br.dev.paulowolfgang.gestao_apolices.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends JpaRepository<Usuario, Integer> {
}
