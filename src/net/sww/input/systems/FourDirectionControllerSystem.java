package net.sww.input.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import net.sww.input.components.FourDirectionController;
import net.sww.physics.components.Physics;

public class FourDirectionControllerSystem extends EntityProcessingSystem {
    
    @Mapper ComponentMapper<FourDirectionController> controllerMapper;
    @Mapper ComponentMapper<Physics> physicsMapper;
    
    public FourDirectionControllerSystem() {
        super(Aspect.getAspectForAll(Physics.class, FourDirectionController.class));
    }

    @Override
    protected void process(Entity entity) {
        Physics physics = physicsMapper.get(entity);
        FourDirectionController controller = controllerMapper.get(entity);
        float speed = controller.getSpeed();

        if(controller.isUp()) {
            physics.getVelocity().add(0, speed*world.getDelta());
        }
        if(controller.isDown()) {
            physics.getVelocity().add(0, -speed*world.getDelta());
        }
        if(controller.isLeft()) {
            physics.getVelocity().add(-speed*world.getDelta(), 0);
        }
        if(controller.isRight()) {
            physics.getVelocity().add(speed*world.getDelta(), 0);
        }
    }
}