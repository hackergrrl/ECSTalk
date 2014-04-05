package game;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class ComponentsInSpace {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 800;
        config.height = 600;
        config.useGL20 = true;
//        config.fullscreen = true;
        config.title = "Components.. IN SPACE!";
        new LwjglApplication(new TestGame(), config);
    }    
}