package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Proyectil {

    private static final float MARGEN_HORIZONTAL = 12f;
    private static final float MARGEN_VERTICAL = 8f;

    private final Rectangle areaVisual;
    private final Rectangle hitbox;

    private final Texture textura;
    private final float velocidadX;
    private final float velocidadY;
    private final boolean danino;
    private final int puntos;

    public Proyectil(
            Texture textura,
            float x,
            float y,
            float ancho,
            float alto,
            float velocidadX,
            float velocidadY,
            boolean danino,
            int puntos) {

        this.textura = textura;
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
        this.danino = danino;
        this.puntos = puntos;

        areaVisual = new Rectangle(
                x,
                y,
                ancho,
                alto
        );

        hitbox = new Rectangle();

        actualizarHitbox();
    }

    private void actualizarHitbox() {
        hitbox.set(
                areaVisual.x + MARGEN_HORIZONTAL,
                areaVisual.y + MARGEN_VERTICAL,
                areaVisual.width
                        - 2 * MARGEN_HORIZONTAL,
                areaVisual.height
                        - 2 * MARGEN_VERTICAL
        );
    }

    public void actualizar(float delta) {
        areaVisual.x += velocidadX * delta;
        areaVisual.y += velocidadY * delta;

        actualizarHitbox();
    }

    public void dibujar(SpriteBatch batch) {
        batch.draw(
                textura,
                areaVisual.x,
                areaVisual.y,
                areaVisual.width,
                areaVisual.height
        );
    }

    public boolean colisionaCon(
            Rectangle otraArea) {

        return hitbox.overlaps(otraArea);
    }

    public boolean estaFueraDePantalla(
            float anchoPantalla,
            float altoPantalla) {

        return areaVisual.x
                    + areaVisual.width < 0
                || areaVisual.x > anchoPantalla
                || areaVisual.y
                    + areaVisual.height < 0
                || areaVisual.y > altoPantalla;
    }

    public Rectangle getArea() {
        return areaVisual;
    }

    public boolean esDanino() {
        return danino;
    }

    public int getPuntos() {
        return puntos;
    }
}