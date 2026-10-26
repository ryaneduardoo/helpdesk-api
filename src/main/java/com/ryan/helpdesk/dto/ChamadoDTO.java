package com.ryan.helpdesk.dto;

import com.ryan.helpdesk.model.Chamado;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank(message = "O campo TÍTULO é obrigatório")
    private String titulo;
    @NotBlank(message = "O campo DESCRIÇÃO é obrigatório")
    private String descricao;
    @NotNull(message = "O campo STATUS é obrigatório")
    private Integer status;
    @NotNull(message = "O campo PRIORIDADE é obrigatório")
    private Integer prioridade;
    @NotNull(message = "O campo TÉCNICO é obrigatório")
    private Long tecnico;
    @NotNull(message = "O campo CLIENTE é obrigatório")
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
