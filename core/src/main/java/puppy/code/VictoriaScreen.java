package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class VictoriaScreen implements Screen {

    private final GameLluviaMenu game;
    private final SpriteBatch batch;
    private final BitmapFont font;

    public VictoriaScreen(GameLluviaMenu game) {
        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(
                0.15f,
                0.02f,
                0.02f,
                1
        );

        batch.begin();

        font.draw(
                batch,
                "VICTORIA",
                330,
                400
        );

        font.draw(
                batch,
                "El conde ha quedado vulnerable.",
                150,
                330
        );

        font.draw(
                batch,
                "El heroe derrota al vampiro",
                175,
                285
        );

        font.draw(
                batch,
                "y regresa con vida a la ciudad.",
                150,
                240
        );

        font.draw(
                batch,
                "Puntuacion final: "
                + game.getHigherScore(),
                245,
                170
        );

        font.draw(
                batch,
                "ENTER: jugar nuevamente",
                210,
                100
        );

        batch.end();

        if (Gdx.input.isKeyJustPressed(
                Input.Keys.ENTER)) {

            game.setScreen(
                    new GameScreen(game)
            );

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
         * No se liberan batch ni font porque pertenecen
         * a GameLluviaMenu y son compartidos.
         */
    }
}