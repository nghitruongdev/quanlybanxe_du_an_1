package com.ultramotor.dao;

import java.sql.ResultSetMetaData;
import java.util.List;

public abstract class UltraDAO<Entity, ID> {

    public ResultSetMetaData meta;
    protected String TABLE_NAME;
    protected String SELECT_BY_ID_SQL;
    protected String SELECT_ALL_SQL;

    public abstract int insert(Entity e);

    public abstract int update(Entity e);

    public abstract int delete(ID id);

    public abstract List<Entity> selectBySQL(String sql, Object... args);

    public Entity selectByID(ID id) {
        List<Entity> list = selectBySQL(SELECT_BY_ID_SQL, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public List<Entity> selectAll() {
        return selectBySQL(SELECT_ALL_SQL);
    }

}
