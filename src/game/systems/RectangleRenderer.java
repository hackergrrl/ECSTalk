package game.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import game.components.RectangleShape;
import net.sww.base.components.Position;
import net.sww.graphics.components.Tint;

public class RectangleRenderer extends EntityProcessingSystem {

    private ShapeRenderer shapeRenderer;

    @Mapper ComponentMapper<Position> positionMapper;
    @Mapper ComponentMapper<RectangleShape> rectMapper;
    @Mapper ComponentMapper<Tint> tintMapper;

    public RectangleRenderer(ShapeRenderer shapeRenderer) {
        super(Aspect.getAspectForAll(Position.class, RectangleShape.class));

        this.shapeRenderer = shapeRenderer;
    }
    
    @Override
    protected void begin() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
    }
    
    @Override
    protected void end() {
        shapeRenderer.end();
    }
    
    @Override
    protected void process(Entity entity) {
        Position position = positionMapper.get(entity);
        RectangleShape rect = rectMapper.get(entity);
        Tint tint = tintMapper.get(entity);
        if(tint != null) {
            shapeRenderer.setColor(tint.getColor());
        }

        shapeRenderer.rect(position.getPosition().x-rect.size/2,
                position.getPosition().y-rect.size/2,
                rect.size, rect.size);
        
        shapeRenderer.setColor(Color.WHITE);
    }

}