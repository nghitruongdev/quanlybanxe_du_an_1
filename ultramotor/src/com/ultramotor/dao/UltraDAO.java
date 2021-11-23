package com.ultramotor.dao;

import java.sql.ResultSetMetaData;
import java.util.List;

public abstract class UltraDAO<Entity, ID> {

    public ResultSetMetaData meta;
    protected String TABLE_NAME;
    protected String SELECT_BY_ID;
    protected String SELECT_ALL;

    public abstract void insert(Entity e);

    public abstract void update(Entity e);

    public abstract void delete(ID id);

    public abstract List<Entity> selectBySQL(String sql, Object... args);

    public Entity selectByID(ID id) {
        List<Entity> list = selectBySQL(SELECT_BY_ID, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<Entity> selectAll() {
        return selectBySQL(SELECT_ALL);
    }

}
