package com.ultramotor.component.table;

import com.ultramotor.entity.Entity;

public class ModelAction {

    private Entity entity;
    private ModelEvent event;

    public ModelAction(Entity entity, ModelEvent event) {
        this.entity = entity;
        this.event = event;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public ModelEvent getEvent() {
        return event;
    }

    public void setEvent(ModelEvent event) {
        this.event = event;
    }
    
    
}
