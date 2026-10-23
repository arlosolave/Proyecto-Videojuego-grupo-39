package puppy.code;

public class NivelEntrada extends Nivel {

    private static final float VELOCIDAD_ATAQUE = 350f;

    public NivelEntrada() {
        super(
                "Entrada del castillo",
                new ConfiguracionNivel.Builder()
                        .conDuracion(30f)
                        .conIntervaloAtaques(0.75f)
                        .conIntervaloAguaBendita(6.5f)
                        .conEscenario(TipoEscenario.ENTRADA)
                        .conAlturaSuelo(80f)
                        .build()
        );
    }

    @Override
    protected void configurarPlataformas() {
        agregarPlataforma(
                new Plataforma(
                        120,
                        160,
                        140,
                        18
                )
        );


        agregarPlataforma(
                new Plataforma(
                        580,
                        160,
                        140,
                        18
                )
        );
    }

    @Override
    protected EstrategiaAtaque crearEstrategiaAtaque() {
        return new AtaqueHorizontal(
                VELOCIDAD_ATAQUE
        );
    }
}