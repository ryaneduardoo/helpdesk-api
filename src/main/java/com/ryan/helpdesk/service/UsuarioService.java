package com.ryan.helpdesk.service;

import com.ryan.helpdesk.model.Usuario;
import com.ryan.helpdesk.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));
    }

    public List<Usuario> findAll(){
        return usuarioRepository.findAll();
    }

    public Usuario create(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    public Usuario update(Long id, Usuario obj){
        Usuario newObj = findById(id);
        newObj.setNome(obj.getNome());
        newObj.setEmail(obj.getEmail());
        newObj.setPerfil(obj.getPerfil());
        newObj.setSenha(obj.getSenha());

        return usuarioRepository.save(newObj);
    }

    public void delete(Long id) {
        findById(id);
        usuarioRepository.deleteById(id);
    }
}
