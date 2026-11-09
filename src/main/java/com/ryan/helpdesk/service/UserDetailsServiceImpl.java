package com.ryan.helpdesk.service;

import com.ryan.helpdesk.model.Usuario;
import com.ryan.helpdesk.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> resultado =usuarioRepository.findByEmail(username);
        if(resultado.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
            return resultado.get();
    }
}
