package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public final class GestorRecursos {

    private static final GestorRecursos INSTANCIA =
            new GestorRecursos();

    private Texture texturaHeroe;
    private Texture texturaAguaBendita;
    private Texture texturaAtaque;
    private Texture fondoPuente;
    private Texture fondoEntrada;
    private Texture fondoRecepcion;
    private Texture fondoConde;
    private Sound sonidoHerido;
    private Sound sonidoRecoleccion;
    private Music musica;

    private boolean recursosCargados;

    /*
     * Constructor privado:
     * impide crear instancias desde otras clases.
     */
    private GestorRecursos() {
    }

    public static GestorRecursos getInstancia() {
        return INSTANCIA;
    }

    public void cargarRecursos() {
        if (recursosCargados) {
            return;
        }

        fondoPuente = new Texture(
                Gdx.files.internal("fondo_puente.png")
        );

        fondoEntrada = new Texture(
                Gdx.files.internal("fondo_entrada.png")
        );

        fondoRecepcion = new Texture(
                Gdx.files.internal("fondo_recepcion.png")
        );

        fondoConde = new Texture(
                Gdx.files.internal("fondo_conde.png")
        );
        
        texturaHeroe = new Texture(
                Gdx.files.internal("bucket.png")
        );

        texturaAguaBendita = new Texture(
                Gdx.files.internal("drop.png")
        );

        texturaAtaque = new Texture(
                Gdx.files.internal("dropBad.png")
        );

        sonidoHerido = Gdx.audio.newSound(
                Gdx.files.internal("hurt.ogg")
        );

        sonidoRecoleccion = Gdx.audio.newSound(
                Gdx.files.internal("drop.wav")
        );

        musica = Gdx.audio.newMusic(
                Gdx.files.internal("rain.mp3")
        );

        recursosCargados = true;
    }

    public Texture getFondo(
            TipoEscenario tipoEscenario) {

        verificarCarga();

        switch (tipoEscenario) {
            case PUENTE:
                return fondoPuente;

            case ENTRADA:
                return fondoEntrada;

            case RECEPCION:
                return fondoRecepcion;

            case CONDE:
                return fondoConde;

            default:
                throw new IllegalArgumentException(
                        "Escenario desconocido."
                );
        }
    }
    
    private void verificarCarga() {
        if (!recursosCargados) {
            throw new IllegalStateException(
                    "Los recursos no han sido cargados."
            );
        }
    }

    public Texture getTexturaHeroe() {
        verificarCarga();
        return texturaHeroe;
    }

    public Texture getTexturaAguaBendita() {
        verificarCarga();
        return texturaAguaBendita;
    }

    public Texture getTexturaAtaque() {
        verificarCarga();
        return texturaAtaque;
    }

    public Sound getSonidoHerido() {
        verificarCarga();
        return sonidoHerido;
    }

    public Sound getSonidoRecoleccion() {
        verificarCarga();
        return sonidoRecoleccion;
    }

    public Music getMusica() {
        verificarCarga();
        return musica;
    }

    public void liberarRecursos() {
        if (!recursosCargados) {
            return;
        }

        texturaHeroe.dispose();
        texturaAguaBendita.dispose();
        texturaAtaque.dispose();

        fondoPuente.dispose();
        fondoEntrada.dispose();
        fondoRecepcion.dispose();
        fondoConde.dispose();
        
        sonidoHerido.dispose();
        sonidoRecoleccion.dispose();
        musica.dispose();

        recursosCargados = false;
    }
}