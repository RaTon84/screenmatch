package com.aluracursos.screenmatch.models;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)

public record DatosEpisodio(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Runtime") String duracion,
        @JsonAlias("imdbRating") String evaluacion,
        @JsonAlias("Episode") Integer numeroEpisodio,
        @JsonAlias("Released") String fechaDeLanzamiento
) {

}
