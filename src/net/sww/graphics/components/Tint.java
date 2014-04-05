package net.sww.graphics.components;

import com.artemis.Component;
import com.badlogic.gdx.graphics.Color;

public class Tint extends Component {

    private Color color;
    
    public Tint(Color color) {
        this.color = color.cpy();
    }

    public Color getColor() {
        return color;
    }
}