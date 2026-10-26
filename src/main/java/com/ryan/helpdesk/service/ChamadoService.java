package com.ryan.helpdesk.service;

import com.ryan.helpdesk.dto.ChamadoDTO;
import com.ryan.helpdesk.model.Chamado;
import com.ryan.helpdesk.repository.ChamadoRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class ChamadoService {
    @Autowired
    private ChamadoRepository repository;

    public ChamadoDTO findById(Long id){
        Chamado obj = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Chamado não encontrado"));
        return new ChamadoDTO(obj);
    }

    public Chamado create(Chamado obj){
        obj.setId(null);
        return repository.save(obj);
    }

    public List<ChamadoDTO> findAll(){
        return repository.findAll().stream().map(obj -> new ChamadoDTO(obj)).collect(Collectors.toList());
    }

    public Chamado update(Long id, Chamado obj){
        Chamado entity = repository.findById(id).orElseThrow();
        entity.setTitulo(obj.getTitulo());
        entity.setDescricao(obj.getDescricao());
        entity.setStatus(obj.getStatus());
        entity.setTecnico(obj.getTecnico());
        return repository.save(entity);
    }

    public void delete(Long id){
        findById(id);
        repository.deleteById(id);
    }

}
