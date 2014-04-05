package net.sww.input.components;

import net.sww.input.components.ActionTrigger;
import com.artemis.Component;

public class FourDirectionController extends Component {

    private float speed;
    private ActionTrigger upTrigger;
    private ActionTrigger downTrigger;
    private ActionTrigger leftTrigger;
    private ActionTrigger rightTrigger;
    
    public FourDirectionController(float speed, ActionTrigger up, ActionTrigger down,
            ActionTrigger left, ActionTrigger right) {
        this.speed = speed;
        upTrigger = up;
        downTrigger = down;
        leftTrigger = left;
        rightTrigger = right;
    }
    
    public float getSpeed() {
        return speed;
    }
    
    public boolean isUp() {
        return upTrigger.isActivated();
    }
    
    public boolean isDown() {
        return downTrigger.isActivated();
    }
    
    public boolean isLeft() {
        return leftTrigger.isActivated();
    }
    
    public boolean isRight() {
        return rightTrigger.isActivated();
    }
}