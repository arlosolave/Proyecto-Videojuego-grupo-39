package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class MainMenuScreen implements Screen {

    private final GameLluviaMenu game;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final OrthographicCamera camera;
    private final GlyphLayout layout =
            new GlyphLayout();
    
    public MainMenuScreen(
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
        ScreenUtils.clear(0.08f, 0.02f, 0.02f, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

        batch.begin();

        dibujarTextoCentrado(
                "EL CASTILLO DEL CONDE",
                390,
                2.2f
        );

        dibujarTextoCentrado(
                "Un heroe viaja hacia el castillo",
                290,
                1.35f
        );

        dibujarTextoCentrado(
                "de un conde acusado de terribles crimenes.",
                240,
                1.25f
        );

        dibujarTextoCentrado(
                "ENTER o clic para comenzar",
                125,
                1.3f
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
