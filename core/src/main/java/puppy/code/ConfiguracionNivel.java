package puppy.code;

public final class ConfiguracionNivel {

	private final TipoEscenario tipoEscenario;
	private final float alturaSuelo;
    private final float duracion;
    private final float intervaloAtaques;
    private final float intervaloAguaBendita;

    private ConfiguracionNivel(Builder builder) {
        this.duracion = builder.duracion;
        this.intervaloAtaques =
                builder.intervaloAtaques;
        this.intervaloAguaBendita =
                builder.intervaloAguaBendita;
        this.tipoEscenario = builder.tipoEscenario;
        this.alturaSuelo = builder.alturaSuelo;
    }

    public float getDuracion() {
        return duracion;
    }

    public float getIntervaloAtaques() {
        return intervaloAtaques;
    }

    public float getIntervaloAguaBendita() {
        return intervaloAguaBendita;
    }

    public TipoEscenario getTipoEscenario() {
        return tipoEscenario;
    }

    public float getAlturaSuelo() {
        return alturaSuelo;
    }
    
    public static class Builder {

        private float duracion = 30f;
        private float intervaloAtaques = 0.65f;
        private float intervaloAguaBendita = 6f;
        private TipoEscenario tipoEscenario =
                TipoEscenario.PUENTE;

        private float alturaSuelo = 80f;
        
        public Builder conDuracion(float duracion) {
            this.duracion = duracion;
            return this;
        }

        public Builder conIntervaloAtaques(
                float intervaloAtaques) {

            this.intervaloAtaques =
                    intervaloAtaques;

            return this;
        }

        public Builder conIntervaloAguaBendita(
                float intervaloAguaBendita) {

            this.intervaloAguaBendita =
                    intervaloAguaBendita;

            return this;
        }

        public ConfiguracionNivel build() {
        	if (duracion <= 0
        	        || intervaloAtaques <= 0
        	        || intervaloAguaBendita <= 0
        	        || alturaSuelo < 0
        	        || tipoEscenario == null) {

        	    throw new IllegalStateException(
        	            "La configuracion del nivel no es valida."
        	    );
        	}

            return new ConfiguracionNivel(this);
        }
        public Builder conEscenario(
                TipoEscenario tipoEscenario) {

            this.tipoEscenario = tipoEscenario;
            return this;
        }

        public Builder conAlturaSuelo(
                float alturaSuelo) {

            this.alturaSuelo = alturaSuelo;
            return this;
        }
    }
}