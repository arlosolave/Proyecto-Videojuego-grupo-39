package puppy.code;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Nivel {

    private final String nombre;

    private final ConfiguracionNivel configuracion;

    private float tiempoRestante;

    private final List<Plataforma> plataformas;

    public Nivel(
            String nombre,
            ConfiguracionNivel configuracion) {

        this.nombre = nombre;
        this.configuracion = configuracion;
        this.plataformas = new ArrayList<>();
    }

    public final void iniciar(
            GestorProyectiles gestorProyectiles) {

        tiempoRestante =
                configuracion.getDuracion();

        plataformas.clear();

        configurarPlataformas();

        EstrategiaAtaque estrategia =
                crearEstrategiaAtaque();

        gestorProyectiles.setEstrategiaAtaque(
                estrategia
        );

        gestorProyectiles.configurarIntervalos(
                configuracion
                        .getIntervaloAtaques(),

                configuracion
                        .getIntervaloAguaBendita()
        );

        alIniciar();
    }

    protected void configurarPlataformas() {
        // Por defecto, no hay plataformas adicionales.
    }

    protected abstract EstrategiaAtaque
            crearEstrategiaAtaque();

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
        return Collections.unmodifiableList(
                plataformas
        );
    }
    public final TipoEscenario getTipoEscenario() {
        return configuracion.getTipoEscenario();
    }

    public final float getAlturaSuelo() {
        return configuracion.getAlturaSuelo();
    }
}