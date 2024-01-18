package br.com.yagomarialva.gestaovagas.modules.exceptions;

public class UserFoundException extends RuntimeException {
    public UserFoundException(){
        super("Usuario ja existente!");
    }    
}
