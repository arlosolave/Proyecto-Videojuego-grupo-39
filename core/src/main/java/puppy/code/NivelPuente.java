package puppy.code;

public class NivelPuente extends Nivel {

    private static final float VELOCIDAD_ATAQUE = 300f;

    public NivelPuente() {
        super(
                "Puente de piedra",
                new ConfiguracionNivel.Builder()
                        .conDuracion(30f)
                        .conIntervaloAtaques(0.85f)
                        .conIntervaloAguaBendita(7f)
                        .build()
        );
    }

    @Override
    protected EstrategiaAtaque crearEstrategiaAtaque() {
        return new AtaqueVertical(VELOCIDAD_ATAQUE);
    }
}