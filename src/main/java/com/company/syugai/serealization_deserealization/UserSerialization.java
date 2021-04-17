package com.company.syugai.serealization_deserealization;

import com.company.syugai.model.User;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class UserSerialization extends StdSerializer<User> {
    public UserSerialization(){
        super(User.class);
    }

    @Override
    public void serialize(User user, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", user.getId());
        jsonGenerator.writeStringField("firstName", user.getFirst_name());
        jsonGenerator.writeStringField("lastName", user.getLast_name());
        jsonGenerator.writeStringField("sex", user.getSex());
        jsonGenerator.writeStringField("country", user.getCountry());
        jsonGenerator.writeStringField("city", user.getCity());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        jsonGenerator.writeStringField("birthday", user.getBirthday().format(formatter));
        jsonGenerator.writeStringField("data", user.getData().format(formatter));
        jsonGenerator.writeStringField("phone", user.getPhone());
        jsonGenerator.writeStringField("role", String.valueOf(user.getRole()));
        jsonGenerator.writeEndObject();
    }
}
