package com.ryan.helpdesk.model;

public enum Status {
    ABERTO, EM_ANDAMENTO, RESOLVIDO;

    public static Status toEnum(Integer cod){
        if(cod == null){
            return null;
        }
        if (cod < 0 || cod >= values().length){
            throw new IllegalArgumentException("Status inválido! Código: " + cod);
        }
        return values()[cod];
    }
}
