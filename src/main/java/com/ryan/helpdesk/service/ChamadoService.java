package com.ryan.helpdesk.service;

import com.ryan.helpdesk.dto.ChamadoDTO;
import com.ryan.helpdesk.model.*;
import com.ryan.helpdesk.repository.ChamadoRepository;
import com.ryan.helpdesk.service.exceptions.DataIntegrityViolationException;
import com.ryan.helpdesk.service.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class ChamadoService {
    @Autowired
    private ChamadoRepository repository;

    @Autowired
    private UsuarioService usuarioService;

    public ChamadoDTO findById(Long id){
        Chamado obj = repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado"));
        return new ChamadoDTO(obj);
    }

    public ChamadoDTO create(ChamadoDTO dto) {
        Chamado novoChamado = new Chamado();
        novoChamado.setTitulo(dto.getTitulo());
        novoChamado.setDescricao(dto.getDescricao());

        novoChamado.setStatus(Status.toEnum(dto.getStatus()));
        if (novoChamado.getStatus().equals(Status.RESOLVIDO)) {
            novoChamado.setDataFechamento(LocalDateTime.now());
        } else {
            novoChamado.setDataFechamento(null);
        }

        novoChamado.setPrioridade(Prioridade.toEnum(dto.getPrioridade()));
        novoChamado.setCliente(usuarioService.findById(dto.getCliente()));
        Usuario tecnico = usuarioService.findById(dto.getTecnico());
        if (!tecnico.getPerfis().contains(Perfil.TECNICO) && !tecnico.getPerfis().contains(Perfil.ADMIN)) {
            throw new DataIntegrityViolationException("O usuário informado não possui perfil de técnico!");
        }
        novoChamado.setTecnico(tecnico);

        novoChamado.setDataCriacao(LocalDateTime.now());
        Chamado chamadoSalvo = repository.save(novoChamado);
        return new ChamadoDTO(chamadoSalvo);
    }

    public List<ChamadoDTO> findAll(){
        return repository.findAll().stream().map(obj -> new ChamadoDTO(obj)).collect(Collectors.toList());
    }

    public ChamadoDTO update(Long id, ChamadoDTO obj){
        Chamado entity = repository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Chamado não encontrado! Id: " + id));

        entity.setTitulo(obj.getTitulo());
        entity.setDescricao(obj.getDescricao());
        entity.setStatus(Status.toEnum(obj.getStatus()));
        entity.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));

        if (entity.getStatus().equals(Status.RESOLVIDO)) {
            entity.setDataFechamento(LocalDateTime.now());
        } else {
            entity.setDataFechamento(null);
        }

        Usuario tecnico = usuarioService.findById(obj.getTecnico());
        if (!tecnico.getPerfis().contains(Perfil.TECNICO) && !tecnico.getPerfis().contains(Perfil.ADMIN)) {
            throw new DataIntegrityViolationException("O usuário informado não possui perfil de técnico!");
        }

        entity.setTecnico(tecnico);
        entity.setCliente(usuarioService.findById(obj.getCliente()));

        return new ChamadoDTO(repository.save(entity));
    }

    public void delete(Long id){
        findById(id);
        repository.deleteById(id);
    }

}
