package br.dev.paulowolfgang.gestao_apolices.config;

import br.dev.paulowolfgang.gestao_apolices.entity.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService
{
    @Value("${spring.security.token.secret}")
    private String secret;

    public String generateToken(Usuario usuario)
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT
                    .create()
                    .withIssuer("auth-api")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);

            return token;
        }
        catch(JWTCreationException exception)
        {
            throw new RuntimeException("Falha na geração do JWT", exception);
        }
    }

    public String validateToken(String token)
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject();
        }
        catch(JWTVerificationException exception)
        {
            return "";
        }
    }

    private Instant generateExpirationDate()
    {
        return LocalDateTime
                .now()
                .plusHours(1)
                .toInstant(ZoneOffset
                        .of("-03:00"));
    }
}
