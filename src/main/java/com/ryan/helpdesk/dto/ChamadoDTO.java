package com.ryan.helpdesk.dto;

import com.ryan.helpdesk.model.Chamado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChamadoDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private Integer status;
    private Integer prioridade;
    private Long tecnico;
    private Long cliente;
    private String nomeTecnico;
    private String nomeCliente;

    public ChamadoDTO(Chamado obj) {
        this.id = obj.getId();
        this.titulo = obj.getTitulo();
        this.descricao = obj.getDescricao();

        this.status = obj.getStatus().ordinal();
        this.prioridade = obj.getPrioridade().ordinal();

        this.tecnico = obj.getTecnico().getId();
        this.nomeTecnico = obj.getTecnico().getNome();

        this.cliente = obj.getCliente().getId();
        this.nomeCliente = obj.getCliente().getNome();
    }
}
