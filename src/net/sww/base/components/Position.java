package net.sww.base.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class Position extends Component {

    private Vector2 position;
//    private Vector2 scale;
    private float rotation;
    
    public Position(Vector2 pos) {
        position = pos.cpy();
        rotation = 0;
    }
    
    public Position(Vector2 pos, float rot) {
        position = pos.cpy();
        rotation = rot;
    }

    public Vector2 getPosition() {
        return position;
    }
    
    public float getRotation() {
        return rotation;
    }
    public void setRotation(float rot) {
        rotation = rot;
    }
}