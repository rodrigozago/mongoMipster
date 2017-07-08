/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigozago.mongomipster.core;

import com.rodrigozago.mongomipster.exceptions.CaixaAbertoException;
import com.rodrigozago.mongomipster.exceptions.CaixaFechadoException;
import java.util.ArrayList;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author rodrigozago
 */
public class Caixa extends PersistableObject{
    boolean aberto;
    ObjectId id;
    
    
    public Caixa(Document caixa)
    {
        super("caixa");
        this.setDocument(caixa);
        System.out.println("debug: $oid: "+this.id);
    }


    @Override
    public Document getDocument() {
        return new Document("aberto", this.aberto);
    }
    
    public void setDocument(Document caixa)
    {
        this.aberto = caixa.getBoolean("aberto");
        this.id = (ObjectId) caixa.get("_id");
    }
    
    public ObjectId getId()
    {
        return this.id;
    }
    
    public Document abrirCaixa() throws CaixaAbertoException
    {
        if(this.aberto)
            throw new CaixaAbertoException("O caixa já está aberto!");
        else
        {
            this.aberto = true;
            return new Document("aberto", this.aberto);
        }
            
    }
    
    public Document fecharCaixa()
    {
        if(this.aberto)
        {
            this.aberto = false;
            return new Document("aberto", this.aberto);
        }
        else
            throw new CaixaFechadoException("O caixa já está fechado!");
    }
    
    
    public boolean caixaAberto()
    {
        return this.aberto;
    }
}
