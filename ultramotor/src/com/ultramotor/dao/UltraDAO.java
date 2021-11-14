/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ultramotor.dao;

import java.util.List;

/**
 *
 * @author nghipc
 */
public interface UltraDAO<Entity, ID> {
    Entity selectByID(ID id);
    List<Entity> selectAll();
    List<Entity> selectBySQL();
    
    void insert(Entity e);
    void update(Entity e);
    void delete(ID id);
}
