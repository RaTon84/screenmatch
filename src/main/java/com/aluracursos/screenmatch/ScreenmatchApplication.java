package com.aluracursos.screenmatch;

import com.aluracursos.screenmatch.models.DatosSerie;
import com.aluracursos.screenmatch.models.DatosEpisodio;
import com.aluracursos.screenmatch.models.DatosTemporada;
import com.aluracursos.screenmatch.principal.Principal;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {SpringApplication.run(ScreenmatchApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {

		Principal principio = new Principal();
		principio.muestraElMenu();


		/* primer enfoque
		System.out.println("HOLA");
		var consumoAPI = new ConsumoAPI();
		var json = consumoAPI.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&apikey=e1bc4c5f&");
		System.out.println(json);
		ConvierteDatos conversor = new ConvierteDatos();

		//serie
		var serie= conversor.obtenerDatos(json, DatosSerie.class);
		System.out.println(serie);

		//episodios
		json = consumoAPI.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&Season=1&episode=1&apikey=e1bc4c5f&");
		var episodio = conversor.obtenerDatos(json, DatosEpisodio.class);
		System.out.println(episodio);

		//temporadas
		List<DatosTemporada> temporadas=new ArrayList<>();
		for (int i = 1; i < serie.totalTemporadas(); i++) {
			json = consumoAPI.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&Season="+i+"&apikey=e1bc4c5f&");
			var temporada = conversor.obtenerDatos(json, DatosTemporada.class);
			temporadas.add(temporada);
		}
		temporadas.forEach(System.out::println);*/

	}
}
