package br.dev.paulowolfgang.gestao_apolices.service.impl;

import br.dev.paulowolfgang.gestao_apolices.repository.IUsuarioRepository;
import br.dev.paulowolfgang.gestao_apolices.infra.i18n.Messages; // utilitário que criamos
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService
{
    private final IUsuarioRepository usuarioRepository;

    public AuthorizationService(IUsuarioRepository usuarioRepository)
    {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException
    {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        Messages.get("usuario.nao.encontrado", email)
                ));
    }
}
