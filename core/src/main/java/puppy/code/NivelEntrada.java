package puppy.code;

public class NivelEntrada extends Nivel {

    private static final float DURACION = 5f;
    private static final float VELOCIDAD_ATAQUE = 350f;

    public NivelEntrada() {
        super("Entrada del castillo", DURACION);
    }

    @Override
    protected void configurarPlataformas() {
        agregarPlataforma(
                new Plataforma(
                        120,
                        100,
                        140,
                        18
                )
        );

        agregarPlataforma(
                new Plataforma(
                        350,
                        190,
                        130,
                        18
                )
        );

        agregarPlataforma(
                new Plataforma(
                        580,
                        100,
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