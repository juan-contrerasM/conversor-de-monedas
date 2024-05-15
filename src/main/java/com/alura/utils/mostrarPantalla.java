package com.alura.utils;

import com.alura.model.Conversion;

import java.util.Scanner;

public class mostrarPantalla {
   private  Scanner teclado = new Scanner(System.in);
    public int mostrarMenu() {

        System.out.println("""
                ***************************************
                sea bienvenido al conversor de monedas
                 
                1) Dolar =>> peso argentino
                2) Peso argentino =>> Dolar
                3)Dolar =>> Real brasilero
                4)Real brasilero =>> Dolar
                5)Dolar ==> Peso Colombiano
                6)Peso colombiano =>> Dolar
                7)salir
                8)ver conversiones
                9)Ingresar siglas de las monenas a convertir
                Elija una opcion valida
                *************************************""");
        return Integer.parseInt(teclado.next());
    }

    public double preguntarValorConvertir(){
        System.out.println("Ingrese el valor a convertir");
        return Double.parseDouble(teclado.next());

    }

    public void mostrarConversionFinal(Conversion conversion, double valorConvertir){
        System.out.println("El valor " + valorConvertir + "[" + conversion.getSiglasMonedaLocal() + "]  corresponde al valor final de =>>" + conversion.getResultado() + "[" + conversion.getSiglasMonedaDestino() + "]");
    }

    public String siglasMOnedaLocal(){
        System.out.println("Ingrese las siglas de la moneda local");
        return teclado.next().toUpperCase();
    }

    public String siglasMonedaDestino(){
        System.out.println("Ingrese las siglas de la moneda de destino ");
        return teclado.next().toUpperCase();

    }



}
