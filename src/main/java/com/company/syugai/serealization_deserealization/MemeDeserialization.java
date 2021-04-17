package com.company.syugai.serealization_deserealization;

import com.company.syugai.model.Meme;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MemeDeserialization extends StdDeserializer<Meme> {
    public MemeDeserialization(){
        super(Meme.class);
    }

    @Override
    public Meme deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode root = p.getCodec().readTree(p);
        int id = root.get(Meme.ID).asInt();
        String link = root.get(Meme.LINK).asText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate data = LocalDate.now();
        String data1 = String.valueOf(data);
        data = LocalDate.parse(data1, formatter);

        return new Meme(id, link, data);
    }
}
