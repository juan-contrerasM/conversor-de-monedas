package com.alura.principal;

import com.alura.model.Conversion;
import com.alura.model.ConversionExchengeRate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class Cliente {
    private Gson gson;

    public Cliente() {
        gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();


    }

    public Conversion pedirDatos(String direccion) throws IOException, InterruptedException {
        try {
            HttpClient client = HttpClient.newHttpClient(); //cliente servidor, por eso se llama cliente, somos el cliente
            HttpRequest request = HttpRequest.newBuilder()// que es lo que vaos a pedir , utiliza patron de diseño builder
                    .uri(URI.create(direccion))
                    .build();


            // coje el cliente anterior mente creado y se crea una http.response y envia los datos que pidio
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();

            System.out.println(json);


            ConversionExchengeRate conversionExchengeRate = gson.fromJson(json, ConversionExchengeRate.class);


            //luego se le asigna atra ves del constructor un titulo normal un tituloDto
            Conversion conversion = new Conversion(conversionExchengeRate);
            return conversion;
        } catch (IllegalArgumentException e) {
            System.out.println("error en la uri, veirfique las siglas deben ser en maysuculas y que sean las correctas de ls monedas");
            return null;
        }

    }


    public void guadarArchivo(ArrayList<Conversion> conversiones) throws IOException {
        // se crea el archivo titulo.json
        FileWriter escritura = new FileWriter("conversiones.json");
        // estamos convirtiendo un arrylist de objetos titulos a json
        escritura.write(gson.toJson(conversiones));
        escritura.close();
        System.out.println("Finalizo la ejecucion del programa");
    }


    public ArrayList<Conversion> leerArchivo(String direccion) throws IOException {
        try (FileReader reader = new FileReader(direccion)) {
            // Lee el contenido del archivo
            StringBuilder stringBuilder = new StringBuilder();
            int character;
            while ((character = reader.read()) != -1) {
                stringBuilder.append((char) character);
            }
            String json = stringBuilder.toString();

            // Convierte el JSON a una lista de objetos Conversion
            Type listType = new TypeToken<ArrayList<Conversion>>() {
            }.getType();
            ArrayList<Conversion> conversiones = gson.fromJson(json, listType);

            return conversiones;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            throw e;
        }
    }
}
