package puppy.code;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;

public class AtaqueVampiro
        implements EstrategiaAtaque {

    private final EstrategiaAtaque ataqueVertical;
    private final EstrategiaAtaque ataqueHorizontal;
    private final EstrategiaAtaque ataqueDiagonal;

    public AtaqueVampiro(
            float velocidadVertical,
            float velocidadHorizontal,
            float velocidadDiagonalX,
            float velocidadDiagonalY) {

        ataqueVertical =
                new AtaqueVertical(
                        velocidadVertical
                );

        ataqueHorizontal =
                new AtaqueHorizontal(
                        velocidadHorizontal
                );

        ataqueDiagonal =
                new AtaqueDiagonal(
                        velocidadDiagonalX,
                        velocidadDiagonalY
                );
    }

    @Override
    public Proyectil crearProyectil(
            Texture textura,
            boolean danino,
            int puntos) {

        int tipoAtaque = MathUtils.random(2);

        switch (tipoAtaque) {
            case 0:
                return ataqueVertical.crearProyectil(
                        textura,
                        danino,
                        puntos
                );

            case 1:
                return ataqueHorizontal.crearProyectil(
                        textura,
                        danino,
                        puntos
                );

            default:
                return ataqueDiagonal.crearProyectil(
                        textura,
                        danino,
                        puntos
                );
        }
    }
}