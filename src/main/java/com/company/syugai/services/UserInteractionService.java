package com.company.syugai.services;

import com.company.syugai.model.UserInteraction;
import com.j256.ormlite.dao.Dao;

public class UserInteractionService extends AbstractService<UserInteraction, Integer>{
    public UserInteractionService(Dao<UserInteraction, Integer> dao){
        super(dao);
    }
}
