package br.dev.paulowolfgang.gestao_apolices.dto.request;

import java.time.LocalDateTime;

public class ClienteFisicoRequestDto extends ClienteRequestDto {

    private String nome;
    private String cpf;
    private LocalDateTime dataNascimento;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDateTime getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDateTime dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
