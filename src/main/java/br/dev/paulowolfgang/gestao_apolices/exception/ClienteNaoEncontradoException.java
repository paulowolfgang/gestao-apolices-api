package br.dev.paulowolfgang.gestao_apolices.exception;

public class ClienteNaoEncontradoException extends RuntimeException {
    public ClienteNaoEncontradoException(String message){
        super(message);
    }
}
