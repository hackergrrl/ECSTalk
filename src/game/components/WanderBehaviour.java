package game.components;

import com.artemis.Component;
import com.badlogic.gdx.math.Vector2;

public class WanderBehaviour extends Component {

    public float speed;
    
    public Vector2 direction;
    public float countdown;
    
    public WanderBehaviour(float speed) {
        this.speed = speed;
    }
}