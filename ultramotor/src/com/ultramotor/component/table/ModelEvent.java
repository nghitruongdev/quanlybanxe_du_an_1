package com.ultramotor.component.table;

public interface ModelEvent<Entity> {

    void update(Entity e);

    void delete(Entity e);
}
