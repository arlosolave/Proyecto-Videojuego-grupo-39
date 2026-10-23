package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Proyectil {

	private static final float MARGEN_HORIZONTAL = 0.15f;
	private static final float MARGEN_VERTICAL = 0.12f;

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

        float escala = Math.min(
                ancho / textura.getWidth(),
                alto / textura.getHeight()
        );

        float anchoAjustado =
                textura.getWidth() * escala;

        float altoAjustado =
                textura.getHeight() * escala;

        /*
         * Se centra la textura dentro del espacio
         * máximo recibido por el constructor.
         */
        float xAjustada =
                x + (ancho - anchoAjustado) / 2f;

        float yAjustada =
                y + (alto - altoAjustado) / 2f;

        areaVisual = new Rectangle(
                xAjustada,
                yAjustada,
                anchoAjustado,
                altoAjustado
        );

        hitbox = new Rectangle();

        actualizarHitbox();
    }

    private void actualizarHitbox() {
        float margenX =
                areaVisual.width
                * MARGEN_HORIZONTAL;

        float margenY =
                areaVisual.height
                * MARGEN_VERTICAL;

        hitbox.set(
                areaVisual.x + margenX,
                areaVisual.y + margenY,
                areaVisual.width - 2 * margenX,
                areaVisual.height - 2 * margenY
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