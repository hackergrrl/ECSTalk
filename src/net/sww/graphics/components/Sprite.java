package net.sww.graphics.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Sprite extends Component {
    
    private TextureRegion sprite;
    
    public Sprite(TextureRegion sprite) {
        this.sprite = sprite;
    }
    
    public TextureRegion getSprite() {
        return sprite;
    }
}