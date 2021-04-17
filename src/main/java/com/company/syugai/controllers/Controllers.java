package com.company.syugai.controllers;
import com.company.syugai.model.Model;
import io.javalin.http.Context;

public interface Controllers <T extends Model<U>, U>{
    void getAll(Context context);
    void getOne(Context context, U id);
    void postOne(Context context);
    void patch(Context context, U id);
    void deleteOne(Context context, U id);
}
