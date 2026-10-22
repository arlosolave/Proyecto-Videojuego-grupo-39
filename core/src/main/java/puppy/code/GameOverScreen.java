package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class GameOverScreen implements Screen {

    private final GameLluviaMenu game;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final OrthographicCamera camera;
    private final GlyphLayout layout =
            new GlyphLayout();
    
    public GameOverScreen(
            final GameLluviaMenu game) {

        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    private void dibujarTextoCentrado(
            String texto,
            float posicionY,
            float escala) {

        font.getData().setScale(escala);
        layout.setText(font, texto);

        float posicionX =
                (camera.viewportWidth
                - layout.width) / 2f;

        font.draw(
                batch,
                layout,
                posicionX,
                posicionY
        );
    }
    
    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.12f, 0.01f, 0.01f, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        dibujarTextoCentrado(
                "GAME OVER",
                370,
                2.3f
        );

        dibujarTextoCentrado(
                "El heroe ha caido durante su viaje.",
                265,
                1.35f
        );

        dibujarTextoCentrado(
                "Record de agua bendita: "
                + game.getRecordAguaBendita(),
                205,
                1.3f
        );

        dibujarTextoCentrado(
                "ENTER o clic para intentarlo nuevamente",
                105,
                1.2f
        );

        batch.end();

        font.getData().setScale(1f);

        if (Gdx.input.isTouched()
                || Gdx.input.isKeyJustPressed(
                        Input.Keys.ENTER)) {

            game.setScreen(new GameScreen(game));
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
