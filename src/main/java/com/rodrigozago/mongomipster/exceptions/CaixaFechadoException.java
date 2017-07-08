/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigozago.mongomipster.exceptions;

/**
 *
 * @author rodrigozago
 */
public class CaixaFechadoException extends RuntimeException{
    public CaixaFechadoException(String message)
    {
        super(message);
    }
}
