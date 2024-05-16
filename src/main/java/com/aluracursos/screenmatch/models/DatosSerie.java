package com.aluracursos.screenmatch.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonIgnoreProperties(ignoreUnknown = true) //ignora los cmpos que no pedimos del json con el true

public record DatosSerie(
        //alias lee
        @JsonAlias("Title") String titulo,
        @JsonAlias("totalSeasons") Integer totalTemporadas,
        @JsonAlias("imdbRating") String evaluacion,
        //property lee y escribe
        @JsonProperty("Language") String lenguaje
        ) {
}
