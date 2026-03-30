package br.dev.paulowolfgang.gestao_apolices.controller.docs;

import br.dev.paulowolfgang.gestao_apolices.dto.request.ClienteRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.ClienteResponseDto;
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

@Tag(name = "Clientes", description = "Endpoints para gerenciamento de clientes.")
@SecurityRequirement(name = "bearerAuth")
public interface ClienteControllerDoc
{
    @Operation(summary = "Cadastrar novo cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente criado com sucesso.",
                    content = @Content(schema = @Schema(implementation = ClienteResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.")
    })
    ResponseEntity<ClienteResponseDto> salvar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do cliente",
                    required = true
            ) ClienteRequestDto request
    );

    @Operation(summary = "Atualizar cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
    })
    ResponseEntity<ClienteResponseDto> atualizar(
            @Parameter(description = "Número do cliente", example = "CL123456") Long id,
            ClienteRequestDto request
    );

    @Operation(summary = "Remover cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente removido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
    })
    ResponseEntity<Void> remover(
            @Parameter(description = "Número do cliente", example = "CL123456") Long id
    );

    @Operation(summary = "Listar todos os clientes")
    @ApiResponse(responseCode = "200", description = "Lista de clientes retornada com sucesso.")
    ResponseEntity<List<ClienteResponseDto>> listarTodos();

    @Operation(summary = "Buscar cliente por número")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Cliente não encontrado.")
    })
    ResponseEntity<ClienteResponseDto> buscarPorId(
            @Parameter(description = "Número do cliente", example = "CL123456") Long id
    );
}
