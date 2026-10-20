package puppy.code;

import com.badlogic.gdx.graphics.Texture;

public interface EstrategiaAtaque {

    Proyectil crearProyectil(
            Texture textura,
            boolean danino,
            int puntos
    );
}