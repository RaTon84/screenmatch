package com.aluracursos.screenmatch;

import com.aluracursos.screenmatch.models.DatosSerie;
import com.aluracursos.screenmatch.models.DatosEpisodio;
import com.aluracursos.screenmatch.service.ConsumoAPI;
import com.aluracursos.screenmatch.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {SpringApplication.run(ScreenmatchApplication.class, args);

	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("HOLA");
		var consumoAPI = new ConsumoAPI();
		var json = consumoAPI.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&apikey=e1bc4c5f&");
		System.out.println(json);
		ConvierteDatos conversor = new ConvierteDatos();
		var datos= conversor.obtenerDatos(json, DatosSerie.class);
		System.out.println(datos);

		json = consumoAPI.obtenerDatos("https://www.omdbapi.com/?t=game+of+thrones&episode=1&apikey=e1bc4c5f&");
		DatosEpisodio ddatos = conversor.obtenerDatos(json, DatosEpisodio.class);
		System.out.println(ddatos);

	}
}
