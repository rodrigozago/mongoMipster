/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigozago.mongomipster.core;

import org.bson.Document;

/**
 *
 * @author rodrigozago
 */
public class Cliente extends PersistableObject{
    String cpf;
    String nome;
    String sobrenome;
    String endereco;
    String nascimento;
    String telefone;
    
    public Cliente(String cpf, String nome, String sobrenome, String endereco, String nascimento, String telefone)
    {
        super("clientes");
        this.cpf = cpf;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.endereco = endereco;
        this.nascimento = nascimento;
        this.telefone = telefone;
    }
    
    public Cliente(Document clienteDocument)
    {
        super("clientes");
        this.cpf = (String) clienteDocument.get("_id");
        this.nome = (String) clienteDocument.get("nome");
        this.sobrenome = (String) clienteDocument.get("sobrenome");
        this.endereco = (String) clienteDocument.get("endereco");
        this.nascimento = (String) clienteDocument.get("nascimento");
        this.telefone = (String) clienteDocument.get("telefone");
    }

    @Override
    public Document getDocument() {
        return new Document("_id", this.cpf).append("nome", this.nome)
                                            .append("sobrenome", this.sobrenome)
                                            .append("endereco", this.endereco)
                                            .append("nascimento", this.nascimento)
                                            .append("telefone", this.telefone);
    }
    
    public String getCpf()
    {
        return this.cpf;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNascimento() {
        return nascimento;
    }

    public String getTelefone() {
        return telefone;
    }
    
    
    
    
    
}
