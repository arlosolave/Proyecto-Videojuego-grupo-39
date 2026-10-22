package puppy.code;


import com.badlogic.gdx.Screen;

import com.badlogic.gdx.graphics.OrthographicCamera;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class GameScreen implements Screen {
	final GameLluviaMenu game;
    private OrthographicCamera camera;
	private SpriteBatch batch;	   
	private BitmapFont font;
	private Heroe heroe;
	private GestorProyectiles gestorProyectiles;
	private ShapeRenderer shapeRenderer;
	private Partida partida;

	   
	//boolean activo = true;

	public GameScreen(
	        final GameLluviaMenu game) {

	    this.game = game;
	    this.batch = game.getBatch();
	    this.font = game.getFont();

	    GestorRecursos recursos =
	            GestorRecursos.getInstancia();

	    heroe = new Heroe(
	            recursos.getTexturaHeroe(),
	            recursos.getSonidoHerido()
	    );

	    gestorProyectiles =
	            new GestorProyectiles(
	                    recursos
	                            .getTexturaAguaBendita(),

	                    recursos
	                            .getTexturaAtaque(),

	                    recursos
	                            .getSonidoRecoleccion(),

	                    recursos.getMusica(),

	                    new AtaqueVertical(300f)
	            );

	    // Cámara.
	    camera = new OrthographicCamera();
	    camera.setToOrtho(false, 800, 480);

	    shapeRenderer = new ShapeRenderer();

	    // Crear el héroe y el gestor.
	    heroe.crear();
	    gestorProyectiles.crear();

	    // Iniciar la partida.
	    partida = new Partida();
	    partida.iniciar(gestorProyectiles);
	}

	@Override
	public void render(float delta) {
	    // Limpiar la pantalla.
	    ScreenUtils.clear(0, 0, 0.2f, 1);

	    Nivel nivelActual = partida.getNivelActual();
	    
	    // Actualizar el estado del nivel.
	    nivelActual.actualizarTiempo(delta);
	    
	    if (nivelActual.estaCompletado()) {

	        if (partida.tieneSiguienteNivel()) {
	            partida.avanzarNivel(
	                    gestorProyectiles,
	                    heroe
	            );

	            nivelActual =
	                    partida.getNivelActual();

	        } else {
	            if (game.getHigherScore()
	                    < heroe.getPuntos()) {

	                game.setHigherScore(
	                        heroe.getPuntos()
	                );
	            }

	            game.setScreen(
	                    new VictoriaScreen(game)
	            );

	            dispose();
	            return;
	        }
	    }

	    // Actualizar matrices de la cámara.
	    camera.update();

	    // Actualizar la lógica del juego.
	    heroe.actualizarMovimiento(
	             nivelActual.getPlataformas()
	    );

	        if (!gestorProyectiles.actualizarMovimiento(heroe)) {
	            if (game.getHigherScore()
	                    < heroe.getPuntos()) {

	                game.setHigherScore(
	                        heroe.getPuntos()
	                );
	            }

	            game.setScreen(
	                    new GameOverScreen(game)
	            );

	            dispose();
	            return;
	        }

	    // Dibujar plataformas.
	    shapeRenderer.setProjectionMatrix(
	            camera.combined
	    );

	    shapeRenderer.begin(ShapeType.Filled);

	    for (Plataforma plataforma
	            : nivelActual.getPlataformas()) {

	        plataforma.dibujar(shapeRenderer);
	    }

	    shapeRenderer.end();

	    // Dibujar héroe, proyectiles e interfaz.
	    batch.setProjectionMatrix(camera.combined);
	    batch.begin();

	    heroe.dibujar(batch);
	    gestorProyectiles.actualizarDibujoLluvia(batch);

	    // Primera fila del HUD.
	    font.draw(
	            batch,
	            "Agua bendita: " + heroe.getPuntos(),
	            10,
	            470
	    );

	    font.draw(
	            batch,
	            "Record: " + game.getHigherScore(),
	            320,
	            470
	    );

	    font.draw(
	            batch,
	            "Vidas: " + heroe.getVidas(),
	            680,
	            470
	    );

	    // Segunda fila del HUD.
	    font.draw(
	            batch,
	            "Nivel: " + nivelActual.getNombre(),
	            10,
	            435
	    );

	    font.draw(
	            batch,
	            "Tiempo: "
	            + (int) Math.ceil(
	                    nivelActual.getTiempoRestante()
	            ),
	            620,
	            435
	    );

	    batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
	  // continuar con sonido de lluvia
	  gestorProyectiles.continuar();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		gestorProyectiles.pausar();
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
	    gestorProyectiles.pausar();
	    shapeRenderer.dispose();
	}

}
