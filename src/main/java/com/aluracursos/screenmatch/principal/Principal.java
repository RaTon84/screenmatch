package com.aluracursos.screenmatch.principal;

import com.aluracursos.screenmatch.model.DatosEpisodio;
import com.aluracursos.screenmatch.model.DatosSerie;
import com.aluracursos.screenmatch.model.DatosTemporada;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

        private Scanner teclado = new Scanner(System.in);
        private final String URL = "https://www.omdbapi.com/?t=";
        private final String APIKEY = "&apikey=e1bc4c5f&";
        private ConsumoAPI consumoAPI = new ConsumoAPI();
        private ConvierteDatos conversor = new ConvierteDatos();

        public void muestraElMenu(){
            System.out.println("Escribe el nombre de la série que deseas buscar");
            var nombreSerie = teclado.nextLine();

            //obtener la serie pasarlos a DatosSeries
            var json = consumoAPI.obtenerDatos(URL + nombreSerie.replace(" ", "+") + APIKEY);
            DatosSerie datos = null;
            try {
                datos = conversor.obtenerDatos(json, DatosSerie.class);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            System.out.println(datos.totalTemporadas());

            //-------------------------------------------------------------------

            //obtener los datos de las temporada pasarlos a DatosTemporada
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
            temporadas.forEach(System.out::println);

            for (int i = 0; i < datos.totalTemporadas(); i++) {
                List<DatosEpisodio> episodiosTemporadas = temporadas.get(i).episodios();
                for (int j = 0; j < episodiosTemporadas.size(); j++) {
                    System.out.println(episodiosTemporadas.get(j).titulo());
                }
            }
            //lambda con clousures
            temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

            //ejemplos de stream-lambda
            List<String> nombres = Arrays.asList("Genesys","Eric","Maria","Brenda");

            nombres.stream()
                    .sorted()
                    .limit(2)
                    .filter(n -> n.startsWith("E"))
                    .map(n -> n.toUpperCase())
                    .forEach(System.out::println);

            List<DatosEpisodio> datosEpisodios = temporadas.stream()
                    .flatMap(t -> t.episodios().stream())
                    .collect(Collectors.toList());
            System.out.println("\n Top 5 episodios");
            /*
            datosEpisodios.stream()
                    .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A"))
                    .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())
                    .limit(5)
                    .forEach(System.out::println);

            List<Episodio> episodios = temporadas.stream()
                    .flatMap(t -> t.episodios().stream()
                            .map(d -> new Episodio(t.numero(), d)))
                    .collect(Collectors.toList());

            episodios.forEach(System.out::println);

            System.out.println("a partir de que año deseas ver los episodios?");
            var fecha = teclado.nextInt();
            teclado.nextLine();

            LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            episodios.stream()
                    .filter(e -> e.getFechaDeLanzamiento() != null && e.getFechaDeLanzamiento().isAfter(fechaBusqueda))
                    .forEach(e -> System.out.println(
                            "Temporada: " + e.getTemporada() +
                                    " Episodio: " + e.getTitulo() +
                                    " Fecha de Lanzamiento: " + e.getFechaDeLanzamiento().format(formatter)
                    ));

        }*/
        }
    }


