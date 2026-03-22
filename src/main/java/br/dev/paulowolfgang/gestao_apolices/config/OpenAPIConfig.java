package br.dev.paulowolfgang.gestao_apolices.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig
{
    @Bean
    public OpenAPI customOpenAPI()
    {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestão de Apólices")
                        .description("""
                                API para gestão de apólices de seguros.
                                
                                ## Funcionalidades Principais:
                                * Cadastro e gerenciamento de apólices
                                * Controle de segurados
                                * Gestão de usuários e permissões
                                * Autenticação JWT
                                
                                ## Roles:
                                * **SUPER_ADMIN**: Acesso total ao sistema
                                * **ADMIN**: Gerenciamento de apólices e segurados
                                * **USER**: Consulta de apólices
                                
                                ## Fluxo de Autenticação:
                                1. Faça login em `/api/v1/auth/entrar` para obter um token JWT
                                2. Clique no botão "Authorize" acima e insira: `Bearer {seu-token}`
                                3. Agora você pode testar os endpoints protegidos
                                """)
                        .version("0.0.1-SNAPSHOT")
                        .contact(new Contact()
                                .name("Paulo Silva")
                                .email("paulosilva@example.com")
                                .url("https://github.com/paulowolfgang"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8080")
                                .description("Servidor de Desenvolvimento")
                ))
                .addSecurityItem(new SecurityRequirement()
                        .addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("bearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")
                                        .description("""
                                                Insira o token JWT obtido no endpoint de login.
                                                
                                                Formato: Bearer {seu-token-jwt}
                                                
                                                Exemplo: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
                                                """)));
    }
}
