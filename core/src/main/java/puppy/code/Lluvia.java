package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

public class Lluvia {

    private static final long INTERVALO_GOTAS =
            100_000_000L;

    private Array<Proyectil> proyectiles;
    private long ultimoTiempoGota;

    private final Texture gotaBuena;
    private final Texture gotaMala;
    private final Sound sonidoGota;
    private final Music musicaLluvia;

    public Lluvia(
            Texture gotaBuena,
            Texture gotaMala,
            Sound sonidoGota,
            Music musicaLluvia) {

        this.gotaBuena = gotaBuena;
        this.gotaMala = gotaMala;
        this.sonidoGota = sonidoGota;
        this.musicaLluvia = musicaLluvia;
    }

    public void crear() {
        proyectiles = new Array<>();
        crearGotaDeLluvia();

        musicaLluvia.setLooping(true);
        musicaLluvia.play();
    }

    private void crearGotaDeLluvia() {
        boolean esDanina =
                MathUtils.random(1, 10) < 5;

        Texture textura =
                esDanina ? gotaMala : gotaBuena;

        int puntos =
                esDanina ? 0 : 10;

        Proyectil gota = new Proyectil(
                textura,
                MathUtils.random(0, 800 - 64),
                480,
                64,
                64,
                0,
                -300,
                esDanina,
                puntos
        );

        proyectiles.add(gota);
        ultimoTiempoGota = TimeUtils.nanoTime();
    }

    public boolean actualizarMovimiento(Heroe heroe) {
        if (TimeUtils.nanoTime() - ultimoTiempoGota
                > INTERVALO_GOTAS) {

            crearGotaDeLluvia();
        }

        float delta = Gdx.graphics.getDeltaTime();

        /*
         * Se recorre desde el final para eliminar
         * elementos sin saltarse el siguiente.
         */
        for (int i = proyectiles.size - 1;
                i >= 0;
                i--) {

            Proyectil proyectil =
                    proyectiles.get(i);

            proyectil.actualizar(delta);

            if (proyectil.estaFueraDePantalla(
                    800,
                    480)) {

                proyectiles.removeIndex(i);
                continue;
            }

            if (proyectil.colisionaCon(
                    heroe.getArea())) {

                if (proyectil.esDanino()) {
                    heroe.danar();

                    proyectiles.removeIndex(i);

                    if (heroe.getVidas() <= 0) {
                        return false;
                    }

                } else {
                    heroe.sumarPuntos(
                            proyectil.getPuntos()
                    );

                    sonidoGota.play();
                    proyectiles.removeIndex(i);
                }
            }
        }

        return true;
    }

    public void actualizarDibujoLluvia(
            SpriteBatch batch) {

        for (Proyectil proyectil : proyectiles) {
            proyectil.dibujar(batch);
        }
    }

    public void pausar() {
        musicaLluvia.pause();
    }

    public void continuar() {
        if (!musicaLluvia.isPlaying()) {
            musicaLluvia.play();
        }
    }

    public void destruir() {
        gotaBuena.dispose();
        gotaMala.dispose();
        sonidoGota.dispose();
        musicaLluvia.dispose();
    }
}