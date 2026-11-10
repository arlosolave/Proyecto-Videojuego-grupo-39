package puppy.code.niveles;

import puppy.code.estrategias.AtaqueVampiro;
import puppy.code.estrategias.EstrategiaAtaque;
import puppy.code.modelo.Plataforma;

public class NivelConde extends Nivel {

    public NivelConde() {
        super(
                "Habitacion del conde",
                new ConfiguracionNivel.Builder()
                        .conDuracion(40f)
                        .conIntervaloAtaques(0.40f)
                        .conIntervaloAguaBendita(7f)
                        .conEscenario(TipoEscenario.CONDE)
                        .conAlturaSuelo(80f)
                        .build()
        );
    }

    @Override
    protected void configurarPlataformas() {
        agregarPlataforma(
                new Plataforma(
                        100,
                        190,
                        130,
                        18
                )
        );

        agregarPlataforma(
                new Plataforma(
                        335,
                        150,
                        130,
                        18
                )
        );

        agregarPlataforma(
                new Plataforma(
                        580,
                        190,
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