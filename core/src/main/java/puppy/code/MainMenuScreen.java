package puppy.code;

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

public class MainMenuScreen implements Screen {

    private static final Color COLOR_INSTRUCCION =
            new Color(
                    0.78f,
                    0.86f,
                    1f,
                    1f
            );

    private final GameLluviaMenu game;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final GlyphLayout layout;
    private final OrthographicCamera camera;
    private final Texture fondo;
    private final Texture titulo;
    
    public MainMenuScreen(
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

        fondo = GestorRecursos
                .getInstancia()
                .getPantallaInicio();
        
        titulo = GestorRecursos
                .getInstancia()
                .getTituloRainingBlood();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();

        batch.setProjectionMatrix(
                camera.combined
        );

        batch.begin();

        batch.draw(
                fondo,
                0,
                0,
                800,
                480
        );

        batch.setColor(Color.WHITE);

        batch.draw(
                titulo,
                130,
                250,
                540,
                167
        );

        dibujarTextoCentrado(
                "ENTER PARA COMENZAR",
                70,
                1.2f,
                COLOR_INSTRUCCION
        );

        dibujarTextoCentrado(
                "FLECHAS: MOVER   |   ESPACIO: SALTAR",
                35,
                0.9f,
                COLOR_INSTRUCCION
        );

        batch.end();

        boolean comenzar =
                Gdx.input.isKeyJustPressed(
                        Input.Keys.ENTER
                )
                || Gdx.input.isKeyJustPressed(
                        Input.Keys.SPACE
                )
                || Gdx.input.isTouched();

        if (comenzar) {
            game.setScreen(
                    new GameScreen(game)
            );

            dispose();
        }
    }

    private void dibujarTextoCentrado(
            String texto,
            float y,
            float escala,
            Color colorPrincipal) {

        font.getData().setScale(escala);

        layout.setText(
                font,
                texto
        );

        float x =
                (camera.viewportWidth
                - layout.width) / 2f;

        // Sombra.
        font.setColor(
                0f,
                0f,
                0f,
                0.9f
        );

        font.draw(
                batch,
                texto,
                x + 3,
                y - 3
        );

        // Contorno.
        font.draw(batch, texto, x - 2, y);
        font.draw(batch, texto, x + 2, y);
        font.draw(batch, texto, x, y - 2);
        font.draw(batch, texto, x, y + 2);

        // Texto principal.
        font.setColor(colorPrincipal);

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
         * El fondo, batch y font pertenecen
         * a otros administradores.
         */
    }
}