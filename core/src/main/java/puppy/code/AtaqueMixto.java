package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class AtaqueMixto implements EstrategiaAtaque {

    private final EstrategiaAtaque ataqueVertical;
    private final EstrategiaAtaque ataqueHorizontal;

    public AtaqueMixto(
            float velocidadVertical,
            float velocidadHorizontal) {

        ataqueVertical =
                new AtaqueVertical(
                        velocidadVertical
                );

        ataqueHorizontal =
                new AtaqueHorizontal(
                        velocidadHorizontal
                );
    }

    @Override
    public Proyectil crearProyectil(
            Texture textura,
            boolean danino,
            int puntos) {

        if (MathUtils.randomBoolean()) {
            return ataqueVertical.crearProyectil(
                    textura,
                    danino,
                    puntos
            );
        }

        return ataqueHorizontal.crearProyectil(
                textura,
                danino,
                puntos
        );
    }
}