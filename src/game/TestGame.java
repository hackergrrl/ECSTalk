package game;

import com.badlogic.gdx.Game;

public class TestGame extends Game {
    
    GameScreen screen;

    @Override
    public void create() {
        screen = new GameScreen(this);
        setScreen(screen);
    }
    
    public void reset() {
        setScreen(new GameScreen(this));
    }
}