package net.sww.physics.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class Physics extends Component {

    private float mass;
    private Vector2 velocity;
    
    public Physics(Vector2 vel, float mass) {
        this.mass = mass;
        velocity = vel.cpy();
    }

    public float getMass() {
        return mass;
    }

    public Vector2 getVelocity() {
        return velocity;
    }
}