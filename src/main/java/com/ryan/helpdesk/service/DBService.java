package com.ryan.helpdesk.service;

import com.ryan.helpdesk.model.Perfil;
import com.ryan.helpdesk.model.Usuario;
import com.ryan.helpdesk.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class DBService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PasswordEncoder encoder;

    public void instanciaBaseDeDados(){
        Usuario user = new Usuario();
        user.setNome("Usuario Teste");
        user.setEmail("teste@teste.com");
        usuarioRepository.save(user);
        user.setSenha(encoder.encode("123"));
        user.addPerfil(Perfil.ADMIN);

    }
}
