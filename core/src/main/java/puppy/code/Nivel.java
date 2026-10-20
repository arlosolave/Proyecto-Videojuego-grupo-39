package puppy.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Nivel {

    private final String nombre;
    private final float duracion;
    private float tiempoRestante;
    private final List<Plataforma> plataformas;

    public Nivel(String nombre, float duracion) {
        this.nombre = nombre;
        this.duracion = duracion;
        this.plataformas = new ArrayList<>();
    }

    /*
     * Template Method:
     * establece los pasos comunes para iniciar cualquier nivel.
     */
    public final void iniciar(Lluvia lluvia) {
        tiempoRestante = duracion;
        plataformas.clear();

        configurarPlataformas();

        EstrategiaAtaque estrategia =
                crearEstrategiaAtaque();

        lluvia.setEstrategiaAtaque(estrategia);

        alIniciar();
    }

    protected void configurarPlataformas() {
    	// Por defecto, el nivel no tiene plataformas adicionales.
    };

    protected abstract EstrategiaAtaque crearEstrategiaAtaque();

    /*
     * Hook opcional. Las subclases pueden sobrescribirlo
     * si necesitan hacer algo adicional al comenzar.
     */
    protected void alIniciar() {
    }

    protected final void agregarPlataforma(
            Plataforma plataforma) {

        plataformas.add(plataforma);
    }

    public final void actualizarTiempo(float delta) {
        if (tiempoRestante > 0) {
            tiempoRestante -= delta;

            if (tiempoRestante < 0) {
                tiempoRestante = 0;
            }
        }
    }

    public final boolean estaCompletado() {
        return tiempoRestante <= 0;
    }

    public final String getNombre() {
        return nombre;
    }

    public final float getTiempoRestante() {
        return tiempoRestante;
    }

    public final List<Plataforma> getPlataformas() {
        return Collections.unmodifiableList(plataformas);
    }
}