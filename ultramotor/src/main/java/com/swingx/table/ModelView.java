
package com.swingx.table;

import com.ultramotor.entity.Entity;
import java.awt.event.ActionListener;

public class ModelView {
    private Entity entity;
    private ActionListener ls;

    public ModelView(Entity entity, ActionListener ls) {
        this.entity = entity;
        this.ls = ls;
    }
    
    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public ActionListener getLs() {
        return ls;
    }

    public void setLs(ActionListener ls) {
        this.ls = ls;
    }
    
    
}
