package puppy.code.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import puppy.code.GameLluviaMenu;
import puppy.code.gestores.GestorRecursos;

public class GameOverScreen implements Screen {

    private static final Color COLOR_INSTRUCCION =
            new Color(
                    0.82f,
                    0.86f,
                    0.95f,
                    1f
            );

    private final GameLluviaMenu game;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final GlyphLayout layout;
    private final OrthographicCamera camera;

    private final Texture fondo;
    private final Texture titulo;

    public GameOverScreen(
            GameLluviaMenu game) {

        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();

        layout = new GlyphLayout();

        camera = new OrthographicCamera();
        camera.setToOrtho(
                false,
                800,
                480
        );

        GestorRecursos recursos =
                GestorRecursos.getInstancia();

        fondo = recursos.getPantallaDerrota();
        titulo = recursos.getTituloGameOver();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();

        batch.setProjectionMatrix(
                camera.combined
        );

        batch.begin();

        batch.setColor(Color.WHITE);

        batch.draw(
                fondo,
                0,
                0,
                800,
                480
        );

        batch.draw(
                titulo,
                150,
                295,
                500,
                163
        );

        dibujarTextoCentrado(
                "ENTER PARA REINTENTAR",
                70,
                1.2f
        );

        dibujarTextoCentrado(
                "REGRESARAS AL PRIMER NIVEL WUAJA  xd",
                35,
                0.9f
        );

        batch.end();

        boolean reiniciar =
                Gdx.input.isKeyJustPressed(
                        Input.Keys.ENTER
                )
                || Gdx.input.isKeyJustPressed(
                        Input.Keys.SPACE
                )
                || Gdx.input.isTouched();

        if (reiniciar) {
            game.setScreen(
                    new GameScreen(game)
            );

            dispose();
        }
    }

    private void dibujarTextoCentrado(
            String texto,
            float y,
            float escala) {

        font.getData().setScale(escala);

        layout.setText(
                font,
                texto
        );

        float x =
                (camera.viewportWidth
                - layout.width) / 2f;

        // Sombra y contorno.
        font.setColor(
                0f,
                0f,
                0f,
                0.95f
        );

        font.draw(
                batch,
                texto,
                x + 3,
                y - 3
        );

        font.draw(batch, texto, x - 2, y);
        font.draw(batch, texto, x + 2, y);
        font.draw(batch, texto, x, y - 2);
        font.draw(batch, texto, x, y + 2);

        // Texto principal.
        font.setColor(COLOR_INSTRUCCION);

        font.draw(
                batch,
                texto,
                x,
                y
        );

        font.setColor(Color.WHITE);
        font.getData().setScale(1f);
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
         * Las texturas, batch y fuente pertenecen
         * a sus respectivos administradores.
         */
    }
}