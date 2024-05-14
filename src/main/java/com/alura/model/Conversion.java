package com.alura.model;

import lombok.*;

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
   public Conversion(ConversionExchengeRate conversionExchengeRate){
    this.siglasMonedaLocal=conversionExchengeRate.base_code();
    this.siglasMonedaDestino=conversionExchengeRate.target_code();
    this.conversion=  conversionExchengeRate.conversion_rate();
   }


   public void calcularConversionFinal(double valorConvertir){
       resultado=conversion*valorConvertir;
   }




}
