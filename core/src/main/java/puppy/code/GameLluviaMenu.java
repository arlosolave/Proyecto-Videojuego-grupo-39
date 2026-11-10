package puppy.code;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import puppy.code.gestores.GestorRecursos;
import puppy.code.pantallas.MainMenuScreen;

	public class GameLluviaMenu extends Game {

		private SpriteBatch batch;
		private BitmapFont font;
		private int recordAguaBendita;

		@Override
		public void create() {
		    batch = new SpriteBatch();
		    font = new BitmapFont();

		    GestorRecursos.getInstancia()
		            .cargarRecursos();

		    setScreen(new MainMenuScreen(this));
		}

		public void render() {
			super.render(); // important!
		}

		@Override
		public void dispose() {
		    if (getScreen() != null) {
		        getScreen().dispose();
		    }

		    batch.dispose();
		    font.dispose();

		    GestorRecursos.getInstancia()
		            .liberarRecursos();
		}

		public SpriteBatch getBatch() {
			return batch;
		}

		public BitmapFont getFont() {
			return font;
		}

		public int getRecordAguaBendita() {
			return recordAguaBendita;
		}

		public void actualizarRecordAguaBendita(
		        int nuevoPuntaje) {

		    if (nuevoPuntaje < 0) {
		        throw new IllegalArgumentException(
		                "El puntaje no puede ser negativo."
		        );
		    }

		    if (nuevoPuntaje > recordAguaBendita) {
		        recordAguaBendita = nuevoPuntaje;
		    }
		}
		

	}
