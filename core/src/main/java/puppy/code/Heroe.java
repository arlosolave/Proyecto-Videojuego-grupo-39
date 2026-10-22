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
	   private int puntos = 0;
	   private int velocidadHorizontal = 400;
	   private float velocidadVertical = 0;
	   private static final float FUERZA_SALTO = 650f;
	   private static final float GRAVEDAD = -1800f;
	   private static final float ALTURA_SUELO = 20f;
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
	
		public int getPuntos() {
			return puntos;
		}
		public Rectangle getArea() {
			return area;
		}
		public void sumarPuntos(int pp) {
			puntos+=pp;
		}
		public void ganarVida() {
		    vidas++;
		}
	
		public void crear() {
		    area = new Rectangle();
		    area.x = 800 / 2 - 64 / 2;
		    area.y = ALTURA_SUELO;
		    area.width = 64;
		    area.height = 64;

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
		 if (!herido)  
		   batch.draw(imagenHeroe, area.x, area.y);
		 else {
		
		   batch.draw(imagenHeroe, area.x, area.y+ MathUtils.random(-5,5));
		   tiempoHerido--;
		   if (tiempoHerido<=0) herido = false;
		 }
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
		    if (area.y <= ALTURA_SUELO) {
		        area.y = ALTURA_SUELO;
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

		    // Límites laterales
		    if (area.x < 0) {
		        area.x = 0;
		    }

		    if (area.x > 800 - area.width) {
		        area.x = 800 - area.width;
		    }
		}
	    

	
   public boolean estaHerido() {
	   return herido;
   }
	   
}
