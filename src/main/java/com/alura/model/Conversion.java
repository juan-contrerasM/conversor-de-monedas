package com.alura.model;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Conversion {
   private String siglasMonedaLocal;
   private  String siglasMonedaDestino;
   private double conversion;
   private  double resultado;
   public String horaConverison;
   public  String fechaConvercio;
   public Conversion(ConversionExchengeRate conversionExchengeRate){
    this.siglasMonedaLocal=conversionExchengeRate.base_code();
    this.siglasMonedaDestino=conversionExchengeRate.target_code();
    this.conversion=  conversionExchengeRate.conversion_rate();
   }
   public void horaFehca(){
       horaConverison=LocalTime.now().toString();
       fechaConvercio=LocalDate.now().toString();
   }


   public void calcularConversionFinal(double valorConvertir){
       resultado=conversion*valorConvertir;
   }




}
