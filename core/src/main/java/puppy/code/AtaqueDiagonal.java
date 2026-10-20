package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class AtaqueDiagonal
        implements EstrategiaAtaque {

    private final float velocidadHorizontal;
    private final float velocidadVertical;

    public AtaqueDiagonal(
            float velocidadHorizontal,
            float velocidadVertical) {

        this.velocidadHorizontal =
                Math.abs(velocidadHorizontal);

        this.velocidadVertical =
                Math.abs(velocidadVertical);
    }

    @Override
    public Proyectil crearProyectil(
            Texture textura,
            boolean danino,
            int puntos) {

        float posicionY =
                MathUtils.random(260, 480 - 64);

        return new Proyectil(
                textura,
                800,
                posicionY,
                64,
                64,
                -velocidadHorizontal,
                -velocidadVertical,
                danino,
                puntos
        );
    }
}