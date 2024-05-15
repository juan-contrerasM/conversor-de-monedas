package com.alura.principal;

import com.alura.model.Conversion;
import com.alura.utils.mostrarPantalla;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        //atributos
        ArrayList<Conversion>conversions= new ArrayList<>();
        mostrarPantalla mostrarPantalla = new mostrarPantalla();
        String direccion = "https://v6.exchangerate-api.com/v6/f430bc32c5be4b4d4d088efb/pair/";
        double valorConvertir=0;
        double resultado = 0;
        int opcion = mostrarPantalla.mostrarMenu();
        Cliente cliente=null;

        while (opcion!=7) {
             cliente = new Cliente(); // SE CREA CADA VEZ QUE SE VA A CONSULTAR
            Conversion conversion = null;// SE CREA UN NUEVO OBJETO CONVERSION
            valorConvertir = mostrarPantalla.preguntarValorConvertir(); // SE LLAMA AL MENU PARA MOSTAR EN PANTALLA

            switch (opcion) {
                case 1:
                    automatizarPrecedimiento("USD", "ARS", valorConvertir, mostrarPantalla, cliente, direccion,conversions);
                    break;

                case 2:
                    automatizarPrecedimiento("ARS", "USD", valorConvertir, mostrarPantalla, cliente, direccion,conversions);
                    break;

                case 3:
                    automatizarPrecedimiento("USD", "BRL", valorConvertir, mostrarPantalla, cliente, direccion,conversions);
                    break;

                case 4:
                    automatizarPrecedimiento("BRL", "USD", valorConvertir, mostrarPantalla, cliente, direccion,conversions);
                    break;
                case 5:
                    automatizarPrecedimiento("USD", "COP", valorConvertir, mostrarPantalla, cliente, direccion,conversions);
                    break;
                case 6:
                    automatizarPrecedimiento("COP", "USD", valorConvertir, mostrarPantalla, cliente, direccion,conversions);
                    break;
                case 8:
                    ArrayList<Conversion>conversions1=cliente.leerArchivo("conversiones.json");
                    if(conversions1==null) {
                        System.out.println("No hay histroial de conversiones");
                    }else {
                        System.out.println("Este es el historial de las conversiones");
                        for (int i = 0; i < conversions1.size(); i++) {
                            System.out.println(conversions1.get(i).toString());
                        }
                    }
                    break;
                case 9:
                    automatizarPrecedimiento(mostrarPantalla.siglasMOnedaLocal(), mostrarPantalla.siglasMonedaDestino(), valorConvertir,mostrarPantalla,cliente,direccion,conversions);
                    break;


            }
            opcion = mostrarPantalla.mostrarMenu();
        }
        cliente.guadarArchivo(conversions);



    }


    // ACA SE CONSUME LA API
    private static Conversion pedirFiltrarConversion(String siglasMonedaLocal, String siglasMonedDestino, Cliente cliente, String direccion) throws IOException, InterruptedException {
        Conversion conversion = cliente.pedirDatos(direccion + siglasMonedaLocal + "/" + siglasMonedDestino);
        return conversion;
    }


    // ESTE METODO PERMIETE QUE NO COPIEMOS LAS 6 VECES LAS 3 MISMA LINEAS
    private static void automatizarPrecedimiento(String siglasMonedaLocal, String siglasMonedDestino, double valorConvertir, mostrarPantalla mostrarPantalla, Cliente cliente, String direccion,ArrayList<Conversion>conversions) throws IOException, InterruptedException {
        Conversion conversion = pedirFiltrarConversion(siglasMonedaLocal, siglasMonedDestino, cliente, direccion);
        conversion.calcularConversionFinal(valorConvertir);
        mostrarPantalla.mostrarConversionFinal(conversion, valorConvertir);
        conversion.horaFehca();
        conversions.add(conversion);



    }

}