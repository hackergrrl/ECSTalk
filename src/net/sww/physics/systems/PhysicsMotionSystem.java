package net.sww.physics.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import net.sww.physics.components.Physics;
import net.sww.base.components.Position;

public class PhysicsMotionSystem extends EntityProcessingSystem {
    
    @Mapper ComponentMapper<Physics> physicsMapper;
    @Mapper ComponentMapper<Position> positionMapper;

    public PhysicsMotionSystem() {
        super(Aspect.getAspectForAll(Position.class, Physics.class));
    }

    @Override
    protected void process(Entity entity) {
        Physics physics = physicsMapper.get(entity);
        Position position = positionMapper.get(entity);

        position.getPosition().add(physics.getVelocity().cpy().scl(world.getDelta()));
        
        physics.getVelocity().scl(0.85f);
    }
}