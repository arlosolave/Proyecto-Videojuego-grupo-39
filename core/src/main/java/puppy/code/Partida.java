package puppy.code;

import java.util.ArrayList;
import java.util.List;

public class Partida {

    private final List<Nivel> niveles;
    private int indiceNivelActual;

    public Partida() {
        niveles = new ArrayList<>();

        niveles.add(new NivelPuente());
        niveles.add(new NivelEntrada());
        niveles.add(new NivelRecepcion());
        niveles.add(new NivelConde());

        indiceNivelActual = 0;
    }

    public void iniciar(Lluvia lluvia) {
        indiceNivelActual = 0;

        lluvia.limpiarProyectiles();
        getNivelActual().iniciar(lluvia);
    }

    public boolean avanzarNivel(
            Lluvia lluvia,
            Heroe heroe) {

        if (!getNivelActual().estaCompletado()
                || !tieneSiguienteNivel()) {

            return false;
        }

        indiceNivelActual++;

        heroe.ganarVida();
        lluvia.limpiarProyectiles();

        getNivelActual().iniciar(lluvia);

        return true;
    }

    public Nivel getNivelActual() {
        return niveles.get(indiceNivelActual);
    }

    public boolean tieneSiguienteNivel() {
        return indiceNivelActual
                < niveles.size() - 1;
    }
}