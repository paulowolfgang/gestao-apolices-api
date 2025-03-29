package br.dev.paulowolfgang.gestao_apolices.dto.response;

import br.dev.paulowolfgang.gestao_apolices.entity.Pagamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PagamentoResponseDto
{

    private Long id;
    private Long apoliceId;
    private String numero;
    private BigDecimal valor;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private Pagamento.Status status;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getApoliceId()
    {
        return apoliceId;
    }

    public void setApoliceId(Long apoliceId)
    {
        this.apoliceId = apoliceId;
    }

    public String getNumero()
    {
        return numero;
    }

    public void setNumero(String numero)
    {
        this.numero = numero;
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
