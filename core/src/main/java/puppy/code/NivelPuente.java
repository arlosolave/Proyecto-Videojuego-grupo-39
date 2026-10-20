package puppy.code;

public class NivelPuente extends Nivel {

    private static final float DURACION = 30f;
    private static final float VELOCIDAD_ATAQUE = 300f;

    public NivelPuente() {
        super("Puente de piedra", DURACION);
    }

    @Override
    protected EstrategiaAtaque crearEstrategiaAtaque() {
        return new AtaqueVertical(VELOCIDAD_ATAQUE);
    }
}