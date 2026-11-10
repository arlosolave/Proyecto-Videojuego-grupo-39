package puppy.code.modelo;

import java.util.ArrayList;
import java.util.List;

import puppy.code.gestores.GestorProyectiles;
import puppy.code.niveles.Nivel;
import puppy.code.niveles.NivelPuente;
import puppy.code.niveles.NivelEntrada;
import puppy.code.niveles.NivelRecepcion;
import puppy.code.niveles.NivelConde;

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

    public void iniciar(GestorProyectiles gestorProyectiles) {
        indiceNivelActual = 0;

        gestorProyectiles.limpiarProyectiles();
        getNivelActual().iniciar(gestorProyectiles);
    }

    public boolean avanzarNivel(
            GestorProyectiles lluvia,
            Heroe heroe) {

        if (!getNivelActual().estaCompletado()
                || !tieneSiguienteNivel()) {

            return false;
        }

        indiceNivelActual++;

        heroe.ganarVida();
        lluvia.limpiarProyectiles();

        getNivelActual().iniciar(lluvia);

        heroe.colocarEnSuelo(
                getNivelActual().getAlturaSuelo()
        );
        
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