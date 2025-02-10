package br.dev.paulowolfgang.gestao_apolices.exception;

public class ApoliceNaoEncontradaException extends RuntimeException{
    public ApoliceNaoEncontradaException(String message){
        super(message);
    }
}
