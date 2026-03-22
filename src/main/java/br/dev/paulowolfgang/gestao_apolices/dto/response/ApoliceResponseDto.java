package br.dev.paulowolfgang.gestao_apolices.dto.response;

import br.dev.paulowolfgang.gestao_apolices.entity.Apolice;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;

@Schema(name = "ApoliceResponseDto", description = "Dados retornados de uma apólice.")
public class ApoliceResponseDto
{
    @Schema(description = "ID da apólice", example = "10")
    private Long id;

    @Schema(description = "ID do cliente", example = "1")
    private Long clienteId;

    @Schema(description = "Número único da apólice", example = "AP-2025-0001")
    private String numero;

    @Schema(description = "Valor total da cobertura", example = "100000.00")
    private BigDecimal valorCobertura;

    @Schema(description = "Valor do prêmio mensal", example = "250.50")
    private BigDecimal premioMensal;

    @Schema(description = "Valor total do prêmio", example = "3000.00")
    private BigDecimal premioTotal;

    @Schema(description = "Quantidade total de parcelas", example = "12")
    private Integer parcelasTotais;

    @Schema(description = "Parcelas já pagas", example = "3")
    private Integer parcelasPagas;

    @Schema(description = "Data de início da vigência", example = "2025-01-01")
    private LocalDate dataInicio;

    @Schema(description = "Data de fim da vigência", example = "2026-01-01")
    private LocalDate dataFim;

    @Schema(description = "Tipo da apólice", example = "VIDA")
    private Apolice.Tipo tipo;

    @Schema(description = "Status da apólice", example = "ATIVA")
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
