package com.aluracursos.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvierteDatos implements IConvierteDatos {

    private ObjectMapper objectMapper = new ObjectMapper();
    //public DatosSerie obtenerDatos(String json);

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) throws JsonProcessingException {
        return objectMapper.readValue(json,clase);
    }
}
