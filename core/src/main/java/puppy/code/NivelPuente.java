package puppy.code;

public class NivelPuente extends Nivel {

    private static final float VELOCIDAD_ATAQUE = 300f;

    public NivelPuente() {
        super(
                "Puente de piedra",
                new ConfiguracionNivel.Builder()
                        .conDuracion(5f)
                        .conIntervaloAtaques(0.55f)
                        .conIntervaloAguaBendita(7f)
                        .conEscenario(TipoEscenario.PUENTE)
                        .conAlturaSuelo(80f)
                        .build()
        );
    }

    @Override
    protected EstrategiaAtaque crearEstrategiaAtaque() {
        return new AtaqueVertical(VELOCIDAD_ATAQUE);
    }
}