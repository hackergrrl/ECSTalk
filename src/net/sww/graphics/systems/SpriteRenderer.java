package net.sww.graphics.systems;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.annotations.Mapper;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import net.sww.graphics.components.Sprite;
import net.sww.base.components.Position;
import net.sww.graphics.components.Tint;

public class SpriteRenderer extends EntityProcessingSystem {

    private SpriteBatch spriteBatch;

    @Mapper ComponentMapper<Position> positionMapper;
    @Mapper ComponentMapper<Sprite> spriteMapper;
    @Mapper ComponentMapper<Tint> tintMapper;

    public SpriteRenderer(SpriteBatch batch) {
        super(Aspect.getAspectForAll(Position.class, Sprite.class));

        this.spriteBatch = batch;
    }
    
    @Override
    protected void begin() {
        spriteBatch.begin();
    }
    
    @Override
    protected void end() {
        spriteBatch.end();
    }
    
    @Override
    protected void process(Entity entity) {
        Position position = positionMapper.get(entity);
        Sprite sprite = spriteMapper.get(entity);
        Tint tint = tintMapper.get(entity);
        if(tint != null) {
            spriteBatch.setColor(tint.getColor());
        }

        spriteBatch.draw(sprite.getSprite(),
                position.getPosition().x-sprite.getSprite().getRegionWidth()/2,
                position.getPosition().y-sprite.getSprite().getRegionHeight()/2);
        
        spriteBatch.setColor(Color.WHITE);
    }
}