package com.ryan.helpdesk.dto;

import com.ryan.helpdesk.model.Perfil;
import com.ryan.helpdesk.model.Usuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class UsuarioDTO {

    private Long id;

    @NotBlank(message = "O campo NOME é obrigatório")
    private String nome;

    @NotBlank(message = "O campo E-MAIL é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotBlank(message = "O campo SENHA é obrigatório")
    private String senha;

    private Set<Integer> perfis = new HashSet<>();

    public UsuarioDTO(Usuario obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
        this.perfis = obj.getPerfis().stream()
                .map(Perfil::getCodigo)
                .collect(Collectors.toSet());
    }
}