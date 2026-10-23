package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Plataforma {

    private static final float ALTO_VISUAL_MINIMO = 48f;
    private static final float AJUSTE_VISUAL_Y = 17f;
    private static final float MARGEN_COLISION_HORIZONTAL = 10f;

    private final Rectangle areaVisual;
    private final Rectangle hitbox;

    public Plataforma(
            float x,
            float y,
            float ancho,
            float alto) {

        areaVisual = new Rectangle(
                x,
                y,
                ancho,
                alto
        );

        hitbox = new Rectangle(
                x + MARGEN_COLISION_HORIZONTAL,
                y,
                ancho - 2 * MARGEN_COLISION_HORIZONTAL,
                alto
        );
    }

    public Rectangle getArea() {
        return hitbox;
    }

    public void dibujar(
            SpriteBatch batch,
            Texture textura) {

        float altoVisual = Math.max(
                areaVisual.height,
                ALTO_VISUAL_MINIMO
        );

        float posicionVisualY =
                areaVisual.y
                + areaVisual.height
                - altoVisual
                + AJUSTE_VISUAL_Y;

        batch.draw(
                textura,
                areaVisual.x,
                posicionVisualY,
                areaVisual.width,
                altoVisual
        );
    }
}