package com.ryan.helpdesk.model;

import com.ryan.helpdesk.dto.UsuarioDTO;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Data
@Entity
@NoArgsConstructor
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String senha;
    protected Set<Integer> perfis = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    public Usuario(UsuarioDTO obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.senha = obj.getSenha();
        this.email = obj.getEmail();
        this.perfil = obj.getPerfil();

    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.perfis.stream()
                .map(x -> new SimpleGrantedAuthority("ROLE_" + Perfil.toEnum(x).name()))
                .collect(Collectors.toList());
    }

    @Override
    public @Nullable String getPassword() {
        return this.getSenha();
    }

    @Override
    public String getUsername() {
        return this.getEmail();
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

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCodigo());
    }

}
