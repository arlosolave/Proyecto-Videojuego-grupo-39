package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class PausaScreen implements Screen {

    private final GameLluviaMenu game;
    private final GameScreen juego;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final OrthographicCamera camera;

    public PausaScreen(
            final GameLluviaMenu game,
            GameScreen juego) {

        this.game = game;
        this.juego = juego;
        this.batch = game.getBatch();
        this.font = game.getFont();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.03f, 0.03f, 0.08f, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        font.getData().setScale(2f);

        font.draw(
                batch,
                "JUEGO EN PAUSA",
                205,
                310
        );

        font.getData().setScale(1f);

        font.draw(
                batch,
                "ENTER o clic para continuar",
                250,
                185
        );

        batch.end();

        font.getData().setScale(1f);

        if (Gdx.input.isTouched()
                || Gdx.input.isKeyJustPressed(
                        Input.Keys.ENTER)) {

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

