package br.dev.paulowolfgang.gestao_apolices.exception.handler;

import br.dev.paulowolfgang.gestao_apolices.exception.UsuarioNaoEncontradoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(UsuarioNaoEncontradoException.class)
    public ResponseEntity<ApiError> handlerUsuarioNaoEncontradoException(UsuarioNaoEncontradoException usuarioNaoEncontradoException){
        ApiError apiError = new ApiError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), usuarioNaoEncontradoException.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiError);
    }
}
