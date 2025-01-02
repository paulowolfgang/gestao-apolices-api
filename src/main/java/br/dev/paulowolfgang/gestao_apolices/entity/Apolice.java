package br.dev.paulowolfgang.gestao_apolices.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "apolices")
public class Apolice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O cliente deve ser informado.")
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false, foreignKey = @ForeignKey(name = "fk_cliente_id"))
    private Cliente cliente;

    @NotNull(message = "O número da apólice é obrigatório.")
    @Column(nullable = false, unique = true, length = 50)
    private String numero;

    @NotNull(message = "O valor de cobertura é obrigatório.")
    @Column(name = "valor_cobertura", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorCobertura;

    @NotNull(message = "O valor do prêmio mensal é obrigatório.")
    @Column(name = "premio_mensal", nullable = false, precision = 15, scale = 2)
    private BigDecimal premioMensal;

    @NotNull(message = "O valor do prêmio total é obrigatório.")
    @Column(name = "premio_total", nullable = false, precision = 15, scale = 2)
    private BigDecimal premioTotal;

    @NotNull(message = "O número total de parcelas é obrigatório.")
    @Column(name = "parcelas_totais", nullable = false)
    private Integer parcelasTotais;

    @Column(name = "parcelas_pagas", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer parcelasPagas = 0;

    @NotNull(message = "A data de início é obrigatória.")
    @Column(name = "data_inicio", nullable = false)
    private LocalDate dataInicio;

    @NotNull(message = "A data de fim é obrigatória.")
    @Column(name = "data_fim", nullable = false)
    private LocalDate dataFim;

    @NotNull(message = "O tipo de apólice é obrigatório.")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Tipo tipo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50, columnDefinition = "ENUM('ATIVA', 'CANCELADA', 'SUSPENSA') DEFAULT 'ATIVA'")
    private Status status = Status.ATIVA;

    public Apolice(){}

    public Apolice(Long id, Cliente cliente, String numero,
                   BigDecimal valorCobertura, BigDecimal premioMensal, BigDecimal premioTotal,
                   Integer parcelasTotais, Integer parcelasPagas, LocalDate dataInicio,
                   LocalDate dataFim, Tipo tipo, Status status) {
        this.id = id;
        this.cliente = cliente;
        this.numero = numero;
        this.valorCobertura = valorCobertura;
        this.premioMensal = premioMensal;
        this.premioTotal = premioTotal;
        this.parcelasTotais = parcelasTotais;
        this.parcelasPagas = parcelasPagas;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.tipo = tipo;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public BigDecimal getValorCobertura() {
        return valorCobertura;
    }

    public void setValorCobertura(BigDecimal valorCobertura) {
        this.valorCobertura = valorCobertura;
    }

    public BigDecimal getPremioMensal() {
        return premioMensal;
    }

    public void setPremioMensal(BigDecimal premioMensal) {
        this.premioMensal = premioMensal;
    }

    public BigDecimal getPremioTotal() {
        return premioTotal;
    }

    public void setPremioTotal(BigDecimal premioTotal) {
        this.premioTotal = premioTotal;
    }

    public Integer getParcelasTotais() {
        return parcelasTotais;
    }

    public void setParcelasTotais(Integer parcelasTotais) {
        this.parcelasTotais = parcelasTotais;
    }

    public Integer getParcelasPagas() {
        return parcelasPagas;
    }

    public void setParcelasPagas(Integer parcelasPagas) {
        this.parcelasPagas = parcelasPagas;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Tipo {
        VEICULO, RESIDENCIA, VIDA, SAUDE
    }

    public enum Status {
        ATIVA, CANCELADA, SUSPENSA
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Apolice apolice)) return false;
        return Objects.equals(getId(), apolice.getId()) && Objects.equals(getNumero(), apolice.getNumero());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getNumero());
    }

    @Override
    public String toString() {
        return "Apolice{" +
                "id=" + id +
                ", cliente=" + cliente +
                ", numero='" + numero + '\'' +
                ", valorCobertura=" + valorCobertura +
                ", premioMensal=" + premioMensal +
                ", premioTotal=" + premioTotal +
                ", parcelasTotais=" + parcelasTotais +
                ", parcelasPagas=" + parcelasPagas +
                ", dataInicio=" + dataInicio +
                ", dataFim=" + dataFim +
                ", tipo=" + tipo +
                ", status=" + status +
                '}';
    }
}
