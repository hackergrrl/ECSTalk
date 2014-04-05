package net.sww.input.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import net.sww.input.components.ActionTrigger;
import net.sww.input.components.KeyboardBindingMap;
import java.util.Map.Entry;

public class KeyboardInputSystem extends EntityProcessingSystem implements InputProcessor {
    
    @Mapper ComponentMapper<KeyboardBindingMap> keyboardBindingMapper;

    private int[] state;

    public KeyboardInputSystem() {
        super(Aspect.getAspectForAll(KeyboardBindingMap.class));
        state = new int[256];
        for(int i=0; i < state.length; i++) {
            state[i] = -2;
        }
        
        Gdx.input.setInputProcessor(this);
    }
    
    @Override
    protected void end() {
        // Mark all freshly down keys as stalely down.
        for(int i=0; i < state.length; i++) {
            if(state[i] == 1) {
                state[i] = 2;
            }
            if(state[i] == -1) {
                state[i] = -2;
            }
        }
    }

    @Override
    protected void process(Entity entity) {
        KeyboardBindingMap bindingMap = keyboardBindingMapper.get(entity);

        for(Entry<Integer,ActionTrigger> entry : bindingMap.getBindings().entrySet()) {
            // Activate or deactivate the trigger depending on the input state.
            if(state[entry.getKey()] == 1) {
                entry.getValue().activate();
            } else if(state[entry.getKey()] == -1) {
                entry.getValue().deactivate();
            }
        }
    }

    @Override
    public boolean keyDown(int i) {
        state[i] = 1;
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        state[i] = -1;
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}