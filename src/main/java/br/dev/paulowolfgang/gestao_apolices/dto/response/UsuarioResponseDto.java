package br.dev.paulowolfgang.gestao_apolices.dto.response;

import br.dev.paulowolfgang.gestao_apolices.entity.Usuario;

import java.time.LocalDateTime;

public class UsuarioResponseDto
{
    private Long id;
    private String nome;
    private String email;
    private Usuario.Papel papel;
    private LocalDateTime dataCriacao;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public Usuario.Papel getPapel()
    {
        return papel;
    }

    public void setPapel(Usuario.Papel papel)
    {
        this.papel = papel;
    }

    public LocalDateTime getDataCriacao()
    {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao)
    {
        this.dataCriacao = dataCriacao;
    }
}
