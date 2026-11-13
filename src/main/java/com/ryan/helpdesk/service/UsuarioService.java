package com.ryan.helpdesk.service;

import com.ryan.helpdesk.model.Usuario;
import com.ryan.helpdesk.repository.UsuarioRepository;
import com.ryan.helpdesk.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // <-- Importante
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder; // <-- Injetar o encoder de senha

    public Usuario findById(Long id) {
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario create(Usuario obj) {
        obj.setId(null);
        obj.setSenha(encoder.encode(obj.getSenha())); // <-- ENCRIPTA A SENHA AQUI
        return repository.save(obj);
    }

    public Usuario update(Long id, Usuario obj) {
        obj.setId(id);
        Usuario oldObj = findById(id);

        // Se a senha foi alterada, precisamos codificar a nova senha também
        if (!obj.getSenha().equals(oldObj.getSenha())) {
            obj.setSenha(encoder.encode(obj.getSenha()));
        }

        return repository.save(obj);
    }

    public void delete(Long id) {
        Usuario obj = findById(id);
        repository.delete(obj);
    }
}