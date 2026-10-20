package puppy.code;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Plataforma {

    private final Rectangle area;

    public Plataforma(
            float x,
            float y,
            float ancho,
            float alto) {

        area = new Rectangle(x, y, ancho, alto);
    }

    public Rectangle getArea() {
        return area;
    }

    public void dibujar(ShapeRenderer renderer) {
        renderer.setColor(Color.GRAY);
        renderer.rect(
                area.x,
                area.y,
                area.width,
                area.height
        );
    }
}