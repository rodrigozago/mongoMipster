/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigozago.mongomipster.core;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.ServerAddress;

import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoCollection;


import org.bson.Document;

import com.mongodb.client.MongoCursor;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.result.DeleteResult;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.BSONException;
import org.bson.types.ObjectId;

/**
 *
 * @author rodrigozago
 */
public class Model {
    MongoClientURI connectionString;
    MongoClient mongoClient;
    MongoDatabase database;
    
    public Model(MongoClientURI uri) throws MongoException
    {
        // this.connectionString = new MongoClientURI("mongodb://admin:admin@ds145312.mlab.com:45312/mipster");
        this.connectionString = uri;
        mongoClient = new MongoClient(connectionString); 
        database = mongoClient.getDatabase("mipster");
    }
    
    public boolean createOneDocument(String collectionName, Document document)
    {
        
        try
        {
            MongoCollection<Document> collection = this.database.getCollection(collectionName);
            collection.insertOne(document);
            return true;
        }catch(MongoException e){
            return false;
        }
    }
    
    public boolean updateOneDocument(String collectionName, String key, String filter, Document document)
    {
        try
        {
            MongoCollection<Document> collection = this.database.getCollection(collectionName);
            collection.updateOne(eq(key, filter), new Document("$set", document));
            return true;
        }catch(MongoException e)
        {
            return false;
        }
    }
    public boolean updateOneDocument(String collectionName, String key, ObjectId filter, Document document)
    {
        try
        {
            MongoCollection<Document> collection = this.database.getCollection(collectionName);
            collection.updateOne(eq(key, filter), new Document("$set", document));
            return true;
        }catch(MongoException e)
        {
            return false;
        }
    }
    
    public boolean deleteOneDocument(String collectionName, String key, String filter)
    {
        try{
            MongoCollection<Document> collection = this.database.getCollection(collectionName);
            collection.deleteOne(eq(key, filter));
            return true;
        }catch(MongoException e)
        {
            return false;
        }
    }
    
    public boolean clienteTemComanda(String id)
    {
        try
        {
            Document comanda = this.getOneDocument("comandas", "cliente_id", id);
            if(comanda != null)
                return true;
            
            return false;
        }
        catch(RuntimeException e)
        {
            return false;
        }
    }
    
    public Document getOneDocument(String collectionName, String key, String filter)
    {
        MongoCollection<Document> collection = this.database.getCollection(collectionName);
        return (Document) collection.find(eq(key, filter)).first();
    }
    
    
    public Document getFirstDocument(String collectionName)
    {
        MongoCollection<Document> collection = this.database.getCollection(collectionName);
        return (Document) collection.find().first();
    }
    
    private ArrayList<Document> getArrayOfDocuments(String collectionName)
    {
        MongoCollection<Document> collection = this.database.getCollection(collectionName);
        ArrayList<Document> documentos = new ArrayList<>();
        
        MongoCursor<Document> iterator = collection.find().iterator();
        try {
            while (iterator.hasNext()) {
                documentos.add(iterator.next());
            }
        } finally {
            iterator.close();
        }
        return documentos;
    }
    
    private int getNumOfDocumentsOnACollection(String collectionName)
    {
        MongoCollection<Document> collection = this.database.getCollection(collectionName);
        return (int) collection.count();
    }
    
    public int getNumComandas()
    {
        return getNumOfDocumentsOnACollection("comandas");
    }
    
    public int getNumProdutos()
    {
        return getNumOfDocumentsOnACollection("produtos");
    }
    
    public int getNumClientes()
    {
        return getNumOfDocumentsOnACollection("clientes");
    }  
    
    public Cliente getCliente(String id)
    {
        try{
            Document clienteDoc = this.getOneDocument("clientes", "_id", id);
            if(clienteDoc != null)
                return new Cliente(clienteDoc);
            return null;
        }catch(RuntimeException e)
        {
            return null;
        }
    }
    
    public Cliente getCliente(Document clienteDoc)
    {
        try{
            return new Cliente(clienteDoc);
        }catch(MongoException e)
        {
            return null;
        }
    }
    
    public Produto getProduto(String id)
    {
        try
        {
            Document produtoDoc = this.getOneDocument("produtos", "_id", id);
            if(produtoDoc != null)
                return new Produto(produtoDoc);
            return null;
        }catch(MongoException e)
        {
            return null;
        }
    }
    
    public Produto getProduto(Document produtoDoc)
    {
        try
        {
            return new Produto(produtoDoc);
        }catch(MongoException e)
        {
            return null;
        }
    }
    
    public Comanda getComanda(String id)
    {
        try{
            Cliente cliente;
            ArrayList<Produto> produtos = new ArrayList<>();
            Document comandaDoc = this.getOneDocument("comandas", "_id", id);

            ArrayList<Document> produtosArrayOfReference = (ArrayList<Document>) comandaDoc.get("produtos");

            for(Iterator<Document> i = produtosArrayOfReference.iterator(); i.hasNext();)
            {
                produtos.add(this.getProduto(i.next().getString("produto_id")));
            }

            cliente = this.getCliente(comandaDoc.getString("cliente_id"));
            
            return new Comanda(comandaDoc.getString("_id"), cliente, produtos);
        }catch(RuntimeException e)
        {
            return null;
        }
         
    }
    
    public Comanda getComanda(Document comandaDoc)
    {
        try{
            Cliente cliente;
            ArrayList<Produto> produtos = new ArrayList<>();

            ArrayList<Document> produtosArrayOfReference = (ArrayList<Document>) comandaDoc.get("produtos");

            for(Iterator<Document> i = produtosArrayOfReference.iterator(); i.hasNext();)
            {
                produtos.add(this.getProduto(i.next().getString("produto_id")));
            }

            cliente = this.getCliente(comandaDoc.getString("cliente_id"));
            
            return new Comanda(comandaDoc.getString("_id"), cliente, produtos);
        }catch(MongoException e)
        {
            return null;
        }
         
    }
    
    
    public ArrayList<Comanda> getAllComandas()
    {
        ArrayList<Document> comandasArrayOfDocument = this.getArrayOfDocuments("comandas");
        ArrayList<Comanda> comandas = new ArrayList<>();
        
        for(Iterator<Document> i = comandasArrayOfDocument.iterator(); i.hasNext();)
        {
            comandas.add(this.getComanda(i.next()));
        }
        
        return comandas;
    }
    
    public ArrayList<Cliente> getAllClientes()
    {
        ArrayList<Document> clientesArrayOfDocument = this.getArrayOfDocuments("clientes");
        ArrayList<Cliente> clientes = new ArrayList<>();
        
        for(Iterator<Document> i = clientesArrayOfDocument.iterator(); i.hasNext();)
        {
            clientes.add(this.getCliente(i.next()));
        }
        
        return clientes;
    }
    
    public ArrayList<Produto> getAllProdutos()
    {
        ArrayList<Document> produtosArrayOfDocument = this.getArrayOfDocuments("produtos");
        ArrayList<Produto> produtos = new ArrayList<>();
        
        for(Iterator<Document> i = produtosArrayOfDocument.iterator(); i.hasNext();)
        {
            produtos.add(this.getProduto(i.next()));
        }
        
        return produtos;
    }
    
}
