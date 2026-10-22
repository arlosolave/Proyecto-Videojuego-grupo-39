package puppy.code;

public final class ConfiguracionNivel {

    private final float duracion;
    private final float intervaloAtaques;
    private final float intervaloAguaBendita;

    private ConfiguracionNivel(Builder builder) {
        this.duracion = builder.duracion;
        this.intervaloAtaques =
                builder.intervaloAtaques;
        this.intervaloAguaBendita =
                builder.intervaloAguaBendita;
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

    public static class Builder {

        private float duracion = 30f;
        private float intervaloAtaques = 0.65f;
        private float intervaloAguaBendita = 6f;

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
                    || intervaloAguaBendita <= 0) {

                throw new IllegalStateException(
                        "Los valores deben ser positivos."
                );
            }

            return new ConfiguracionNivel(this);
        }
    }
}