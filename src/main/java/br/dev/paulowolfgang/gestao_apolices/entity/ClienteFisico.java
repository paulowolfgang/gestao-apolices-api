package br.dev.paulowolfgang.gestao_apolices.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("FISICO")
public class ClienteFisico extends Cliente
{

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 14)
    private String cpf;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    protected ClienteFisico() {}

    public ClienteFisico(String email, String endereco, String telefone, String nome, String cpf, LocalDate dataNascimento)
    {
        super(email, endereco, telefone);
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCpf()
    {
        return cpf;
    }

    public void setCpf(String cpf)
    {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento()
    {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento)
    {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString()
    {
        return "ClienteFisico{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
