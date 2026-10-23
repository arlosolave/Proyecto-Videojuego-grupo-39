package puppy.code;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import java.util.List;

public class Heroe {
	   private Rectangle area;
	   private Texture imagenHeroe;
	   private Sound sonidoHerido;
	   private int vidas = 3;
	   private int aguaBenditaRecolectada = 0;
	   private int velocidadHorizontal = 400;
	   private float velocidadVertical = 0;
	   private static final float FUERZA_SALTO = 650f;
	   private static final float GRAVEDAD = -1800f;
	   private static final float ANCHO_VISUAL = 80f;
	   private static final float ALTO_VISUAL = 80f;
	   private static final float ANCHO_HITBOX = 42f;
	   private static final float ALTO_HITBOX = 67f;
	   private static final float AJUSTE_VISUAL_Y = -5f;
	   private float alturaSuelo;
	   private boolean enSuelo = true;
	   private boolean herido = false;
	   private static final int DURACION_HERIDO=50;
	   private int tiempoHerido;
	   
	   public Heroe(Texture tex, Sound ss) {
		   imagenHeroe = tex;
		   sonidoHerido = ss;
	   }
	   
		public int getVidas() {
			return vidas;
		}
	
		public int getAguaBenditaRecolectada() {
			return aguaBenditaRecolectada;
		}
		public Rectangle getArea() {
			return area;
		}
		public void recolectarAguaBendita(int pp) {
			aguaBenditaRecolectada+=pp;
		}
		public void ganarVida() {
		    vidas++;
		}
	
		public void crear(float alturaSuelo) {
		    area = new Rectangle();

		    area.width = ANCHO_HITBOX;
		    area.height = ALTO_HITBOX;

		    area.x = 800 / 2f - area.width / 2f;
		    area.y = alturaSuelo;

		    this.alturaSuelo = alturaSuelo;

		    velocidadVertical = 0;
		    enSuelo = true;
		}
		
		public void danar() {
		    if (herido) {
		        return;
		    }

		    vidas--;
		    herido = true;
		    tiempoHerido = DURACION_HERIDO;
		    sonidoHerido.play();
		}
		
		public void dibujar(SpriteBatch batch) {
		   
		    float posicionVisualX =
		            area.x
		            - (ANCHO_VISUAL - area.width) / 2f;

		    float desplazamientoHerido = 0f;

		    if (herido) {
		        desplazamientoHerido =
		                MathUtils.random(-5f, 5f);

		        tiempoHerido--;

		        if (tiempoHerido <= 0) {
		            herido = false;
		        }
		    }

		    batch.draw(
		            imagenHeroe,
		            posicionVisualX,
		            area.y
		            + AJUSTE_VISUAL_Y
		            + desplazamientoHerido,
		            ANCHO_VISUAL,
		            ALTO_VISUAL
		    );
		}
	   
	   
	   public void actualizarMovimiento(
		        List<Plataforma> plataformas) {

		    float delta = Gdx.graphics.getDeltaTime();

		    // Movimiento horizontal
		    if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
		        area.x -= velocidadHorizontal * delta;
		    }

		    if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
		        area.x += velocidadHorizontal * delta;
		    }

		    // Salto
		    boolean solicitaSalto =
		            Gdx.input.isKeyJustPressed(Input.Keys.SPACE)
		            || Gdx.input.isKeyJustPressed(Input.Keys.UP);

		    if (solicitaSalto && enSuelo) {
		        velocidadVertical = FUERZA_SALTO;
		        enSuelo = false;
		    }

		    float posicionYAnterior = area.y;

		    // Gravedad
		    velocidadVertical += GRAVEDAD * delta;
		    area.y += velocidadVertical * delta;
		    enSuelo = false;

		    // Colisión con el suelo
		    if (area.y <= alturaSuelo) {
		        area.y = alturaSuelo;
		        velocidadVertical = 0;
		        enSuelo = true;

		    } else if (velocidadVertical <= 0) {

		        // Colisión al caer sobre plataformas
		        for (Plataforma plataforma : plataformas) {
		            Rectangle areaPlataforma =
		                    plataforma.getArea();

		            float parteSuperior =
		                    areaPlataforma.y
		                    + areaPlataforma.height;

		            boolean cruzaPlataforma =
		                    posicionYAnterior >= parteSuperior
		                    && area.y <= parteSuperior;

		            boolean coincideHorizontalmente =
		                    area.x + area.width
		                            > areaPlataforma.x
		                    && area.x
		                            < areaPlataforma.x
		                            + areaPlataforma.width;

		            if (cruzaPlataforma
		                    && coincideHorizontalmente) {

		                area.y = parteSuperior;
		                velocidadVertical = 0;
		                enSuelo = true;
		                break;
		            }
		        }
		    }

		    float margenVisual =
		            (ANCHO_VISUAL - area.width) / 2f;

		    if (area.x < margenVisual) {
		        area.x = margenVisual;
		    }

		    if (area.x
		            > 800 - area.width - margenVisual) {

		        area.x =
		                800 - area.width - margenVisual;
		    }
		}
	   public void colocarEnSuelo(
		        float nuevaAlturaSuelo) {

		    alturaSuelo = nuevaAlturaSuelo;

		    area.y = alturaSuelo;
		    velocidadVertical = 0;
		    enSuelo = true;
		}

	
   public boolean estaHerido() {
	   return herido;
   }
	   
}
