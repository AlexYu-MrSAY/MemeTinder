package com.company.syugai.serealization_deserealization;

import com.company.syugai.model.User;
import com.company.syugai.model.UserInteraction;
import com.company.syugai.services.Service;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class UserInteractionSerialization extends StdSerializer<UserInteraction> {
    private final Service<User, Integer> userService;

    protected UserInteractionSerialization(Service<User, Integer> userService){
        super(UserInteraction.class);
        this.userService = userService;
    }

    @Override
    public void serialize(UserInteraction userInteraction, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", userInteraction.getId());
        jsonGenerator.writeStringField("source", String.valueOf(userInteraction.getSource()));
        jsonGenerator.writeStringField("target", String.valueOf(userInteraction.getTarget()));
        jsonGenerator.writeBooleanField("reaction", userInteraction.isReaction());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        jsonGenerator.writeStringField("date", userInteraction.getDate().format(formatter));
        jsonGenerator.writeEndObject();
    }
}
