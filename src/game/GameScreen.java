package game;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import game.components.GameWorld;
import game.systems.GoblinRespawnerSystem;
import game.systems.HuntBehaviourSystem;
import net.sww.input.components.ActionTrigger;
import net.sww.input.components.FourDirectionController;
import net.sww.input.components.KeyboardBindingMap;
import game.systems.RectangleRenderer;
import net.sww.input.systems.FourDirectionControllerSystem;
import net.sww.input.systems.KeyboardInputSystem;
import net.sww.physics.systems.PhysicsMotionSystem;
import game.systems.PhysicsScreenBounceSystem;
import game.systems.WanderBehaviourSystem;
import java.util.Random;

public class GameScreen implements Screen {
    
    private static Stage stage;
    private TestGame game;
    private OrthographicCamera camera;
    
    World world;
    
    GameScreen(TestGame game) {
        stage = new Stage();
        this.game = game;

        camera = new OrthographicCamera();
        stage.setCamera(camera);
        
        world = new World();
        
        world.setManager(new GroupManager());
        world.setManager(new TagManager());

        Random rand = new Random();
        
        // Game world.
        Entity root = world.createEntity();
        root.addComponent(new GameWorld());
        root.addToWorld();
        
        // Four-Direction controller /w keyboard bindings.
        ActionTrigger up = new ActionTrigger("up");
        ActionTrigger down = new ActionTrigger("down");
        ActionTrigger left = new ActionTrigger("left");
        ActionTrigger right = new ActionTrigger("right");
        FourDirectionController fourDirController = new FourDirectionController(1500, up, down, left, right);
        KeyboardBindingMap kbMap = new KeyboardBindingMap();
        kbMap.addBinding(Keys.W, up);
        kbMap.addBinding(Keys.S, down);
        kbMap.addBinding(Keys.A, left);
        kbMap.addBinding(Keys.D, right);
        
        // Create a bunch of villagers.
        for(int i=0; i < 12; i++) {
            Vector2 pos = new Vector2(
                    rand.nextFloat()*stage.getWidth(),
                    rand.nextFloat()*stage.getHeight());
            Entity villager = EntityFactory.createVillager(world, pos);
        }
        
        // Create a bunch of FREAKING GOBLINS.
        for(int i=0; i < 4; i++) {
            Vector2 pos = new Vector2(
                    rand.nextFloat()*stage.getWidth(),
                    rand.nextFloat()*stage.getHeight());
            Entity goblin = EntityFactory.createGoblin(world, pos);
        }
        
        Entity player = EntityFactory.createHero(world, new Vector2(250,250));
        player.addComponent(fourDirController);
        player.addComponent(kbMap);
        world.getManager(TagManager.class).register("player", player);

        
        world.setSystem(new GoblinRespawnerSystem());

        world.setSystem(new PhysicsMotionSystem());
        world.setSystem(new PhysicsScreenBounceSystem());
        
        world.setSystem(new WanderBehaviourSystem());
        world.setSystem(new HuntBehaviourSystem());
        
        world.setSystem(new RectangleRenderer(new ShapeRenderer()));

        world.setSystem(new KeyboardInputSystem());
        world.setSystem(new FourDirectionControllerSystem());

        world.initialize();
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
  
        stage.draw();
        stage.act();
        
        world.setDelta(delta);
        world.process();
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }
}