package org.academiadecodigo.bootcamp.Objects;

import org.academiadecodigo.bootcamp.Field;
import org.academiadecodigo.bootcamp.Position.Position;

public abstract class GameObject {

    protected Position position;
    protected boolean destroyed;

    public GameObject(int col, int row, Field field){
        this.position = new Position(col, row, field);
    }

    public Position getPosition(){
        return position;
    }
}
