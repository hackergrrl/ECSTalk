package game.components;

import com.artemis.Component;
import com.artemis.Entity;

public class HuntBehaviour extends Component {

    public float speed;
    
    public Entity target;
    
    public HuntBehaviour(float speed) {
        this.speed = speed;
    }
}