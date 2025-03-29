package br.dev.paulowolfgang.gestao_apolices.dto.request;

import java.time.LocalDate;

public class ClienteJuridicoRequestDto extends ClienteRequestDto
{

    private String nomeFantasia;
    private String razaoSocial;
    private String cnpj;
    private LocalDate dataAbertura;

    public String getNomeFantasia()
    {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia)
    {
        this.nomeFantasia = nomeFantasia;
    }

    public String getRazaoSocial()
    {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial)
    {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj()
    {
        return cnpj;
    }

    public void setCnpj(String cnpj)
    {
        this.cnpj = cnpj;
    }

    public LocalDate getDataAbertura()
    {
        return dataAbertura;
    }

    public void setDataAbertura(LocalDate dataAbertura)
    {
        this.dataAbertura = dataAbertura;
    }
}
