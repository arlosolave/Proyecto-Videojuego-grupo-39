package puppy.code.gestores;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import java.util.EnumMap;
import java.util.Map;

import puppy.code.niveles.TipoEscenario;

public final class GestorRecursos {

    private static final GestorRecursos INSTANCIA =
            new GestorRecursos();

    private Texture texturaHeroe;
    private Texture texturaAguaBendita;
    private Texture texturaPlataforma;
    private Texture pantallaInicio;
    private Texture pantallaDerrota;
    private Texture pantallaVictoria;
    private Texture tituloRainingBlood;
    private Texture tituloGameOver;
    private Texture tituloVictoria;
    private Sound sonidoHerido;
    private Sound sonidoRecoleccion;
    private Music musica;
    private final Map<TipoEscenario, Texture> fondos =
            new EnumMap<>(TipoEscenario.class);

    private final Map<TipoEscenario, Texture> texturasAtaque =
            new EnumMap<>(TipoEscenario.class);

    private final Map<TipoEscenario, Texture> transiciones =
            new EnumMap<>(TipoEscenario.class);
    
    private boolean recursosCargados;

    private GestorRecursos() {
    }

    public static GestorRecursos getInstancia() {
        return INSTANCIA;
    }

    public void cargarRecursos() {
        if (recursosCargados) {
            return;
        }
        
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
        
        for (TipoEscenario tipo
                : TipoEscenario.values()) {

            fondos.put(
                    tipo,
                    new Texture(
                            Gdx.files.internal(
                                    tipo.getRutaFondo()
                            )
                    )
            );

            texturasAtaque.put(
                    tipo,
                    new Texture(
                            Gdx.files.internal(
                                    tipo.getRutaAtaque()
                            )
                    )
            );

            if (tipo.tieneTransicion()) {
                transiciones.put(
                        tipo,
                        new Texture(
                                Gdx.files.internal(
                                        tipo.getRutaTransicion()
                                )
                        )
                );
            }
        }
        
        texturaHeroe = new Texture(
                Gdx.files.internal("heroe.png")
        );

        texturaAguaBendita = new Texture(
                Gdx.files.internal("agua_bendita.png")
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

    private Texture obtenerTextura(
            Map<TipoEscenario, Texture> texturas,
            TipoEscenario tipo,
            String descripcion) {

        if (tipo == null) {
            throw new IllegalArgumentException(
                    "El tipo de escenario no puede ser nulo."
            );
        }

        Texture textura = texturas.get(tipo);

        if (textura == null) {
            throw new IllegalArgumentException(
                    descripcion + tipo
            );
        }

        return textura;
    }
    
    public Texture getFondo(
            TipoEscenario tipoEscenario) {

        verificarCarga();

        return obtenerTextura(
                fondos,
                tipoEscenario,
                "No existe un fondo para "
        );
    }
    
    public Texture getTransicion(
            TipoEscenario destino) {

        verificarCarga();

        return obtenerTextura(
                transiciones,
                destino,
                "No existe una transición para "
        );
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

        return obtenerTextura(
                texturasAtaque,
                tipoEscenario,
                "No existe un ataque para "
        );
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

    private void liberarTexturas(
            Map<TipoEscenario, Texture> texturas) {

        for (Texture textura : texturas.values()) {
            textura.dispose();
        }

        texturas.clear();
    }
    
    public void liberarRecursos() {
        if (!recursosCargados) {
            return;
        }

        liberarTexturas(fondos);
        liberarTexturas(texturasAtaque);
        liberarTexturas(transiciones);
        
        texturaHeroe.dispose();
        texturaAguaBendita.dispose();
        texturaPlataforma.dispose();
        
        sonidoHerido.dispose();
        sonidoRecoleccion.dispose();
        musica.dispose();
        
        pantallaInicio.dispose();
        pantallaDerrota.dispose();
        pantallaVictoria.dispose();
        
        tituloRainingBlood.dispose();
        tituloGameOver.dispose();
        tituloVictoria.dispose();
        
        recursosCargados = false;
    }
}