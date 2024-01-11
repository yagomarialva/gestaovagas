package br.com.yagomarialva.gestaovagas.modules.candidate;

public class UserFoundException extends RuntimeException {
    public UserFoundException(){
        super("Usuario ja existente!");
    }    
}
