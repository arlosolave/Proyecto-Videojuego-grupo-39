package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class AtaqueHorizontal
        implements EstrategiaAtaque {

    private final float velocidad;

    public AtaqueHorizontal(float velocidad) {
        this.velocidad = Math.abs(velocidad);
    }

    @Override
    public Proyectil crearProyectil(
            Texture textura,
            boolean danino,
            int puntos) {

        return new Proyectil(
                textura,
                800,
                MathUtils.random(20, 480 - 64),
                64,
                64,
                -velocidad,
                0,
                danino,
                puntos
        );
    }
}