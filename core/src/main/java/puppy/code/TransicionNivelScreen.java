package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class TransicionNivelScreen
        implements Screen {

    private static final float DURACION = 3f;

    private final GameLluviaMenu game;
    private final GameScreen juego;
    private final String nombreSiguienteNivel;

    private final SpriteBatch batch;
    private final BitmapFont font;
    private final OrthographicCamera camera;

    private float tiempoTranscurrido;

    public TransicionNivelScreen(
            GameLluviaMenu game,
            GameScreen juego,
            String nombreSiguienteNivel) {

        this.game = game;
        this.juego = juego;
        this.nombreSiguienteNivel =
                nombreSiguienteNivel;

        this.batch = game.getBatch();
        this.font = game.getFont();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {
        tiempoTranscurrido += delta;

        ScreenUtils.clear(0.06f, 0.02f, 0.08f, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        font.getData().setScale(1.5f);

        font.draw(
                batch,
                "El heroe continua su viaje...",
                155,
                340
        );

        font.getData().setScale(1f);

        font.draw(
                batch,
                "Siguiente destino:",
                315,
                240
        );

        font.draw(
                batch,
                nombreSiguienteNivel,
                290,
                195
        );

        font.draw(
                batch,
                "ENTER para continuar",
                300,
                105
        );


        batch.end();

        font.getData().setScale(1f);

        boolean continuar =
                tiempoTranscurrido >= DURACION
                || Gdx.input.isTouched()
                || Gdx.input.isKeyJustPressed(
                        Input.Keys.ENTER
                );

        if (continuar) {
            game.setScreen(juego);
            dispose();
        }
    }

    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}