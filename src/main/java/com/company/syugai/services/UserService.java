package com.company.syugai.services;

import com.company.syugai.model.User;
import com.j256.ormlite.dao.Dao;

public class UserService extends AbstractService<User, Integer>{
    public UserService(Dao<User, Integer> dao){
        super(dao);
    }
}
