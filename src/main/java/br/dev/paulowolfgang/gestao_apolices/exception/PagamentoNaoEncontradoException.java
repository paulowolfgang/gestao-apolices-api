package br.dev.paulowolfgang.gestao_apolices.exception;

public class PagamentoNaoEncontradoException extends RuntimeException
{
    public PagamentoNaoEncontradoException(String message)
    {
        super(message);
    }
}
