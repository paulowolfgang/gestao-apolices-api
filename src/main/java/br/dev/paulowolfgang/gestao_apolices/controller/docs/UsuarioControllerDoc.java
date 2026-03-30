package br.dev.paulowolfgang.gestao_apolices.controller.docs;

import br.dev.paulowolfgang.gestao_apolices.dto.request.UsuarioRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.UsuarioResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários.")
@SecurityRequirement(name = "bearerAuth")
public interface UsuarioControllerDoc
{
    @Operation(summary = "Atualizar usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    ResponseEntity<UsuarioResponseDto> atualizar(
            @Parameter(description = "ID do usuário", example = "1") Long id,
            UsuarioRequestDto request
    );

    @Operation(summary = "Remover usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Usuário removido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    ResponseEntity<Void> remover(
            @Parameter(description = "ID do usuário", example = "1") Long id
    );

    @Operation(summary = "Listar todos os usuários")
    @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso.")
    ResponseEntity<List<UsuarioResponseDto>> listarTodos();

    @Operation(summary = "Buscar usuário por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.")
    })
    ResponseEntity<UsuarioResponseDto> buscarPorId(
            @Parameter(description = "ID do usuário", example = "1") Long id
    );
}
