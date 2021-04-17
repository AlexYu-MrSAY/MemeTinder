package com.company.syugai.serealization_deserealization;

import com.company.syugai.model.User;
import com.company.syugai.model.UserRole;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserDeserialization extends StdDeserializer<User> {
    public UserDeserialization(){
        super(User.class);
    }

    @Override
    public User deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        JsonNode root = p.getCodec().readTree(p);
        int id = root.get(User.ID).asInt();
        String login = root.get(User.LOGIN).asText();
        String password = root.get(User.PASSWORD).asText();
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        String first_name = root.get(User.FIRST_NAME).asText();
        String last_name = root.get(User.LAST_NAME).asText();
        String sex = root.get(User.SEX).asText();
        String country = root.get(User.COUNTRY).asText();
        String city = root.get(User.CITY).asText();
        String birthdayString = root.get(User.BIRTHDAY).asText();
        LocalDate birthday = LocalDate.parse(birthdayString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate data = LocalDate.now();
        String phone = root.get(User.PHONE).asText();
        String role = root.get(User.ROLE).asText();
        UserRole trueRole = UserRole.valueOf(role);

        return new User(id, login, hashedPassword, first_name, last_name, sex, country, city, birthday, data ,phone, trueRole);
    }
}
