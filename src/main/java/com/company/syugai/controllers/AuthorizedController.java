package com.company.syugai.controllers;

import com.company.syugai.model.Model;
import com.company.syugai.model.User;
import com.company.syugai.services.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.http.Context;
import io.javalin.http.ForbiddenResponse;
import io.javalin.http.UnauthorizedResponse;
import org.mindrot.jbcrypt.BCrypt;

import static com.company.syugai.model.User.LOGIN;

public abstract class AuthorizedController<T extends Model<U>, U> extends AbstractController<T, U>{

    public AuthorizedController(Service<T, U> service, ObjectMapper objectMapper, Class<T> modelClass) {
        super(service, objectMapper, modelClass);
    }

    abstract public Service<User, Integer> userService();

    public User checkBasicAuthCredentials(String login, String password){
        User user = userService().findByColumnUnique(LOGIN, login);
        if(BCrypt.checkpw(password, user.getPassword())){
            return user;
        } else {
            throw new UnauthorizedResponse();
        }
    }

    public User actor(Context context){
        if(context.basicAuthCredentialsExist()){
            String login = context.basicAuthCredentials().getUsername();
            String password = context.basicAuthCredentials().getPassword();
            return checkBasicAuthCredentials(login, password);
        } else{
            throw new UnauthorizedResponse();
        }
    }

    abstract boolean isAuthorized(User user, Context context);

    public boolean isAuthorized(Context context){
        User actor = actor(context);
        return isAuthorized(actor, context);
    }

    @Override
    public void getAll(Context context) {
        if(isAuthorized(context)){
            super.getAll(context);
        } else{
            throw new ForbiddenResponse();
        }
    }

    @Override
    public void getOne(Context context, U id) {
        if(isAuthorized(context)) {
            super.getOne(context, id);
        } else{
            throw new ForbiddenResponse();
        }
    }

    @Override
    public void postOne(Context context) {
        if(isAuthorized(context)){
            super.postOne(context);
        } else{
            throw new ForbiddenResponse();
        }
    }

    @Override
    public void patch(Context context, U id) {
        if(isAuthorized(context)){
            super.patch(context, id);
        } else{
            throw new ForbiddenResponse();
        }
    }

    @Override
    public void deleteOne(Context context, U id) {
        if(isAuthorized(context)){
            super.deleteOne(context, id);
        } else{
            throw new ForbiddenResponse();
        }
    }
}
