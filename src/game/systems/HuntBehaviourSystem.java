package game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.managers.GroupManager;
import com.artemis.managers.TagManager;
import com.artemis.systems.EntityProcessingSystem;
import com.artemis.utils.ImmutableBag;
import com.badlogic.gdx.math.Vector2;
import game.components.HuntBehaviour;
import java.util.Random;
import net.sww.physics.components.Physics;
import net.sww.base.components.Position;

public class HuntBehaviourSystem extends EntityProcessingSystem {
    
    @Mapper ComponentMapper<Position> positionMapper;
    @Mapper ComponentMapper<Physics> physicsMapper;
    @Mapper ComponentMapper<HuntBehaviour> huntMapper;

    public HuntBehaviourSystem() {
        super(Aspect.getAspectForAll(Position.class, Physics.class, HuntBehaviour.class));
    }

    @Override
    protected void process(Entity entity) {
        Position position = positionMapper.get(entity);
        Physics physics = physicsMapper.get(entity);
        HuntBehaviour hunt = huntMapper.get(entity);
        
        if(hunt.target == null || !hunt.target.isActive()) {
            Random random = new Random();
            ImmutableBag<Entity> humans = world.getManager(GroupManager.class).getEntities("humans");
            if(humans.size() > 0) {
                hunt.target = humans.get(random.nextInt(humans.size()));
            }
        } else {
            Vector2 dir = positionMapper.get(hunt.target).getPosition()
                    .cpy()
                    .sub(position.getPosition())
                    .nor()
                    .scl(hunt.speed * world.getDelta());
            physics.getVelocity().add(dir);
            
            if(positionMapper.get(hunt.target).getPosition().dst(position.getPosition()) <= 1) {
                hunt.target.deleteFromWorld();
            }
            
        }

        Entity player = world.getManager(TagManager.class).getEntity("player");
        if(player != null && positionMapper.get(player).getPosition().dst(position.getPosition()) <= 16) {
            entity.deleteFromWorld();
        }
    }
}