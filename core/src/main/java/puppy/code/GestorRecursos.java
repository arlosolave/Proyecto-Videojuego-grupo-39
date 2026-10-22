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

        sonidoHerido.dispose();
        sonidoRecoleccion.dispose();
        musica.dispose();

        recursosCargados = false;
    }
}