package com.company.syugai.services;

import com.company.syugai.model.MemeReview;
import com.j256.ormlite.dao.Dao;

public class MemeReviewService extends AbstractService<MemeReview, Integer>{
    public MemeReviewService(Dao<MemeReview, Integer> dao){
        super(dao);
    }
}
