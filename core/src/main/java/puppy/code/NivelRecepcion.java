package puppy.code;

public class NivelRecepcion extends Nivel {

    private static final float VELOCIDAD_VERTICAL = 350f;
    private static final float VELOCIDAD_HORIZONTAL = 400f;

    public NivelRecepcion() {
        super(
                "Recepcion del castillo",
                new ConfiguracionNivel.Builder()
                        .conDuracion(35f)
                        .conIntervaloAtaques(0.55f)
                        .conIntervaloAguaBendita(6f)
                        .conEscenario(TipoEscenario.RECEPCION)
                        .conAlturaSuelo(80f)
                        .build()
        );
    }

    @Override
    protected void configurarPlataformas() {
        agregarPlataforma(
                new Plataforma(
                        100,
                        160,
                        140,
                        18
                )
        );

        agregarPlataforma(
                new Plataforma(
                        335,
                        190,
                        130,
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
        return new AtaqueMixto(
                VELOCIDAD_VERTICAL,
                VELOCIDAD_HORIZONTAL
        );
    }
}