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
import java.util.ArrayList;
import java.util.List;
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
	private List<Plataforma> plataformas;

	   
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
         lluvia = new Lluvia(gota, gotaMala, dropSound, rainMusic);
	      
	      // camera
	      camera = new OrthographicCamera();
	      camera.setToOrtho(false, 800, 480);
	      
	      shapeRenderer = new ShapeRenderer();

	      plataformas = new ArrayList<>();

	      plataformas.add(
	              new Plataforma(140, 100, 180, 20)
	      );

	      plataformas.add(
	              new Plataforma(460, 200, 180, 20)
	      );
	      
	      // creacion del tarro
	      heroe.crear();
	      
	      // creacion de la lluvia
	      lluvia.crear();
	}

	@Override
	public void render(float delta) {
		//limpia la pantalla con color azul obscuro.
		ScreenUtils.clear(0, 0, 0.2f, 1);
		//actualizar matrices de la cámara
		camera.update();
		shapeRenderer.setProjectionMatrix(camera.combined);
		shapeRenderer.begin(ShapeType.Filled);

		for (Plataforma plataforma : plataformas) {
		    plataforma.dibujar(shapeRenderer);
		}

		shapeRenderer.end();
		//actualizar 
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		//dibujar textos
		font.draw(batch, "Gotas totales: " + heroe.getPuntos(), 5, 475);
		font.draw(batch, "Vidas : " + heroe.getVidas(), 670, 475);
		font.draw(batch, "HighScore : " + game.getHigherScore(), camera.viewportWidth/2-50, 475);
		
		if (!heroe.estaHerido()) {
			// movimiento del tarro desde teclado
			heroe.actualizarMovimiento(plataformas);        
			// caida de la lluvia 
	       if (!lluvia.actualizarMovimiento(heroe)) {
	    	  //actualizar HigherScore
	    	  if (game.getHigherScore()<heroe.getPuntos())
	    		  game.setHigherScore(heroe.getPuntos());  
	    	  //ir a la ventana de finde juego y destruir la actual
	    	  game.setScreen(new GameOverScreen(game));
	    	  dispose();
	       }
		}
		
		heroe.dibujar(batch);
		lluvia.actualizarDibujoLluvia(batch);
		
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
