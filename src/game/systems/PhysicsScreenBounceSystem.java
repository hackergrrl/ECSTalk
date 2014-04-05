package game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import net.sww.physics.components.Physics;
import net.sww.base.components.Position;

public class PhysicsScreenBounceSystem extends EntityProcessingSystem {
    
    @Mapper ComponentMapper<Position> positionMapper;
    @Mapper ComponentMapper<Physics> physicsMapper;

    public PhysicsScreenBounceSystem() {
        super(Aspect.getAspectForAll(Physics.class));
    }

    @Override
    protected void process(Entity entity) {
        Position position = positionMapper.get(entity);
        Physics physics = physicsMapper.get(entity);
        Rectangle rect = new Rectangle(0,0,800,600);
        if(!rect.contains(position.getPosition())) {
            Vector2 testPoint;
            float delta = world.getDelta();

            // Move physics back by a step.
            position.getPosition().sub(physics.getVelocity().cpy().scl(delta));
            
            // X bounce test.
            testPoint = position.getPosition().cpy().add(new Vector2(physics.getVelocity().x*delta,0));
            if(!rect.contains(testPoint)) {
                physics.getVelocity().x *= -1;
            }
            
            // Y bounce test.
            testPoint = position.getPosition().cpy().add(new Vector2(0,physics.getVelocity().y*delta));
            if(!rect.contains(testPoint)) {
                physics.getVelocity().y *= -1;
            }
        }
    }

}