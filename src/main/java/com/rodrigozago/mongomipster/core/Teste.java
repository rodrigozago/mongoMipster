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
import java.util.List;

/**
 *
 * @author rodrigozago
 */
public class Teste {
//    public static void main(String[] args) {
//        try
//        {
////            MongoClientURI connectionString = new MongoClientURI("mongodb://admin:admin@ds145312.mlab.com:45312/mipster");
////            MongoClient mongoClient = new MongoClient(connectionString);     
////            MongoDatabase database = mongoClient.getDatabase("mipster");
////            MongoCollection<Document> collection = database.getCollection("clientes");
////            ArrayList<Document> produtos = new ArrayList();
////            produtos.add(new Document("nome", "Coca-Cola"));
////            Document comanda = new Document("aberta","true").append("produtos", produtos);
////            Document cliente = new Document("cpf", "28791762120").append("nome", "Sebastião")
////                                                               .append("sobrenome", "Zago")
////                                                               .append("endereco", "Getúlio de Sá - Morada Nova")
////                                                               .append("nascimento", "09011997")
////                                                               .append("telefone", "993357027")
////                                                               .append("comanda", comanda);
////            collection.insertOne(cliente);
//
////            Document client;
////            client = collection.find(eq("cpf", "02253592196")).first();
////            System.out.println(client.toJson());
//            Document caixa = new Document("aberto", false);
//            Model database = new Model();
//            database.createOneDocument("caixa", caixa);
//        }
//        catch(MongoException e)
//        {
//            System.out.println(e.getClass() + ": error: " + e.getMessage());
//        }
//    }
}



