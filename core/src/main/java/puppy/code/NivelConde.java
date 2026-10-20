package puppy.code;

public class NivelConde extends Nivel {

    private static final float DURACION = 5f;

    public NivelConde() {
        super(
                "Habitacion del conde",
                DURACION
        );
    }

    @Override
    protected void configurarPlataformas() {
        agregarPlataforma(
                new Plataforma(
                        100,
                        105,
                        130,
                        18
                )
        );

        agregarPlataforma(
                new Plataforma(
                        335,
                        170,
                        130,
                        18
                )
        );

        agregarPlataforma(
                new Plataforma(
                        580,
                        105,
                        130,
                        18
                )
        );
    }

    @Override
    protected EstrategiaAtaque crearEstrategiaAtaque() {
        return new AtaqueVampiro(
                400f,
                450f,
                380f,
                260f
        );
    }
}