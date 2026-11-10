package puppy.code.estrategias;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

import puppy.code.modelo.Proyectil;

public class AtaqueVertical
        implements EstrategiaAtaque {

    private final float velocidad;

    public AtaqueVertical(float velocidad) {
        this.velocidad = Math.abs(velocidad);
    }

    @Override
    public Proyectil crearProyectil(
            Texture textura,
            boolean danino,
            int puntos) {

        return new Proyectil(
                textura,
                MathUtils.random(0, 800 - 64),
                480,
                64,
                64,
                0,
                -velocidad,
                danino,
                puntos
        );
    }
}