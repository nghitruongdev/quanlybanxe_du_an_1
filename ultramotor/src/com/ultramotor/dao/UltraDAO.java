/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.dao;

import java.util.List;
import javax.sql.rowset.CachedRowSet;

/**
 *
 * @author nghipc
 */
public interface UltraDAO<Entity, ID> {

    void insert(Entity e);

    void update(Entity e);

    void delete(ID id);

    Entity selectByID(ID id);

    List<Entity> selectAll();

    List<Entity> selectBySQL(String sql, Object... args);

    CachedRowSet getRowSet();

}
