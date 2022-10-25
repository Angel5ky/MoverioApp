package com.example.moverioapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.moverioapp.moka7.S7;

public class MainActivity extends AppCompatActivity {

    private String IpAddr = "192.168.1.253";
    private int dBNumber = 1;
    private int length = 320;
    private int rack = 0;
    private int slot = 0;
    PlcConnection p = new PlcConnection(IpAddr, dBNumber, length, rack, slot);
    
    final TextView textoDato1 = (TextView) findViewById(R.id.Dato1);
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargaDatos();
        textoEnPantalla();
    }

    //Cambio de a pantalla de ajustes
    public void botonAjustes(View view) {
        Intent intent = new Intent(this, Ajustes.class);
        startActivity(intent);
    }

    //Funcion de inicio de Tread de actualización de los datos de PLC
    public void cargaDatos() {
        new Thread(p).start();
        }

    public void textoEnPantalla() {
        int [] Datos;
        Datos = p.getDatos();
        for (int a: Datos) {
            textoDato1.append(Datos[a] + " ");
        }
        
    }






//======================================================================================================================
}







