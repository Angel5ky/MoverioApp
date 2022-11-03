package com.example.moverioapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.moverioapp.moka7.PlcConnection;

public class MainActivity extends AppCompatActivity {

    private String IpAddr = "192.168.0.253";
    private int dBNumber = 10;
    private int length = 40;
    private int rack = 0;
    private int slot = 0;
    PlcConnection p;
    ActualizarTexto A;
    public static Datos[] d = new Datos[20];
    TextView [] textos = new TextView[10];
    


    
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
        final Button botonAjustes = (Button) findViewById(R.id.button);

        final EditText textIP = (EditText) findViewById(R.id.textIP);
        IpAddr = textIP.getText().toString();
        cargaDatos();
        textIP.setVisibility(View.GONE);
        botonAjustes.setVisibility(View.VISIBLE);
        while(!p.leido);
        botonInicio.setVisibility(View.GONE);
        //textoEnPantalla(); //<--
        final TextView textoDato1 = (TextView) findViewById(R.id.Dato1);
        for(int i = 1;i<10;i++) {    //TODO: Actualizar a 20 campos de datos
            textos[i] = (TextView) findViewById(getResources().getIdentifier("Dato" + i, "id", this.getPackageName()));
        }

        for(int i=0;i<20;i++){
            d[i]=new Datos();
        }
        A = new ActualizarTexto(textos, p, d);
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

    /*    //TODO: Refrescar pantalla continuamente con un thread
    public void textoEnPantalla() {
            final TextView textoDato1 = (TextView) findViewById(R.id.Dato1);
            int[] Datos;
            Datos = p.getDatos();
            for (int a : Datos) {
                textoDato1.append(a + " ");
            }
    }*/

//======================================================================================================================
}

 class ActualizarTexto implements Runnable {
    TextView [] textView;
     PlcConnection p;
     Datos[] d;


     public ActualizarTexto(TextView [] textView, PlcConnection p, Datos[] d){
        this.textView = textView;
        this.p = p;
        this.d = d;
    }

    public void run() {
         while(true) {
             try {
                 int[] Datos;
                 Datos = p.getDatos();
                // Dato1.setText(" ");
                 String [] cadena = new String[10];
                 for(int i=1;i<=9;i++){
                     cadena[i]=new String();
                 }

                 //Bucle de asignación de valores
                 for(int i=0;i<20;i++){
                     d[i].setValor(Datos[i]);
                 }
                 for (String i: cadena) {
                     i = "";
                 }
                 //Bucle de lectura y posicionador de datos
                 for(int i=0;i<=9;i++){
                     switch(d[i].getPosicion()){
                         case 0:
                            break;
                         case 1:
                            cadena[1] += d[i].getValor()+"\n";
                            break;
                         case 2:
                             cadena[2] += d[i].getValor()+"\n";
                             break;
                         case 3:
                             cadena[3] += d[i].getValor()+"\n";
                             break;
                         case 4:
                             cadena[4] += d[i].getValor()+"\n";
                             break;
                         case 5:
                             cadena[5] += d[i].getValor()+"\n";
                             break;
                         case 6:
                             cadena[7] += d[i].getValor()+"\n";
                             break;
                         case 8:
                             cadena[8] += d[i].getValor()+"\n";
                             break;
                         case 9:
                             cadena[9] += d[i].getValor()+"\n";
                             break;
                     }
                 }
                 if (Ajustes.Boleano) {
                     for (int i = 1; i <= 9; i++) {
                         textView[i].setText(cadena[i]);
                         Thread.sleep(10);
                     }
                     Ajustes.Boleano = false;
                 }
             }
             catch (Exception e) {
                 System.out.println("Fallo en la lectura del buffer");
                 System.out.println("Excepción " + e);
             }
         }

    }

}







