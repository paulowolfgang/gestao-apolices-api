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

    public static final String OFFSET_ID = "-03:00";
    public static final String AUTH_API = "auth-api";

    public String generateToken(Usuario usuario)
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = JWT
                    .create()
                    .withIssuer(AUTH_API)
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);

            return token;
        }
        catch(JWTCreationException exception)
        {
            throw new RuntimeException("Falha na geração do JWT: ", exception);
        }
    }

    public String validateToken(String token)
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer(AUTH_API)
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
                        .of(OFFSET_ID));
    }
}
