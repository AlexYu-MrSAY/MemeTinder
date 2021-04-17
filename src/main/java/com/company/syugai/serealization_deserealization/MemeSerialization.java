package com.company.syugai.serealization_deserealization;

import com.company.syugai.model.Meme;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class MemeSerialization extends StdSerializer<Meme> {
    public MemeSerialization(){
        super(Meme.class);
    }

    @Override
    public void serialize(Meme meme, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", meme.getId());
        jsonGenerator.writeStringField("link", meme.getLink());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        jsonGenerator.writeStringField("date", meme.getDate().format(formatter));
        jsonGenerator.writeEndObject();
    }
}
