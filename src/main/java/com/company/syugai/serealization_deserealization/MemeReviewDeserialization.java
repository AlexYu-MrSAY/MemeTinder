package com.company.syugai.serealization_deserealization;

import com.company.syugai.model.Meme;
import com.company.syugai.model.MemeReview;
import com.company.syugai.model.User;
import com.company.syugai.services.Service;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MemeReviewDeserialization extends StdDeserializer<MemeReview> {
    private final Service<User, Integer> userService;
    private final Service<Meme, Integer> memeService;

    protected MemeReviewDeserialization(Service<User, Integer> userService, Service<Meme, Integer> memeService){
        super(MemeReview.class);
        this.userService = userService;
        this.memeService = memeService;
    }

    @Override
    public MemeReview deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        JsonNode root = p.getCodec().readTree(p);
        int id = root.get("id").asInt();
        int userId = root.get("user").asInt();
        User user = userService.findById(userId);
        int memeId = root.get("memeId").asInt();
        Meme meme = memeService.findById(memeId);
        boolean rating = root.get("rating").asBoolean();
        String stringData = root.get("date").asText();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate data = LocalDate.parse(stringData, formatter);

        return new MemeReview(id, user, meme, rating, data);
    }
}
