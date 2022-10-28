package com.example.moverioapp;//======================================================================================================================
//Clase de conexión y lectura de datos de PLC
//======================================================================================================================

import android.widget.TextView;

import com.example.moverioapp.moka7.S7;
import com.example.moverioapp.moka7.S7Client;

public class PlcConnection implements Runnable {
    private final S7Client Client;

    private String IpAddr = "192.168.1.253";
    private int res;
    private int selectedArea = S7.S7AreaDB;
    private int dBNumber = 1;
    private int offset = 0;
    private int length = 320;
    private int rack = 0;
    private int slot = 0;
    private boolean running = false;
    public boolean leido = false;

    //Array datos extraidos del PLC
    private int [] Datos = new int [20];

    public PlcConnection(String IP,int DB, int longitudDB, int rack, int slot) {
        Client = new S7Client();
        this.IpAddr = IP;
        this.dBNumber = DB;
        this.length = longitudDB;
        this.rack = rack;
        this.slot = slot;
        running = true;

    }

    public void terminate() {
        running = false;
    }

    @Override
    public void run() {


        Client.SetConnectionType(S7.OP);
        res=Client.ConnectTo(IpAddr, rack, slot);

        if (res==0){
            byte[] data = new byte[65536];
            running = true;
            while (running) {
                try{
                    res = Client.ReadArea(selectedArea, dBNumber, offset, length, data);
                    for(int i=0,y=0;i<=length-2;i=i+2,y++){
                        Datos[y]=S7.GetWordAt(data,i);
                    }
                    leido = true;

                }
                catch (Exception e) {
                    System.out.println("Fallo en la lectura del buffer");
                    System.out.println("Excepción " + e);
                    running = false;
                }
            }
        }
        else{
            System.out.println("Error de conexión al PLC");
            for(int y=0;y<=19;y++){
                Datos[y]=255;
            }
            leido = true;

        }
    }
    public int[] getDatos(){
        return Datos;
    }
}
