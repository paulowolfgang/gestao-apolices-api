package br.dev.paulowolfgang.gestao_apolices.controller.docs;

import br.dev.paulowolfgang.gestao_apolices.dto.request.PagamentoRequestDto;
import br.dev.paulowolfgang.gestao_apolices.dto.response.PagamentoResponseDto;
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

@Tag(name = "Pagamentos", description = "Endpoints para gerenciamento de pagamentos.")
@SecurityRequirement(name = "bearerAuth")
public interface PagamentoControllerDoc
{
    @Operation(summary = "Cadastrar novo pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pagamento criado com sucesso.",
                    content = @Content(schema = @Schema(implementation = PagamentoResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Dados inválidos.")
    })
    ResponseEntity<PagamentoResponseDto> salvar(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Dados do cliente",
                    required = true
            ) PagamentoRequestDto request
    );

    @Operation(summary = "Atualizar pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento atualizado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado.")
    })
    ResponseEntity<PagamentoResponseDto> atualizar(
            @Parameter(description = "Número do pagamento", example = "PG123456") String numero,
            PagamentoRequestDto request
    );

    @Operation(summary = "Remover pagamento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pagamento removido com sucesso."),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado.")
    })
    ResponseEntity<Void> remover(
            @Parameter(description = "Número do pagamento", example = "PG123456") String numero
    );

    @Operation(summary = "Listar todos os pagamentos")
    @ApiResponse(responseCode = "200", description = "Lista de pagamentos retornada com sucesso.")
    ResponseEntity<List<PagamentoResponseDto>> listarTodos();

    @Operation(summary = "Buscar pagamento por número")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pagamento encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Pagamento não encontrado.")
    })
    ResponseEntity<PagamentoResponseDto> buscarPorNumero(
            @Parameter(description = "Número do pagamento", example = "PG123456") String numero
    );
}
