package com.swingx.table;

public interface ModelEvent<Entity> {

    void update(Entity e);

    void delete(Entity e);
}
