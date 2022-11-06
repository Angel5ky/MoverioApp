package com.example.moverioapp;

//Clase encargada de pintar los datos en pantalla según la posición deseada


import android.widget.TextView;

class ActualizarTexto implements Runnable {
    TextView[] textView;
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
                for(int j=1;j<=9;j++){
                    cadena[j]=new String();
                }

                //Bucle de asignación de valores a las clases Datos
                for(int i=1;i<=20;i++){
                    d[i].setValor(Datos[i-1]);
                }
                for (String y: cadena) {
                    y = "";
                }
                //Bucle de lectura y posicionador de datos

                for (int i = 1; i <= 20; i++) {
                    switch (d[i].getPosicion()) {
                        case 0:
                            break;
                        case 1:
                            cadena[1] += d[i].getValor() + "\n";
                            break;
                        case 2:
                            cadena[2] += d[i].getValor() + "\n";
                            break;
                        case 3:
                            cadena[3] += d[i].getValor() + "\n";
                            break;
                        case 4:
                            cadena[4] += d[i].getValor() + "\n";
                            break;
                        case 5:
                            cadena[5] += d[i].getValor() + "\n";
                            break;
                        case 6:
                            cadena[6] += d[i].getValor() + "\n";
                            break;
                        case 7:
                            cadena[7] += d[i].getValor() + "\n";
                            break;
                        case 8:
                            cadena[8] += d[i].getValor() + "\n";
                            break;
                        case 9:
                            cadena[9] += d[i].getValor() + "\n";
                            break;
                    }
                }

                for (int i = 1; i <= 9; i++) {
                    textView[i].setText(cadena[i]);
                    Thread.sleep(10);
                }


            }
            catch (Exception e) {
                System.out.println("Fallo en la lectura del buffer");
                System.out.println("Excepción " + e);
            }
        }

    }

}







