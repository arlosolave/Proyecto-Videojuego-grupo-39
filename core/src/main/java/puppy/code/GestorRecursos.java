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
    private Texture texturaSangre;
    private Texture texturaFlecha;
    private Texture texturaLava;
    private Texture texturaGotaConde;
    private Texture fondoPuente;
    private Texture fondoEntrada;
    private Texture fondoRecepcion;
    private Texture fondoConde;
    private Texture texturaPlataforma;
    private Texture transicionEntrada;
    private Texture transicionRecepcion;
    private Texture transicionConde;
    private Texture pantallaInicio;
    private Texture pantallaDerrota;
    private Texture pantallaVictoria;
    private Texture tituloRainingBlood;
    private Texture tituloGameOver;
    private Texture tituloVictoria;
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
        
        transicionEntrada = new Texture(
                Gdx.files.internal(
                        "transicion_entrada.png"
                )
        );

        transicionRecepcion = new Texture(
                Gdx.files.internal(
                        "transicion_recepcion.png"
                )
        );

        transicionConde = new Texture(
                Gdx.files.internal(
                        "transicion_conde.png"
                )
        );
        
        pantallaInicio = new Texture(
                Gdx.files.internal(
                        "pantalla_inicio.png"
                )
        );

        pantallaDerrota = new Texture(
                Gdx.files.internal(
                        "pantalla_derrota.png"
                )
        );

        pantallaVictoria = new Texture(
                Gdx.files.internal(
                        "pantalla_victoria.png"
                )
        );
        
        texturaHeroe = new Texture(
                Gdx.files.internal("heroe.png")
        );

        texturaAguaBendita = new Texture(
                Gdx.files.internal("agua_bendita.png")
        );

        texturaSangre = new Texture(
                Gdx.files.internal("gota_sangre.png")
        );

        texturaFlecha = new Texture(
                Gdx.files.internal("flecha.png")
        );

        texturaLava = new Texture(
                Gdx.files.internal("lava.png")
        );

        texturaGotaConde = new Texture(
                Gdx.files.internal("gota_conde.png")
        );

        texturaPlataforma = new Texture(
                Gdx.files.internal(
                        "plataforma_piedra.png"
                )
        );
        
        tituloRainingBlood = new Texture(
                Gdx.files.internal(
                        "titulo_raining_blood.png"
                )
        );

        tituloGameOver = new Texture(
                Gdx.files.internal(
                        "titulo_game_over.png"
                )
        );

        tituloVictoria = new Texture(
                Gdx.files.internal(
                        "titulo_victoria.png"
                )
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
    
    public Texture getTransicion(
            TipoEscenario destino) {

        verificarCarga();

        switch (destino) {
            case ENTRADA:
                return transicionEntrada;

            case RECEPCION:
                return transicionRecepcion;

            case CONDE:
                return transicionConde;

            default:
                throw new IllegalArgumentException(
                        "No existe una transicion para "
                        + destino
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

    public Texture getTexturaPlataforma() {
        verificarCarga();
        return texturaPlataforma;
    }
    
    public Texture getTexturaAtaque(
            TipoEscenario tipoEscenario) {

        verificarCarga();

        switch (tipoEscenario) {
            case PUENTE:
                return texturaSangre;

            case ENTRADA:
                return texturaFlecha;

            case RECEPCION:
                return texturaLava;

            case CONDE:
                return texturaGotaConde;

            default:
                throw new IllegalArgumentException(
                        "Escenario desconocido."
                );
        }
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
    
    public Texture getPantallaInicio() {
        verificarCarga();
        return pantallaInicio;
    }

    public Texture getPantallaDerrota() {
        verificarCarga();
        return pantallaDerrota;
    }

    public Texture getPantallaVictoria() {
        verificarCarga();
        return pantallaVictoria;
    }
    
    public Texture getTituloRainingBlood() {
        verificarCarga();
        return tituloRainingBlood;
    }

    public Texture getTituloGameOver() {
        verificarCarga();
        return tituloGameOver;
    }

    public Texture getTituloVictoria() {
        verificarCarga();
        return tituloVictoria;
    }

    public void liberarRecursos() {
        if (!recursosCargados) {
            return;
        }

        texturaHeroe.dispose();
        texturaAguaBendita.dispose();
        texturaSangre.dispose();
        texturaFlecha.dispose();
        texturaLava.dispose();
        texturaGotaConde.dispose();
        texturaPlataforma.dispose();
        
        fondoPuente.dispose();
        fondoEntrada.dispose();
        fondoRecepcion.dispose();
        fondoConde.dispose();
        
        sonidoHerido.dispose();
        sonidoRecoleccion.dispose();
        musica.dispose();

        transicionEntrada.dispose();
        transicionRecepcion.dispose();
        transicionConde.dispose();
        
        pantallaInicio.dispose();
        pantallaDerrota.dispose();
        pantallaVictoria.dispose();
        
        tituloRainingBlood.dispose();
        tituloGameOver.dispose();
        tituloVictoria.dispose();
        
        recursosCargados = false;
    }
}