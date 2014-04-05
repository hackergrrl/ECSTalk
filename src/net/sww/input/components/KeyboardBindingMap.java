package net.sww.input.components;

import com.artemis.Component;
import java.util.HashMap;
import java.util.Map;

public class KeyboardBindingMap extends Component {
    
    private Map<Integer, ActionTrigger> bindings;
    
    public KeyboardBindingMap() {
        bindings = new HashMap<>();
    }
    
    public Map<Integer, ActionTrigger> getBindings() {
        return bindings;
    }
    
    public void addBinding(int key, ActionTrigger trigger) {
        bindings.put(key, trigger);
    }
    
    public ActionTrigger getTrigger(int key) {
        return bindings.get(key);
    }
}