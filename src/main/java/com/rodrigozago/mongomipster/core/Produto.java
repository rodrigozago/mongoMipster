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
public class Produto extends PersistableObject{
    String id;
    String nome;
    String descricao;
    String preco;
    String precoDeCusto;
    int quantEmEstoque;

    public Produto(String id, String nome, String descricao, String preco, String precoDeCusto, int quantEmEstoque) {
        super("produtos");
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.precoDeCusto = precoDeCusto;
        this.quantEmEstoque = quantEmEstoque;
    }
    
    public Produto(Document produto)
    {
        super("produtos");
        this.id = produto.getString("_id");
        this.nome = produto.getString("nome");
        this.descricao = produto.getString("descricao");
        this.preco = produto.getString("preco");
        this.precoDeCusto = produto.getString("precoDeCusto");
        this.quantEmEstoque = produto.getInteger("quantEmEstoque");
        
    }
    

    @Override
    public Document getDocument() {
        return new Document("_id", this.id).append("nome", this.nome)
                                          .append("descricao", this.descricao)
                                          .append("preco", this.preco)
                                          .append("precoDeCusto", this.precoDeCusto)
                                          .append("quantEmEstoque", this.quantEmEstoque);
    }
    
    public String getId()
    {
        return this.id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getPreco() {
        return preco;
    }

    public String getPrecoDeCusto() {
        return precoDeCusto;
    }

    public int getQuantEmEstoque() {
        return quantEmEstoque;
    }
    
    
}
