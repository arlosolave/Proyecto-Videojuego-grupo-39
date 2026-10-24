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

public class VictoriaScreen implements Screen {

    private final GameLluviaMenu game;
    private final SpriteBatch batch;
    private final BitmapFont font;
    private final GlyphLayout layout;
    private final OrthographicCamera camera;

    private final Texture fondo;
    private final Texture titulo;

    public VictoriaScreen(
            final GameLluviaMenu game) {

        this.game = game;
        this.batch = game.getBatch();
        this.font = game.getFont();

        layout = new GlyphLayout();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);

        GestorRecursos recursos =
                GestorRecursos.getInstancia();

        fondo = recursos.getPantallaVictoria();
        titulo = recursos.getTituloVictoria();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0, 1);

        camera.update();
        batch.setProjectionMatrix(camera.combined);

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
                300,
                500,
                157
        );

        dibujarTextoCentrado(
                "AGUA  BENDITA  REUNIDA:  "
                + game.getRecordAguaBendita(),
                82,
                1.15f
        );

        dibujarTextoCentrado(
                "ENTER PARA JUGAR NUEVAMENTE",
                43,
                1f
        );

        batch.end();

        font.getData().setScale(1f);
        font.setColor(Color.WHITE);

        if (Gdx.input.isKeyJustPressed(
                Input.Keys.ENTER)
                || Gdx.input.isKeyJustPressed(
                        Input.Keys.SPACE)
                || Gdx.input.justTouched()) {

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
                (800 - layout.width) / 2f;

        // Contorno oscuro.
        font.setColor(Color.BLACK);

        font.draw(
                batch,
                texto,
                posicionX - 2,
                posicionY
        );

        font.draw(
                batch,
                texto,
                posicionX + 2,
                posicionY
        );

        font.draw(
                batch,
                texto,
                posicionX,
                posicionY - 2
        );

        font.draw(
                batch,
                texto,
                posicionX,
                posicionY + 2
        );

        // Texto principal.
        font.setColor(Color.WHITE);

        font.draw(
                batch,
                texto,
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
         * Las texturas, la fuente y el SpriteBatch
         * pertenecen a los gestores compartidos.
         */
    }
}