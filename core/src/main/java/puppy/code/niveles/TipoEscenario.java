package puppy.code.niveles;

public enum TipoEscenario {

    PUENTE(
            "fondo_puente.png",
            "gota_sangre.png",
            null
    ),

    ENTRADA(
            "fondo_entrada.png",
            "flecha.png",
            "transicion_entrada.png"
    ),

    RECEPCION(
            "fondo_recepcion.png",
            "lava.png",
            "transicion_recepcion.png"
    ),

    CONDE(
            "fondo_conde.png",
            "gota_conde.png",
            "transicion_conde.png"
    );

    private final String rutaFondo;
    private final String rutaAtaque;
    private final String rutaTransicion;

    TipoEscenario(
            String rutaFondo,
            String rutaAtaque,
            String rutaTransicion) {

        this.rutaFondo = rutaFondo;
        this.rutaAtaque = rutaAtaque;
        this.rutaTransicion = rutaTransicion;
    }

    public String getRutaFondo() {
        return rutaFondo;
    }

    public String getRutaAtaque() {
        return rutaAtaque;
    }

    public String getRutaTransicion() {
        return rutaTransicion;
    }

    public boolean tieneTransicion() {
        return rutaTransicion != null;
    }
}