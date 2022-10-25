package com.example.moverioapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Ajustes extends AppCompatActivity {

   // Spinner spinnerUbicacion =findViewById(R.id.Spinner_ubicacion1);

    private static boolean Boleano=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajustes);

    }

    public boolean getBoleano(){
        return Boleano;
    }

    // ArrayAdapter<CharSequence>adapter=ArrayAdapter.createFromResource(this, R.array.Spinner_ubicacion1, android.R.layout.simple_spinner_item);

   // adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
   // spinnerUbicacion.setAdapter(adapter);

}