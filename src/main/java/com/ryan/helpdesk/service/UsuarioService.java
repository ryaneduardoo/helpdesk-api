package com.ryan.helpdesk.service;

import com.ryan.helpdesk.model.Usuario;
import com.ryan.helpdesk.repository.ChamadoRepository;
import com.ryan.helpdesk.repository.UsuarioRepository;
import com.ryan.helpdesk.service.exceptions.DataIntegrityViolationException;
import com.ryan.helpdesk.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public Usuario findById(Long id) {
        Optional<Usuario> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Usuario> findAll() {
        return repository.findAll();
    }

    public Usuario create(Usuario obj) {
        obj.setId(null);
        validaPorEmail(obj);
        obj.setSenha(encoder.encode(obj.getSenha()));
        return repository.save(obj);
    }

    public Usuario update(Long id, Usuario obj) {
        obj.setId(id);
        Usuario oldObj = findById(id);
        validaPorEmail(obj);

        if (!obj.getSenha().equals(oldObj.getSenha())) {
            obj.setSenha(encoder.encode(obj.getSenha()));
        }

        return repository.save(obj);
    }

    public void delete(Long id) {
        Usuario obj = findById(id);
        if(chamadoRepository.existsByTecnicoIdOrClienteId(id, id)){
            throw new DataIntegrityViolationException("Usuário possui chamados vinculados e não pode ser deletado!");
        }
        repository.delete(obj);
    }

    private void validaPorEmail(Usuario obj){
      Optional<Usuario> usuarioExistente = repository.findByEmail(obj.getEmail());
      if(usuarioExistente.isPresent() && !usuarioExistente.get().getId().equals(obj.getId())){
          throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }
}