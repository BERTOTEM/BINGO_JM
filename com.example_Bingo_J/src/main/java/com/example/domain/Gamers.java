/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.domain;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import lombok.Data;
import org.springframework.stereotype.Service;

/**
 *
 * @author julian mazo
 */
@Data
@Service
public class Gamers {

    /**
     * id de la parsona conectada
     */
    String _id;
    /**
     * nombre de la persona en el lobby
     */
    String name;
    /**
     * email de la persona
     */
    String email;
    /**
     * clave de la persona
     */
    String password;
    /**
     * cracion de la persona
     */
    String createdAt;
    /**
     * modificacion de la persona
     */
    String updatedAt;
    String __v;
    /**
     * Json con todas las personas conecadas en lobby
     */
    String Json;
    /**
     * variabel con el json en formato de String
     */
    String respuesta = "";
    /**
     * array con todos las personas conectadas
     */
    Gamers[] jugadores;

    /**
     * metodo para darle un formato a la cadena de texto
     *
     * @return variabel con el json en formato de String
     */
    public String leer_Json() {
        String url = "http://localhost:4000/get";
        try {
            respuesta = peticionHttpGet(url);
            respuesta = respuesta.substring(0, respuesta.length() - 1);
            respuesta = respuesta.replace("{\"Jugadores\":", "");

        } catch (Exception e) {
            // Manejar excepción
            e.printStackTrace();
        }
        return respuesta;

    }

    /**
     * metodo para hacer ua peticion y volverlo un cadena
     *
     * @param urlParaVisitar LA URL DE LA PETICION JSON
     * @return EL JSON EN FORMATO DE STRING
     * @throws Exception
     */
    public static String peticionHttpGet(String urlParaVisitar) throws Exception {
        // Esto es lo que vamos a devolver
        StringBuilder resultado = new StringBuilder();
        // Crear un objeto de tipo URL
        URL url = new URL(urlParaVisitar);

        // Abrir la conexión e indicar que será de tipo GET
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");
        // Búferes para leer
        BufferedReader rd = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
        String linea;
        // Mientras el BufferedReader se pueda leer, agregar contenido a resultado
        while ((linea = rd.readLine()) != null) {
            resultado.append(linea);
        }
        // Cerrar el BufferedReader
        rd.close();
        // Regresar resultado, pero como cadena, no como StringBuilder
        return resultado.toString();
    }

    /**
     * se insatancia una clase gson para dar formato a la cadena de texto
     *
     * @return devuelve la lista con todasd las personas conectadas
     */
    public Gamers[] Formato_json() {
        Gson gson = new Gson();
        Json = leer_Json();
        jugadores = gson.fromJson(Json,
                Gamers[].class);

        return jugadores;
    }

    @Override
    public String toString() {
        return name + "\n" + email + "\n\n";
    }

}
