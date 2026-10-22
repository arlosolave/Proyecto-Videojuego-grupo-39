package puppy.code;

public class NivelConde extends Nivel {

    public NivelConde() {
        super(
                "Habitacion del conde",
                new ConfiguracionNivel.Builder()
                        .conDuracion(45f)
                        .conIntervaloAtaques(0.55f)
                        .conIntervaloAguaBendita(7f)
                        .build()
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