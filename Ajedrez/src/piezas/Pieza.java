package piezas;

public abstract class Pieza {
    protected boolean esBlanco;

    public Pieza(boolean esBlanco) {
        this.esBlanco = esBlanco;
    }

    public boolean esBlanco() {
        return esBlanco;
    }

    public abstract boolean movimientoValido(int[] from, int[] to, Pieza[][] board);
}
