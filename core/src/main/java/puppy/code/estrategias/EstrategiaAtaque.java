package puppy.code.estrategias;

import com.badlogic.gdx.graphics.Texture;

import puppy.code.modelo.Proyectil;

public interface EstrategiaAtaque {

    Proyectil crearProyectil(
            Texture textura,
            boolean danino,
            int puntos
    );
}