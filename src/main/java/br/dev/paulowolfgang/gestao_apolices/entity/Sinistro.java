package br.dev.paulowolfgang.gestao_apolices.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "sinistros")
public class Sinistro
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O número de apólice deve ser informado.")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apolice_id", nullable = false, foreignKey = @ForeignKey(name = "fk_apolice_id"))
    private Apolice apolice;

    @Column(name = "numero_sinistro", nullable = false, unique = true, length = 50, updatable = false)
    private String numero;

    @NotNull(message = "A descrição do sinistro é obrigatória.")
    @Column(nullable = false)
    private String descricao;

    @NotNull(message = "A data do ocorrido é obrigatória.")
    @Column(name = "data_ocorrido", nullable = false)
    private LocalDate dataOcorrido;

    @NotNull(message = "O valor estimado é obrigatório.")
    @Column(name = "valor_estimado", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorEstimado;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50, columnDefinition = "ENUM('EM_ANALISE', 'APROVADO', 'REJEITADO') DEFAULT 'EM_ANALISE'")
    private Status status = Status.EM_ANALISE;

    private static final String PREFIXO = "SNT-";

    public Sinistro()
    {
        this.numero = gerarNumero();
    }

    public Sinistro(Long id, Apolice apolice, String numero,
                    String descricao, LocalDate dataOcorrido, BigDecimal valorEstimado, Status status)
    {
        this.id = id;
        this.apolice = apolice;
        this.numero = numero;
        this.descricao = descricao;
        this.dataOcorrido = dataOcorrido;
        this.valorEstimado = valorEstimado;
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
        EM_ANALISE, APROVADO, REJEITADO
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Sinistro sinistro)) return false;
        return Objects.equals(getId(), sinistro.getId()) && Objects.equals(getNumero(), sinistro.getNumero());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId(), getNumero());
    }

    @Override
    public String toString()
    {
        return "Sinistro{" +
                "id=" + id +
                ", apolice=" + apolice +
                ", numero='" + numero + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataOcorrido=" + dataOcorrido +
                ", valorEstimado=" + valorEstimado +
                ", status=" + status +
                '}';
    }
}
