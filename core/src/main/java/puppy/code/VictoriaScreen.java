package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class VictoriaScreen implements Screen {

    private final GameLluviaMenu game;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final GlyphLayout layout =
            new GlyphLayout();
    private final OrthographicCamera camera;
    
    public VictoriaScreen(GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(
                0.15f,
                0.02f,
                0.02f,
                1
        );

        camera.update();

        batch.setProjectionMatrix(
                camera.combined
        );

        batch.begin();

        dibujarTextoCentrado(
                "VICTORIA",
                410,
                2.2f
        );

        dibujarTextoCentrado(
                "El conde ha quedado vulnerable.",
                320,
                1.3f
        );

        dibujarTextoCentrado(
                "El heroe derrota al vampiro",
                265,
                1.3f
        );

        dibujarTextoCentrado(
                "y regresa con vida a la ciudad.",
                210,
                1.3f
        );

        dibujarTextoCentrado(
                "Agua bendita reunida: "
                + game.getRecordAguaBendita(),
                140,
                1.25f
        );

        dibujarTextoCentrado(
                "ENTER: jugar nuevamente",
                70,
                1.15f
        );

        batch.end();

        font.getData().setScale(1f);

        if (Gdx.input.isKeyJustPressed(
                Input.Keys.ENTER)) {

            game.setScreen(
                    new GameScreen(game)
            );

            dispose();
        }
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
         * No se liberan batch ni font porque pertenecen
         * a GameLluviaMenu y son compartidos.
         */
    }
}