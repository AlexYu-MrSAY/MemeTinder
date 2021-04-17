package com.company.syugai.configuration;

import com.company.syugai.exception.ApplicationException;
import com.company.syugai.model.Meme;
import com.company.syugai.model.MemeReview;
import com.company.syugai.model.User;
import com.company.syugai.model.UserInteraction;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

public class JdbcDatabaseConfiguration implements DatabaseConfiguration{
    private final ConnectionSource source;

    public JdbcDatabaseConfiguration(String jdbcConnectionString) {
        try {
            source = new JdbcConnectionSource(jdbcConnectionString);
            TableUtils.createTableIfNotExists(source, User.class);
            TableUtils.createTableIfNotExists(source, Meme.class);
            TableUtils.createTableIfNotExists(source, MemeReview.class);
            TableUtils.createTableIfNotExists(source, UserInteraction.class);
        } catch (SQLException throwables){
            throwables.printStackTrace();
            throw new ApplicationException("Can not initialize database connection", throwables);
        }
    }

    public ConnectionSource source(){
        return source;
    }
}
