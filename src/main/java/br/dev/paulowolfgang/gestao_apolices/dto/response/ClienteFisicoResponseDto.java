package br.dev.paulowolfgang.gestao_apolices.dto.response;

import java.time.LocalDate;

public class ClienteFisicoResponseDto extends ClienteResponseDto
{

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }
}
