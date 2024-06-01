public abstract class Pieza {
    protected boolean esBlanca;

    public Pieza(boolean esBlanca) {
        this.esBlanca = esBlanca;
    }

    public boolean esBlanca() {
        return esBlanca;
    }

    public abstract boolean movimientoValido(int[] from, int[] to, Pieza[][] board);
}

