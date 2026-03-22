package br.dev.paulowolfgang.gestao_apolices.dto.request;

import br.dev.paulowolfgang.gestao_apolices.entity.Apolice;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(name = "ApoliceRequestDto", description = "Dados para criação/atualização de uma apólice.")
public class ApoliceRequestDto
{
    @Schema(description = "ID do cliente vinculado à apólice", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long clienteId;

    @Schema(description = "Valor total da cobertura da apólice", example = "100000.00")
    private BigDecimal valorCobertura;

    @Schema(description = "Valor do prêmio mensal", example = "250.50")
    private BigDecimal premioMensal;

    @Schema(description = "Valor total do prêmio", example = "3000.00")
    private BigDecimal premioTotal;

    @Schema(description = "Quantidade total de parcelas", example = "12")
    private Integer parcelasTotais;

    @Schema(description = "Quantidade de parcelas já pagas", example = "3")
    private Integer parcelasPagas;

    @Schema(description = "Data de início da vigência", example = "2025-01-01")
    private LocalDate dataInicio;

    @Schema(description = "Data de fim da vigência", example = "2026-01-01")
    private LocalDate dataFim;

    @Schema(description = "Tipo da apólice", example = "VIDA")
    private Apolice.Tipo tipo;

    @Schema(description = "Status atual da apólice", example = "ATIVA")
    private Apolice.Status status;

    public Long getClienteId()
    {
        return clienteId;
    }

    public void setClienteId(Long clienteId)
    {
        this.clienteId = clienteId;
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
