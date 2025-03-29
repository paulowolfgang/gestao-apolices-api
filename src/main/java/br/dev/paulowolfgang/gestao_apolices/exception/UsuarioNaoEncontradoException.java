package br.dev.paulowolfgang.gestao_apolices.exception;

public class UsuarioNaoEncontradoException extends RuntimeException
{
    public UsuarioNaoEncontradoException(String message)
    {
        super(message);
    }
}
