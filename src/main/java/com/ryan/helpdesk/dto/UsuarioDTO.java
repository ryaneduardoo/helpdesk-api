package com.ryan.helpdesk.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ryan.helpdesk.model.Perfil;
import com.ryan.helpdesk.model.Usuario;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class UsuarioDTO{
    private Long id;
    private String nome;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;
    private Perfil perfil;

    public UsuarioDTO(Usuario obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfil = obj.getPerfil();
    }


}
