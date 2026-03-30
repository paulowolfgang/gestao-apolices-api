package br.dev.paulowolfgang.gestao_apolices.controller.docs;

import br.dev.paulowolfgang.gestao_apolices.dto.request.ApoliceRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.ApoliceResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.*;
import io.swagger.v3.oas.annotations.media.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Apólices", description = "Endpoints para gerenciamento de apólices.")
@SecurityRequirement(name = "bearerAuth")
public interface ApoliceControllerDoc
{
    @Operation(summary = "Cadastrar nova apólice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Apólice criada com sucesso.",
                    content = @Content(schema = @Schema(implementation = ApoliceResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.")
    })
    ResponseEntity<ApoliceResponseDto> salvar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados da apólice",
                    required = true
            ) ApoliceRequestDto request
    );


    @Operation(summary = "Atualizar apólice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Apólice atualizada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Apólice não encontrada.")
    })
    ResponseEntity<ApoliceResponseDto> atualizar(
            @Parameter(description = "Número da apólice", example = "AP123456") String numero, ApoliceRequestDto request
    );


    @Operation(summary = "Remover apólice")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Apólice removida com sucesso."),
            @ApiResponse(responseCode = "404", description = "Apólice não encontrada.")
    })
    ResponseEntity<Void> remover(
            @Parameter(description = "Número da apólice", example = "AP123456") String numero
    );


    @Operation(summary = "Listar todas as apólices")
    @ApiResponse(responseCode = "200", description = "Lista de apólices retornada com sucesso.")
    ResponseEntity<List<ApoliceResponseDto>> listarTodos();


    @Operation(summary = "Buscar apólice por número")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Apólice encontrada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Apólice não encontrada.")
    })
    ResponseEntity<ApoliceResponseDto> buscarPorNumero(
            @Parameter(description = "Número da apólice", example = "AP123456") String numero
    );
}
