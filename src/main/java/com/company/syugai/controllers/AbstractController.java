package com.company.syugai.controllers;

import com.company.syugai.model.Model;
import com.company.syugai.services.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import io.javalin.http.Context;
import java.util.List;

public abstract class AbstractController<T extends Model<U>, U> implements Controllers<T, U>{
    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractController.class);

    private Service<T, U> service;
    private ObjectMapper objectMapper;
    private final Class<T> modelClass;

    public AbstractController(Service<T, U> service, ObjectMapper objectMapper, Class<T> modelClass) {
        this.service = service;
        this.objectMapper = objectMapper;
        this.modelClass = modelClass;
    }

    public Service<T, U> getService() {
        return service;
    }

    public void setService(Service<T, U> service) {
        this.service = service;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public Class<T> getModelClass() {
        return modelClass;
    }

    @Override
    public void getAll(Context context) {
        List<T> models = service.findAll();
        try {
            context.result(objectMapper.writeValueAsString(models));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getOne(Context context, U id) {
        T model = service.findById(id);
        try {
            context.result(objectMapper.writeValueAsString(model));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void postOne(Context context) {
        try {
            T model = objectMapper.readValue(context.body(), modelClass);
            service.save(model);
            context.status(201);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void patch(Context context, U id) {
        try {
            T model = objectMapper.readValue(context.body(), modelClass);
            model.setId(id);
            service.update(model);
            context.status(200);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOne(Context context, U id) {
        service.deleteById(id);
        context.status(204);
    }
}
