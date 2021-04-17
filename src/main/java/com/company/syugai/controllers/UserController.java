package com.company.syugai.controllers;

import com.company.syugai.exception.ApplicationException;
import com.company.syugai.model.User;
import com.company.syugai.services.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.javalin.http.Context;
import org.mindrot.jbcrypt.BCrypt;

public class UserController extends AuthorizedController<User, Integer>{
    public UserController(Service<User, Integer> service, ObjectMapper objectMapper){
        super(service, objectMapper, User.class);
    }

    @Override
    public Service<User, Integer> userService() {
        return getService();
    }

    @Override
    boolean isAuthorized(User user, Context context) {
        String login = context.basicAuthCredentials().getUsername();
        String password = context.basicAuthCredentials().getPassword();
        if(user.getLogin().equals(login) && BCrypt.checkpw(password, user.getPassword())){
            return true;
        }

        switch (context.method()){
            case "GET":
                return isAuthorizedGet(user, context);
            case "POST":
                return true;
            case "PATCH":
                return isAuthorizedPatch(user, context);
            case "DELETE":
                return isAuthorizedDelete(user, context);
            default:
                throw new ApplicationException();
        }
    }

    private boolean isAuthorizedGet(User user, Context context){
        Integer id = context.pathParam("id", Integer.class).getOrNull();
        if(id == null){
            return false;
        } else {
            return id.equals(user.getId());
        }
    }

    private boolean isAuthorizedPatch(User user, Context context){
        Integer id = context.pathParam("id", Integer.class).get();
        if(id == null){
            return false;
        } else {
            return id.equals(user.getId());
        }
    }

    private boolean isAuthorizedDelete(User user, Context context){
        Integer id = context.pathParam("id", Integer.class).get();
        if(id == null){
            return false;
        } else {
            return id.equals(user.getId());
        }
    }
}
