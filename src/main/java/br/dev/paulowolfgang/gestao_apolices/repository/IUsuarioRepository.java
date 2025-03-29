package br.dev.paulowolfgang.gestao_apolices.repository;

import br.dev.paulowolfgang.gestao_apolices.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUsuarioRepository extends JpaRepository<Usuario, Long>
{
    Optional<Usuario> findByEmail(String email);
}
