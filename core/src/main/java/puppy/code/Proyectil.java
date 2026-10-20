package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Proyectil {

    private final Rectangle area;
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
        this.area = new Rectangle(
                x,
                y,
                ancho,
                alto
        );
        this.velocidadX = velocidadX;
        this.velocidadY = velocidadY;
        this.danino = danino;
        this.puntos = puntos;
    }

    public Rectangle getArea() {
        return area;
    }

    public boolean esDanino() {
        return danino;
    }

    public int getPuntos() {
        return puntos;
    }

    public void actualizar(float delta) {
        area.x += velocidadX * delta;
        area.y += velocidadY * delta;
    }

    public boolean colisionaCon(Rectangle otraArea) {
        return area.overlaps(otraArea);
    }

    public boolean estaFueraDePantalla(
            float anchoPantalla,
            float altoPantalla) {

        return area.x + area.width < 0
                || area.x > anchoPantalla
                || area.y + area.height < 0
                || area.y > altoPantalla;
    }

    public void dibujar(SpriteBatch batch) {
        batch.draw(
                textura,
                area.x,
                area.y,
                area.width,
                area.height
        );
    }
}