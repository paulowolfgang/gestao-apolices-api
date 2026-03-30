package br.dev.paulowolfgang.gestao_apolices.controller.docs;

import br.dev.paulowolfgang.gestao_apolices.dto.request.SinistroRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.SinistroResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Sinistros", description = "Endpoints para gerenciamento de sinistros.")
@SecurityRequirement(name = "bearerAuth")
public interface SinistroControllerDoc
{
    @Operation(summary = "Cadastrar novo sinistro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sinistro criado com sucesso.",
                    content = @Content(schema = @Schema(implementation = SinistroResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.")
    })
    ResponseEntity<SinistroResponseDto> salvar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do sinistro",
                    required = true
            ) SinistroRequestDto request
    );

    @Operation(summary = "Atualizar sinistro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sinistro atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Sinistro não encontrado.")
    })
    ResponseEntity<SinistroResponseDto> atualizar(
            @Parameter(description = "Número do sinistro", example = "SN123456") String numero,
            SinistroRequestDto request
    );

    @Operation(summary = "Remover sinistro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Sinistro removido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Sinistro não encontrado.")
    })
    ResponseEntity<Void> remover(@Parameter(description = "Número do sinistro", example = "SN123456") String numero);

    @Operation(summary = "Listar todos os sinistros")
    @ApiResponse(responseCode = "200", description = "Lista de sinistros retornada com sucesso.")
    ResponseEntity<List<SinistroResponseDto>> listarTodos();

    @Operation(summary = "Buscar sinistro por número")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sinistro encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Sinistro não encontrado.")
    })
    ResponseEntity<SinistroResponseDto> buscarPorNumero(
            @Parameter(description = "Número do sinistro", example = "SN123456") String numero
    );
}
