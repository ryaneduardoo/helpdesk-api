package com.ryan.helpdesk.controller;

import com.ryan.helpdesk.dto.UsuarioDTO;
import com.ryan.helpdesk.model.Usuario;
import com.ryan.helpdesk.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService service;
    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll(){
        List<Usuario> list = service.findAll();
        List<UsuarioDTO> listDTO = list.stream().map(obj -> new UsuarioDTO(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);

    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable Long id){
        Usuario obj = service.findById(id);
        return ResponseEntity.ok().body(new UsuarioDTO(obj));
    }
    @PostMapping
    public ResponseEntity<UsuarioDTO> create(@RequestBody UsuarioDTO objDTO){
        Usuario novoObj = service.create(new Usuario(objDTO));
        return ResponseEntity.ok().body(new UsuarioDTO(novoObj));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UsuarioDTO> update(@PathVariable Long id, @RequestBody UsuarioDTO objDTO){
        Usuario novoObj = service.update(id, new Usuario(objDTO));
        return ResponseEntity.ok().body(new UsuarioDTO(novoObj));

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
