package com.ryan.helpdesk.model;

public enum Prioridade {
    BAIXA, MEDIA, ALTA, URGENTE;

    public static Prioridade toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        if(cod < 0 || cod >= values().length){
            throw new IllegalArgumentException("Prioridade inválida! Código: " + cod);
        }
        return values()[cod];
    }
}
