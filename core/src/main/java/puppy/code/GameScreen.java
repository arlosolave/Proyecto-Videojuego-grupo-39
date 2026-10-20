package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
	private Lluvia lluvia;
	private ShapeRenderer shapeRenderer;
	private Partida partida;

	   
	//boolean activo = true;

	public GameScreen(final GameLluviaMenu game) {
		this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
		  // load the images for the droplet and the bucket, 64x64 pixels each 	     
		  Sound hurtSound = Gdx.audio.newSound(Gdx.files.internal("hurt.ogg"));
		  heroe = new Heroe(new Texture(Gdx.files.internal("bucket.png")),hurtSound);
         
	      // load the drop sound effect and the rain background "music" 
         Texture gota = new Texture(Gdx.files.internal("drop.png"));
         Texture gotaMala = new Texture(Gdx.files.internal("dropBad.png"));
         
         Sound dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
        
	     Music rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));
	     lluvia = new Lluvia(
	    	        gota,
	    	        gotaMala,
	    	        dropSound,
	    	        rainMusic,
	    	        new AtaqueVertical(300)
	    	);
	      
	      // camera
	      camera = new OrthographicCamera();
	      camera.setToOrtho(false, 800, 480);
	      
	      shapeRenderer = new ShapeRenderer();
	      
	      // creacion del tarro
	      heroe.crear();
	      
	      // creacion de la lluvia
	      lluvia.crear();
	      
	      partida = new Partida();
	      partida.iniciar(lluvia);
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
	                    lluvia,
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
	    if (!heroe.estaHerido()) {
	        heroe.actualizarMovimiento(
	                nivelActual.getPlataformas()
	        );

	        if (!lluvia.actualizarMovimiento(heroe)) {
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
	    lluvia.actualizarDibujoLluvia(batch);

	    // Primera fila del HUD.
	    font.draw(
	            batch,
	            "Puntos: " + heroe.getPuntos(),
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
	  lluvia.continuar();
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {
		lluvia.pausar();
		game.setScreen(new PausaScreen(game, this)); 
	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
      heroe.destruir();
      lluvia.destruir();
      shapeRenderer.dispose();
	}

}
