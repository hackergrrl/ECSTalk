package net.sww.input.components;

import com.artemis.Component;

public class ActionTrigger extends Component {

    private String name;
    private int activationCount;
    
    public ActionTrigger(String actionName) {
        this.name = actionName;
    }
    
    public String getActionName() {
        return name;
    }

    public void activate() {
        activationCount++;
    }
    
    public void deactivate() {
        assert(activationCount > 0);
        activationCount = Math.max(0, activationCount-1);
    }
    
    public boolean isActivated() {
        return activationCount > 0;
    }
}