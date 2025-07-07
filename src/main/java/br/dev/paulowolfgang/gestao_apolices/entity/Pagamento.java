package br.dev.paulowolfgang.gestao_apolices.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "pagamentos")
public class Pagamento
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O número de apólice deve ser informado.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apolice_id", nullable = false, foreignKey = @ForeignKey(name = "fk_apolice_id"))
    private Apolice apolice;

    @Column(name = "numero_pagamento", nullable = false, unique = true, length = 50, updatable = false)
    private String numero;

    @NotNull(message = "O valor deve ser informado")
    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal valor;

    @NotNull(message = "A data de vencimento é obrigatória.")
    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento", nullable = false)
    private LocalDate dataPagamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50, columnDefinition = "ENUM('PAGO', 'ATRASADO') DEFAULT 'ATRASADO'")
    private Status status = Status.ATRASADO;

    private static final String PREFIXO = "PGT-";

    public Pagamento()
    {
        this.numero = gerarNumero();
    }

    public Pagamento(Long id, Apolice apolice, String numero, BigDecimal valor,
                     LocalDate dataVencimento, LocalDate dataPagamento, Status status)
    {
        this.id = id;
        this.apolice = apolice;
        this.numero = numero;
        this.valor = valor;
        this.dataVencimento = dataVencimento;
        this.dataPagamento = dataPagamento;
        this.status = status;
    }

    @PrePersist
    public void prePersist()
    {
        if (this.numero == null || this.numero.isEmpty())
        {
            this.numero = gerarNumero();
        }
    }

    private String gerarNumero()
    {
        return PREFIXO + UUID.randomUUID().toString();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Apolice getApolice()
    {
        return apolice;
    }

    public void setApolice(Apolice apolice)
    {
        this.apolice = apolice;
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

    public Status getStatus()
    {
        return status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    public enum Status
    {
        PAGO, ATRASADO
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Pagamento pagamento)) return false;
        return Objects.equals(getId(), pagamento.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId());
    }

    @Override
    public String toString()
    {
        return "Pagamento{" +
                "id=" + id +
                ", apolice=" + apolice +
                ", numero='" + numero + '\'' +
                ", valor=" + valor +
                ", dataVencimento=" + dataVencimento +
                ", dataPagamento=" + dataPagamento +
                ", status=" + status +
                '}';
    }
}
