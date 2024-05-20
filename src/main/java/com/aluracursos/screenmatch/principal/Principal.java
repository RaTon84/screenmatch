package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.models.DatosEpisodio;
import com.aluracursos.screenmatch.models.DatosSerie;
import com.aluracursos.screenmatch.models.DatosTemporada;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

    public class Principal {

        private Scanner teclado = new Scanner(System.in);
        private final String URL = "https://www.omdbapi.com/?t=";
        private final String APIKEY = "&apikey=e1bc4c5f&";
        private ConsumoAPI consumoAPI = new ConsumoAPI();
        private ConvierteDatos conversor = new ConvierteDatos();
        public void muestraElMenu(){
            System.out.println("Escribe el nombre de la série que deseas buscar");
            var nombreSerie = teclado.nextLine();
            //obtener la serie
            var json = consumoAPI.obtenerDatos(URL + nombreSerie.replace(" ", "+") + APIKEY);
            DatosSerie datos = null;
            try {
                datos = conversor.obtenerDatos(json, DatosSerie.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            System.out.println(datos.totalTemporadas());

            //obtener los datos de las temporada
            List<DatosTemporada> temporadas = new ArrayList<>();
            for (int i = 1; i <= datos.totalTemporadas(); i++) {
                json = consumoAPI.obtenerDatos(URL + nombreSerie.replace(" ", "+") + "&Season=" + i + APIKEY);
                DatosTemporada datosTemporada = null;
                try {
                    datosTemporada = conversor.obtenerDatos(json, DatosTemporada.class);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                temporadas.add(datosTemporada);
            }
            //temporadas.forEach(System.out::println);

            for (int i = 0; i < datos.totalTemporadas(); i++) {
                List<DatosEpisodio> episodiosTemporadas = temporadas.get(i).episodios();
                for (int j = 0; j < episodiosTemporadas.size(); j++) {
                    System.out.println(episodiosTemporadas.get(j).titulo());
                }
            }
            //lambda con clousures
            temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));
        }
    }


