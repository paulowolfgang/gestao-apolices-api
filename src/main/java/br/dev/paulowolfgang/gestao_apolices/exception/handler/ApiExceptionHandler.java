package br.dev.paulowolfgang.gestao_apolices.exception.handler;

import br.dev.paulowolfgang.gestao_apolices.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler
{
    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ApiError> handlerUsuarioNaoEncontradoException(UsuarioNaoEncontradoException usuarioNaoEncontradoException)
    {
        ApiError apiError = new ApiError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), usuarioNaoEncontradoException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(ClienteNaoEncontradoException.class)
    public ResponseEntity<ApiError> handlerClienteNaoEncontradoException(ClienteNaoEncontradoException clienteNaoEncontradoException)
    {
        ApiError apiError = new ApiError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), clienteNaoEncontradoException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(ApoliceNaoEncontradaException.class)
    public ResponseEntity<ApiError> handlerApoliceNaoEncontradoException(ApoliceNaoEncontradaException apoliceNaoEncontradaException)
    {
        ApiError apiError = new ApiError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), apoliceNaoEncontradaException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(PagamentoNaoEncontradoException.class)
    public ResponseEntity<ApiError> handlerPagamentoNaoEncontradoException(PagamentoNaoEncontradoException pagamentoNaoEncontradoException)
    {
        ApiError apiError = new ApiError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), pagamentoNaoEncontradoException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }

    @ExceptionHandler(SinistroNaoEncontradoException.class)
    public ResponseEntity<ApiError> handlerSinistroNaoEncontradoException(SinistroNaoEncontradoException sinistroNaoEncontradoException)
    {
        ApiError apiError = new ApiError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), sinistroNaoEncontradoException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
}
