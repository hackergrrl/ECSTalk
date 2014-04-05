package game;

import com.artemis.Entity;
import com.artemis.World;
import com.artemis.managers.GroupManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import game.components.HuntBehaviour;
import game.components.RectangleShape;
import game.components.WanderBehaviour;
import net.sww.base.components.Position;
import net.sww.graphics.components.Tint;
import net.sww.physics.components.Physics;

public class EntityFactory {

    public static Entity createVillager(World world, Vector2 pos) {
            Entity entity = world.createEntity();

            entity.addComponent(new Position(pos));

            entity.addComponent(new Physics(new Vector2(0,0), 5.0f));
            
            entity.addComponent(new RectangleShape(16));
            
            if(Math.random() <= 0.5f) {
                entity.addComponent(new Tint(Color.PINK));
            } else {
                entity.addComponent(new Tint(new Color(0.36f,0.28f,0.25f,1)));
            }
            
            entity.addComponent(new WanderBehaviour(600));

            entity.addToWorld();
            world.getManager(GroupManager.class).add(entity, "humans");
            return entity;
    }

    public static Entity createHero(World world, Vector2 pos) {
            Entity entity = world.createEntity();

            entity.addComponent(new Position(pos));

            entity.addComponent(new Physics(new Vector2(0,0), 5.0f));
            
            entity.addComponent(new RectangleShape(16));
            
            entity.addComponent(new Tint(Color.MAGENTA));
            
            entity.addToWorld();            
            return entity;
    }

    public static Entity createGoblin(World world, Vector2 pos) {
            Entity entity = world.createEntity();

            entity.addComponent(new Position(pos));

            entity.addComponent(new Physics(new Vector2(0,0), 5.0f));
            
            entity.addComponent(new RectangleShape(16));
            
            entity.addComponent(new Tint(Color.GREEN));
            
            entity.addComponent(new HuntBehaviour(900));
            
            entity.addToWorld();
            world.getManager(GroupManager.class).add(entity, "goblins");
            return entity;
    }
}