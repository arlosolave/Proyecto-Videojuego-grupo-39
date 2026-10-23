package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class TransicionNivelScreen
        implements Screen {

    private static final float DURACION = 3f;

    private final GameLluviaMenu game;
    private final GameScreen juego;
    private final SpriteBatch batch;
    private final OrthographicCamera camera;
    private final Texture imagenTransicion;

    private float tiempoTranscurrido;

    public TransicionNivelScreen(
            GameLluviaMenu game,
            GameScreen juego,
            TipoEscenario destino) {

        this.game = game;
        this.juego = juego;
        this.batch = game.getBatch();

        imagenTransicion =
                GestorRecursos
                        .getInstancia()
                        .getTransicion(destino);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        tiempoTranscurrido += delta;

        camera.update();

        batch.setProjectionMatrix(
                camera.combined
        );

        batch.begin();

        batch.draw(
                imagenTransicion,
                0,
                0,
                800,
                480
        );

        batch.end();

        boolean continuar =
                tiempoTranscurrido >= DURACION
                || Gdx.input.isKeyJustPressed(
                        Input.Keys.ENTER
                )
                || Gdx.input.isKeyJustPressed(
                        Input.Keys.SPACE
                );

        if (continuar) {
            game.setScreen(juego);
            dispose();
        }
    }

    @Override
    public void show() {
    }

    @Override
    public void resize(
            int width,
            int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        /*
         * La textura pertenece a GestorRecursos,
         * por eso no se libera en esta pantalla.
         */
    }
}