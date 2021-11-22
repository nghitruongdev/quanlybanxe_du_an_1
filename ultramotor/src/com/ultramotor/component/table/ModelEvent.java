package com.ultramotor.component.table;

public interface ModelEvent<Entity, Key> {

    void update(Entity e);

    void delete(Key id);
}
