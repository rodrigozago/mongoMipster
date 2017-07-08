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
public abstract class PersistableObject {
    String collectionName;
    
    public PersistableObject(String collectionName)
    {
        this.collectionName = collectionName;
    }
    
    public abstract Document getDocument();
    
    public String getCollectionName()
    {
        return this.collectionName;
    }
    
    
}
