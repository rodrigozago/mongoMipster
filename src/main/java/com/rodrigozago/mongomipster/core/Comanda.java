/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigozago.mongomipster.core;

import java.util.ArrayList;
import java.util.Iterator;
import org.bson.Document;

/**
 *
 * @author rodrigozago
 */
public class Comanda extends PersistableObject{
    ArrayList<Produto> produtos;
    String id;
    Cliente cliente;

    public Comanda(String id, Cliente cliente, ArrayList<Produto> produtos) {
        super("comandas");
        this.id = id;
        this.cliente = cliente;
        if(produtos != null)
            this.produtos = produtos;
        else
            this.produtos = new ArrayList<>();
    }

    @Override
    public Document getDocument() {
        ArrayList<Document> produtos = new ArrayList<>();
        for(Iterator<Produto> i = this.produtos.iterator(); i.hasNext();)
        {
            produtos.add(new Document("produto_id", i.next().getId()));
        }
        return new Document("_id", this.id).append("cliente_id", this.cliente.getCpf())
                                          .append("produtos", produtos);
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }
    
    public void addProduto(Produto pro)
    {
        this.produtos.add(pro);
    }

    public String getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    
    
}
