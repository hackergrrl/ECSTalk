package game.systems;

import com.artemis.Aspect;
import com.artemis.Entity;
import com.artemis.managers.GroupManager;
import com.artemis.systems.EntityProcessingSystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.math.Vector2;
import game.EntityFactory;
import game.components.GameWorld;
import java.util.Random;

public class GoblinRespawnerSystem extends EntityProcessingSystem {
    
    public GoblinRespawnerSystem() {
        super(Aspect.getAspectForAll(GameWorld.class));
    }

    @Override
    protected void process(Entity entity) {
        ImmutableBag<Entity> goblins = world.getManager(GroupManager.class).getEntities("goblins");
        if(goblins.size() <= 0) {
            for(int i=0; i < 5; i++) {
                Random rand = new Random();
                Vector2 pos = new Vector2(
                        rand.nextFloat()*800,
                        rand.nextFloat()*600);
                Entity goblin = EntityFactory.createGoblin(world, pos);
            }
        }
    }
}