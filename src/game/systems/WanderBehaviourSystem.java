package game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Vector2;
import game.components.WanderBehaviour;
import java.util.Random;
import net.sww.physics.components.Physics;
import net.sww.base.components.Position;

public class WanderBehaviourSystem extends EntityProcessingSystem {
    
    @Mapper ComponentMapper<Position> positionMapper;
    @Mapper ComponentMapper<Physics> physicsMapper;
    @Mapper ComponentMapper<WanderBehaviour> wanderMapper;

    public WanderBehaviourSystem() {
        super(Aspect.getAspectForAll(Position.class, Physics.class, WanderBehaviour.class));
    }

    @Override
    protected void process(Entity entity) {
        Position position = positionMapper.get(entity);
        Physics physics = physicsMapper.get(entity);
        WanderBehaviour wander = wanderMapper.get(entity);
        
        if(wander.countdown <= 0) {
            Random rand = new Random();
            Vector2 randomPos = new Vector2(rand.nextFloat()*800, rand.nextFloat()*600);
            wander.direction = randomPos
                    .sub(position.getPosition())
                    .nor()
                    .scl(wander.speed * world.getDelta());
            wander.countdown = 2;
        }
        wander.countdown -= world.getDelta();
        
        physics.getVelocity().add(wander.direction);
    }
}