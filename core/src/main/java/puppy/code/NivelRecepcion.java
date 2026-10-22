package puppy.code;

public class NivelRecepcion extends Nivel {

    private static final float VELOCIDAD_VERTICAL = 350f;
    private static final float VELOCIDAD_HORIZONTAL = 400f;

    public NivelRecepcion() {
        super(
                "Recepcion del castillo",
                new ConfiguracionNivel.Builder()
                        .conDuracion(35f)
                        .conIntervaloAtaques(0.65f)
                        .conIntervaloAguaBendita(6f)
                        .build()
        );
    }

    @Override
    protected void configurarPlataformas() {
        agregarPlataforma(
                new Plataforma(
                        100,
                        100,
                        140,
                        18
                )
        );

        agregarPlataforma(
                new Plataforma(
                        335,
                        180,
                        130,
                        18
                )
        );

        agregarPlataforma(
                new Plataforma(
                        580,
                        110,
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