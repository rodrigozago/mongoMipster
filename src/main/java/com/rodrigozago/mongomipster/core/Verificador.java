/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rodrigozago.mongomipster.core;

import java.util.InputMismatchException;

/**
 *
 * @author rodrigozago
 */
public class Verificador {
    
    public static boolean verificaIdComanda (String id)
    {
        if(id.length() != 4)
            return false;
        try {
            int identificador = Integer.parseInt(id);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    
    public static boolean verificaIdProduto (String id)
    {
        if(id.length() != 4)
            return false;
        try {
            int identificador = Integer.parseInt(id);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    
     public static boolean verificaCpf(String cpf) {
        if (cpf.equals("00000000000") || cpf.equals("11111111111")
                || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999")
                || (cpf.length() != 11)) 
        {
            return (false);
        }

        char digito10, digito11;
        int soma, i, r, num, peso;

        try 
        {
            soma = 0;
            peso = 10;
            for (i = 0; i < 9; i++) 
            {         
                num = (int) (cpf.charAt(i) - 48);
                soma = soma + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (soma % 11);
            if ((r == 10) || (r == 11)) 
            {
                digito10 = '0';
            } else 
            {
                digito10 = (char) (r + 48); 
            }
            soma = 0;
            peso = 11;
            for (i = 0; i < 10; i++) 
            {
                num = (int) (cpf.charAt(i) - 48);
                soma = soma + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (soma % 11);
            if ((r == 10) || (r == 11)) 
            {
                digito11 = '0';
            } 
            else 
            {
                digito11 = (char) (r + 48);
            }
            if ((digito10 == cpf.charAt(9)) && (digito11 == cpf.charAt(10))) 
            {
                return (true);
            } 
            else 
            {
                return (false);
            }
        } catch (InputMismatchException erro) 
        {
            return (false);
        }
    }
}
