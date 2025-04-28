package br.dev.paulowolfgang.gestao_apolices.config;

import br.dev.paulowolfgang.gestao_apolices.repository.IUsuarioRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter
{
    @Autowired
    TokenService tokenService;

    @Autowired
    IUsuarioRepository usuarioRepository;

    @Autowired
    private TokenBlacklistService tokenBlacklistService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var token = this.recoverToken(request);

        if (token != null && !tokenBlacklistService.isBlacklisted(token)) {
            var email = tokenService.validateToken(token);

            if (email != null && !email.isEmpty()) {
                Optional<UserDetails> usuarioOptional = usuarioRepository.findByEmail(email);

                if (usuarioOptional.isPresent()) {
                    UserDetails usuario = usuarioOptional.get();
                    var authentication = new UsernamePasswordAuthenticationToken(
                            usuario,
                            null,
                            usuario.getAuthorities()
                    );
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request)
    {
        var authHeader = request.getHeader("Authorization");

        if(authHeader == null)
        {
            return null;
        }

        return authHeader.replace("Bearer ", "");
    }
}
