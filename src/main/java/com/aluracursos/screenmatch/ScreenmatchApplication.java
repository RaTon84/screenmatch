package com.aluracursos.screenmatch;

import com.aluracursos.screenmatch.service.ConsumoAPI;
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
	}
}
