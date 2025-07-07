package br.dev.paulowolfgang.gestao_apolices.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "usuarios")
public class Usuario implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @NotNull
    @Size(min = 3, max = 100)
    private String nome;

    @Column(nullable = false, unique = true, length = 100)
    @Email
    @NotNull
    private String email;

    @Column(nullable = false)
    @NotNull
    @Size(min = 8)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull
    private Papel papel;

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao = LocalDateTime.now();

    public Usuario() {}

    public Usuario(String nome, String email, String senha, Papel papel)
    {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.papel = papel;
    }

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

    public String getSenha()
    {
        return senha;
    }

    public void setSenha(String senha)
    {
        this.senha = senha;
    }

    public Papel getPapel()
    {
        return papel;
    }

    public void setPapel(Papel papel)
    {
        this.papel = papel;
    }

    public LocalDateTime getDataCriacao()
    {
        return dataCriacao;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.papel == Papel.SUPER_ADMIN)
        {
            return List.of(new SimpleGrantedAuthority("ROLE_SUPER_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_CORRETOR"));
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_CORRETOR"));
        }
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public enum Papel
    {
        SUPER_ADMIN, CORRETOR
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Usuario usuario)) return false;
        return Objects.equals(getId(), usuario.getId());
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId());
    }

    @Override
    public String toString()
    {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", papel=" + papel +
                ", dataCriacao=" + dataCriacao +
                '}';
    }
}
