package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class GestorProyectiles {

    private static final float VELOCIDAD_AGUA_BENDITA =
            180f;

    private Array<Proyectil> proyectiles;

    private long ultimoAtaque;
    private long ultimaAguaBendita;

    private final Texture texturaAguaBendita;
    private Texture texturaAtaque;

    private final Sound sonidoRecoleccion;
    private final Music musica;

    private EstrategiaAtaque estrategiaAtaque;

    private long intervaloAtaques =
            650_000_000L;

    private long intervaloAguaBendita =
            6_000_000_000L;
    
    public GestorProyectiles(
            Texture texturaAguaBendita,
            Texture texturaAtaque,
            Sound sonidoRecoleccion,
            Music musica,
            EstrategiaAtaque estrategiaAtaque) {

        this.texturaAguaBendita =
                texturaAguaBendita;

        this.texturaAtaque =
                texturaAtaque;

        this.sonidoRecoleccion =
                sonidoRecoleccion;

        this.musica = musica;

        this.estrategiaAtaque =
                estrategiaAtaque;
    }

    public void iniciar() {
        proyectiles = new Array<>();

        ultimoAtaque = TimeUtils.nanoTime();
        ultimaAguaBendita = TimeUtils.nanoTime();

        musica.setLooping(true);
        musica.play();
    }

    public void configurarIntervalos(
            float segundosAtaques,
            float segundosAguaBendita) {

        intervaloAtaques =
                (long) (
                        segundosAtaques
                        * 1_000_000_000L
                );

        intervaloAguaBendita =
                (long) (
                        segundosAguaBendita
                        * 1_000_000_000L
                );
    }
    
    public void setEstrategiaAtaque(
            EstrategiaAtaque estrategiaAtaque) {

        if (estrategiaAtaque == null) {
            throw new IllegalArgumentException(
                    "La estrategia no puede ser nula."
            );
        }

        this.estrategiaAtaque =
                estrategiaAtaque;
    }

    private void crearAtaqueEnemigo() {
        Proyectil ataque =
                estrategiaAtaque.crearProyectil(
                        texturaAtaque,
                        true,
                        0
                );

        proyectiles.add(ataque);

        ultimoAtaque = TimeUtils.nanoTime();
    }
    
    public void configurarTexturaAtaque(
            Texture texturaAtaque) {

        if (texturaAtaque == null) {
            throw new IllegalArgumentException(
                    "La textura de ataque no puede ser nula."
            );
        }

        this.texturaAtaque = texturaAtaque;
    }
    
    private void crearAguaBendita() {
        float posicionX =
                MathUtils.random(0, 800 - 64);

        Proyectil aguaBendita =
                new Proyectil(
                        texturaAguaBendita,
                        posicionX,
                        480,
                        64,
                        64,
                        0,
                        -VELOCIDAD_AGUA_BENDITA,
                        false,
                        1
                );

        proyectiles.add(aguaBendita);

        ultimaAguaBendita =
                TimeUtils.nanoTime();
    }

    private boolean hayAguaBenditaActiva() {
        for (Proyectil proyectil : proyectiles) {
            if (!proyectil.esDanino()) {
                return true;
            }
        }

        return false;
    }

    public boolean actualizarMovimiento(
            Heroe heroe) {

        long tiempoActual =
                TimeUtils.nanoTime();

        if (tiempoActual - ultimoAtaque
                > intervaloAtaques) {

            crearAtaqueEnemigo();
        }

        if (tiempoActual - ultimaAguaBendita
                > intervaloAguaBendita
                && !hayAguaBenditaActiva()) {

            crearAguaBendita();
        }

        float delta =
                Gdx.graphics.getDeltaTime();

        for (int i = proyectiles.size - 1;
                i >= 0;
                i--) {

            Proyectil proyectil =
                    proyectiles.get(i);

            proyectil.actualizar(delta);

            if (proyectil.estaFueraDePantalla(
                    800,
                    480)) {

                proyectiles.removeIndex(i);
                continue;
            }

            if (proyectil.colisionaCon(
                    heroe.getArea())) {

                if (proyectil.esDanino()) {
                    heroe.danar();

                    proyectiles.removeIndex(i);

                    if (heroe.getVidas() <= 0) {
                        return false;
                    }

                } else {
                    heroe.recolectarAguaBendita(
                            proyectil.getPuntos()
                    );

                    sonidoRecoleccion.play();
                    proyectiles.removeIndex(i);
                }
            }
        }

        return true;
    }

    public void dibujar(
            SpriteBatch batch) {

        for (Proyectil proyectil
                : proyectiles) {

            proyectil.dibujar(batch);
        }
    }

    public void limpiarProyectiles() {
        proyectiles.clear();

        /*
         * Reinicia ambos temporizadores para evitar que
         * aparezcan elementos inmediatamente al cambiar.
         */
        ultimoAtaque = TimeUtils.nanoTime();
        ultimaAguaBendita = TimeUtils.nanoTime();
    }

    public void pausar() {
        musica.pause();
    }

    public void continuar() {
        if (!musica.isPlaying()) {
            musica.play();
        }
    }

}