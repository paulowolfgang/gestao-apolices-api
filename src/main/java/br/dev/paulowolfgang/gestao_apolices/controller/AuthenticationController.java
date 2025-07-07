package br.dev.paulowolfgang.gestao_apolices.controller;

import br.dev.paulowolfgang.gestao_apolices.config.TokenBlacklistService;
import br.dev.paulowolfgang.gestao_apolices.config.TokenService;
import br.dev.paulowolfgang.gestao_apolices.dto.request.UsuarioRequestDto;
import br.dev.paulowolfgang.gestao_apolices.entity.record.AuthenticationDto;
import br.dev.paulowolfgang.gestao_apolices.entity.record.LoginResponseDto;
import br.dev.paulowolfgang.gestao_apolices.entity.Usuario;
import br.dev.paulowolfgang.gestao_apolices.repository.IUsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthenticationController
{
    private final AuthenticationManager authenticationManager;
    private final IUsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenService tokenService;
    private final TokenBlacklistService tokenBlacklistService;

    public AuthenticationController(AuthenticationManager authenticationManager, IUsuarioRepository usuarioRepository,
                                    PasswordEncoder passwordEncoder, TokenService tokenService,
                                    TokenBlacklistService tokenBlacklistService)
    {
        this.authenticationManager = authenticationManager;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenService = tokenService;
        this.tokenBlacklistService = tokenBlacklistService;
    }

    @PostMapping("/registrar")
    public ResponseEntity registrar(@RequestBody @Valid UsuarioRequestDto data)
    {
        if(this.usuarioRepository.findByEmail(data.getEmail()).isPresent())
        {
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = passwordEncoder.encode(data.getSenha());

        Usuario usuario = new Usuario(data.getNome(),
                data.getEmail(),
                encryptedPassword,
                data.getPapel());

        this.usuarioRepository.save(usuario);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/entrar")
    public ResponseEntity entrar(@RequestBody @Valid AuthenticationDto data)
    {
        var emailSenha = new UsernamePasswordAuthenticationToken(data.email(), data.senha());
        var auth = this.authenticationManager.authenticate(emailSenha);

        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/sair")
    public ResponseEntity<?> sair(HttpServletRequest request)
    {
        String token = recoverToken(request);

        if (token != null && !token.isEmpty())
        {
            tokenBlacklistService.add(token);
        }

        return ResponseEntity.ok().build();
    }

    private String recoverToken(HttpServletRequest request)
    {
        var authHeader = request.getHeader("Authorization");

        if (authHeader == null || authHeader.isEmpty())
        {
            return null;
        }

        return authHeader.replace("Bearer ", "");
    }
}
