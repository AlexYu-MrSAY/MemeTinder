package com.company.syugai.services;

import com.company.syugai.exception.ApplicationException;
import com.company.syugai.model.Model;
import com.j256.ormlite.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public abstract class AbstractService<T extends Model<U>, U> implements Service<T, U>{
    private final static Logger LOGGER = LoggerFactory.getLogger(AbstractService.class);


    private Dao<T, U> dao;

    public AbstractService(Dao<T, U> dao) {
        this.dao = dao;
    }

    public Dao<T, U> getDao() {
        return dao;
    }

    public void setDao(Dao<T, U> dao) {
        this.dao = dao;
    }

    public AbstractService() {
        super();
    }

    @Override
    public List<T> findAll() {
        try {
            List<T> result = dao.queryForAll();
            LOGGER.debug("Found result = {} by querying all records.", result);
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new ApplicationException(throwables);
        }
    }

    @Override
    public T findById(U id) {
        try {
            T model = dao.queryForId(id);
            LOGGER.debug("Finding record = {} by id = {}", model, id);
            return model;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new ApplicationException(throwables);
        }
    }

    @Override
    public void save(T model) {
        try {
            dao.create(model);
            LOGGER.debug("Created record = {} ", model);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new ApplicationException(throwables);
        }
    }

    @Override
    public void update(T model) {
        try {
            dao.update(model);
            LOGGER.debug("Updated record = {} ", model);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new ApplicationException(throwables);
        }
    }

    @Override
    public void delete(T model) {
        try {
            dao.delete(model);
            LOGGER.debug("Deleted record = {} ", model);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new ApplicationException(throwables);
        }
    }

    @Override
    public void deleteById(U id) {
        try {
            T model = dao.queryForId(id);
            dao.delete(model);
            LOGGER.debug("Record = {} deleted by id = {} ", model, id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new ApplicationException(throwables);
        }
    }

    @Override
    public <V> List<T> findByColumn(String columnName, V columnValue) {
        try {
            List<T> result = dao.queryBuilder().where().eq(columnName, columnValue).query();
            LOGGER.debug("Found result = {} by searching column = {} equals value = {}", result, columnName, columnValue);
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new ApplicationException(throwables);
        }
    }

    @Override
    public <V> T findByColumnUnique(String columnName, V columnValue) {
        try {
            T result = dao.queryBuilder().where().eq(columnName, columnValue).queryForFirst();
            return result;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new ApplicationException(throwables);
        }
    }
}
