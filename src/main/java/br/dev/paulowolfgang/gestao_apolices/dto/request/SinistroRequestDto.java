package br.dev.paulowolfgang.gestao_apolices.dto.request;

import br.dev.paulowolfgang.gestao_apolices.entity.Sinistro;

import java.math.BigDecimal;
import java.time.LocalDate;

public class SinistroRequestDto
{
    private String apoliceNumero;
    private String descricao;
    private LocalDate dataOcorrido;
    private BigDecimal valorEstimado;
    private Sinistro.Status status = Sinistro.Status.EM_ANALISE;

    public String getApoliceNumero()
    {
        return apoliceNumero;
    }

    public void setApoliceNumero(String apoliceNumero)
    {
        this.apoliceNumero = apoliceNumero;
    }

    public String getDescricao()
    {
        return descricao;
    }

    public void setDescricao(String descricao)
    {
        this.descricao = descricao;
    }

    public LocalDate getDataOcorrido()
    {
        return dataOcorrido;
    }

    public void setDataOcorrido(LocalDate dataOcorrido)
    {
        this.dataOcorrido = dataOcorrido;
    }

    public BigDecimal getValorEstimado()
    {
        return valorEstimado;
    }

    public void setValorEstimado(BigDecimal valorEstimado)
    {
        this.valorEstimado = valorEstimado;
    }

    public Sinistro.Status getStatus()
    {
        return status;
    }

    public void setStatus(Sinistro.Status status)
    {
        this.status = status;
    }
}
