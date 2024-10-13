package com.example.demo.controlador;

public class NotFoundException extends RuntimeException {
    
    private int id;

    public NotFoundException(int id){
        super("Usuario no encontrado con ID: " + id);
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }
}
