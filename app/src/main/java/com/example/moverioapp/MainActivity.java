package com.example.moverioapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.moverioapp.moka7.S7;

public class MainActivity extends AppCompatActivity {

    private String IpAddr = "192.168.0.253";
    private int dBNumber = 10;
    private int length = 40;
    private int rack = 0;
    private int slot = 0;
    PlcConnection p;
    ActualizarTexto A;
    


    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    protected void onStart(){
        super.onStart();
        //cargaDatos();
    }

    @Override
    protected void onStop(){
        super.onStop();
        p.terminate();
    }

    public  void botonIniciar(View view){
        final Button botonInicio = (Button) findViewById(R.id.button2);
        final EditText textIP = (EditText) findViewById(R.id.textIP);
        IpAddr = textIP.getText().toString();
        cargaDatos();
        textIP.setVisibility(View.GONE);
        botonInicio.setVisibility(View.GONE);
        while(!p.leido);
        textoEnPantalla(); //<--
        final TextView textoDato1 = (TextView) findViewById(R.id.Dato1);


        A = new ActualizarTexto(textoDato1, p);
        new Thread(A).start();
    }

    //Cambio de a pantalla de ajustes
    public void botonAjustes(View view) {
        Intent intent = new Intent(this, Ajustes.class);
        startActivity(intent);
    }

    //Funcion de inicio de Tread de actualización de los datos de PLC
    public void cargaDatos() {
        p = new PlcConnection(IpAddr, dBNumber, length, rack, slot);
        new Thread(p).start();


        }

        //TODO: Refrescar pantalla continuamente con un thread
    public void textoEnPantalla() {
            final TextView textoDato1 = (TextView) findViewById(R.id.Dato1);
            int[] Datos;
            Datos = p.getDatos();
            for (int a : Datos) {
                textoDato1.append(a + " ");
            }
    }
//======================================================================================================================
}

 class ActualizarTexto implements Runnable {
    public TextView Dato1;
     PlcConnection p;


     public ActualizarTexto(TextView Dato1, PlcConnection p){
        this.Dato1 = Dato1;
        this.p = p;
    }

    public void run() {
         while(true) {
             try {
                 int[] Datos;
                 Datos = p.getDatos();
                // Dato1.setText(" ");
                 String cadena="";
                 for (int a : Datos) {
                     cadena += a + " ";
                     Thread.sleep(10);
                 }
                 Dato1.setText(cadena);
             }
             catch (Exception e) {
                 System.out.println("Fallo en la lectura del buffer");
                 System.out.println("Excepción " + e);
             }
         }

    }

}







