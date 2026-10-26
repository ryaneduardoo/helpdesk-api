package com.ryan.helpdesk.controller;

import com.ryan.helpdesk.dto.ChamadoDTO;
import com.ryan.helpdesk.model.Chamado;
import com.ryan.helpdesk.service.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoController {
    @Autowired
    private ChamadoService service;
    @GetMapping(value = "/{id}")
    ResponseEntity<ChamadoDTO> findById(@PathVariable Long id) {
        ChamadoDTO obj = service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll(){
        List<ChamadoDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
        }

    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@RequestBody ChamadoDTO obj){
        obj = service.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
    @PutMapping(value = "/{id}")
    public ResponseEntity<Chamado> update(@PathVariable Long id, @RequestBody Chamado obj){
        obj = service.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    }
