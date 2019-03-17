package com.TESTE.TXappteste225205252;

import java.io.Serializable;

public class Usuario implements Serializable {
    public String nome;
    public String email;
    public String senha;
    public String telefone;

    public Usuario() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = senha;
    }

    public Usuario(String nome, String email, String senha, String telefone) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.telefone = telefone;
    }

}
