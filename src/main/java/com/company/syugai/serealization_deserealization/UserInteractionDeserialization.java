package com.company.syugai.serealization_deserealization;

import com.company.syugai.model.User;
import com.company.syugai.model.UserInteraction;
import com.company.syugai.services.Service;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserInteractionDeserialization extends StdDeserializer<UserInteraction> {
    private final Service<User, Integer> userService;

    protected UserInteractionDeserialization(Service<User, Integer> userService){
        super(UserInteraction.class);
        this.userService = userService;
    }

    @Override
    public UserInteraction deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode root = p.getCodec().readTree(p);
        int id = root.get("id").asInt();
        int source = root.get("source").asInt();
        User trueSource = userService.findById(source);
        int target = root.get("target").asInt();
        User trueTarget = userService.findById(target);
        boolean reaction = root.get("reaction").asBoolean();
        String data = root.get("date").asText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate trueData = LocalDate.parse(data, formatter);

        return new UserInteraction(id, trueSource, trueTarget, reaction, trueData);
    }
}
