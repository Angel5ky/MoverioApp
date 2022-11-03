package com.example.moverioapp;

public class Datos {

    private  int valor = 0;
    private int posicion=0; //0 No mostrar, 1 Arriba izquierda,2 Arriba centro,...., 9 Abajo derecha

    public Datos(){
        valor = 0;
        posicion = 0;
    }

    public void setValor(int i){
        this.valor = i;
    }

    public int getValor(){
        return this.valor;
    }

    public void setPosicion(int i){
        this.posicion = i;
    }

    public int getPosicion(){
        return posicion;
    }
}




