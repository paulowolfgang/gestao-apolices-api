package br.dev.paulowolfgang.gestao_apolices.dto.request;

import br.dev.paulowolfgang.gestao_apolices.entity.Pagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PagamentoRequestDto
{
    private String apoliceNumero;
    private BigDecimal valor;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private Pagamento.Status status;

    public String getApoliceNumero()
    {
        return apoliceNumero;
    }

    public void setApoliceNumero(String apoliceNumero)
    {
        this.apoliceNumero = apoliceNumero;
    }

    public BigDecimal getValor()
    {
        return valor;
    }

    public void setValor(BigDecimal valor)
    {
        this.valor = valor;
    }

    public LocalDate getDataVencimento()
    {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento)
    {
        this.dataVencimento = dataVencimento;
    }

    public LocalDate getDataPagamento()
    {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento)
    {
        this.dataPagamento = dataPagamento;
    }

    public Pagamento.Status getStatus()
    {
        return status;
    }

    public void setStatus(Pagamento.Status status)
    {
        this.status = status;
    }
}
