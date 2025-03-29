package br.dev.paulowolfgang.gestao_apolices.dto.response;

import br.dev.paulowolfgang.gestao_apolices.entity.Apolice;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ApoliceResponseDto
{

    private Long id;
    private Long clienteId;
    private String numero;
    private BigDecimal valorCobertura;
    private BigDecimal premioMensal;
    private BigDecimal premioTotal;
    private Integer parcelasTotais;
    private Integer parcelasPagas;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Apolice.Tipo tipo;
    private Apolice.Status status;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getClienteId()
    {
        return clienteId;
    }

    public void setClienteId(Long clienteId)
    {
        this.clienteId = clienteId;
    }

    public String getNumero()
    {
        return numero;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
    }

    public BigDecimal getValorCobertura()
    {
        return valorCobertura;
    }

    public void setValorCobertura(BigDecimal valorCobertura)
    {
        this.valorCobertura = valorCobertura;
    }

    public BigDecimal getPremioMensal()
    {
        return premioMensal;
    }

    public void setPremioMensal(BigDecimal premioMensal)
    {
        this.premioMensal = premioMensal;
    }

    public BigDecimal getPremioTotal()
    {
        return premioTotal;
    }

    public void setPremioTotal(BigDecimal premioTotal)
    {
        this.premioTotal = premioTotal;
    }

    public Integer getParcelasTotais()
    {
        return parcelasTotais;
    }

    public void setParcelasTotais(Integer parcelasTotais)
    {
        this.parcelasTotais = parcelasTotais;
    }

    public Integer getParcelasPagas()
    {
        return parcelasPagas;
    }

    public void setParcelasPagas(Integer parcelasPagas)
    {
        this.parcelasPagas = parcelasPagas;
    }

    public LocalDate getDataInicio()
    {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio)
    {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim()
    {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim)
    {
        this.dataFim = dataFim;
    }

    public Apolice.Tipo getTipo()
    {
        return tipo;
    }

    public void setTipo(Apolice.Tipo tipo)
    {
        this.tipo = tipo;
    }

    public Apolice.Status getStatus()
    {
        return status;
    }

    public void setStatus(Apolice.Status status)
    {
        this.status = status;
    }
}
