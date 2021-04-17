package com.company.syugai.serealization_deserealization;

import com.company.syugai.model.Meme;
import com.company.syugai.model.MemeReview;
import com.company.syugai.model.User;
import com.company.syugai.services.Service;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class MemeReviewSerialization extends StdSerializer<MemeReview> {
    private final Service<User, Integer> userService;
    private final Service<Meme, Integer> memeService;

    protected MemeReviewSerialization(Service<User, Integer> userService, Service<Meme, Integer> memeService){
        super(MemeReview.class);
        this.memeService = memeService;
        this.userService = userService;
    }

    @Override
    public void serialize(MemeReview memeReview, JsonGenerator g, SerializerProvider serializer) throws IOException {
        g.writeStartObject();
        g.writeNumberField("id", memeReview.getId());
        g.writeStringField("user", String.valueOf(memeReview.getUser()));
        g.writeStringField("meme", String.valueOf(memeReview.getMeme()));
        g.writeStringField("rating", String.valueOf(memeReview.isRating()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        g.writeStringField("date", memeReview.getDate().format(formatter));
        g.writeEndObject();
    }
}
