package br.dev.paulowolfgang.gestao_apolices.dto.request;

import br.dev.paulowolfgang.gestao_apolices.entity.Usuario;

import java.time.LocalDateTime;

public class UsuarioRequestDto {

    private String nome;
    private String email;
    private String senha;
    private Usuario.Papel papel;
    private LocalDateTime dataCriacao;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Usuario.Papel getPapel() {
        return papel;
    }

    public void setPapel(Usuario.Papel papel) {
        this.papel = papel;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
