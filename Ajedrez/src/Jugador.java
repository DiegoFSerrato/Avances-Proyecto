public class Jugador {
    private String nombre;
    private boolean esBlanco;
    private Mediator mediator;

    public Jugador(String nombre, boolean esBlanco, Mediator mediator) {
        this.nombre = nombre;
        this.esBlanco = esBlanco;
        this.mediator = mediator;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean esBlanco() {
        return esBlanco;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public void hacerMovimiento(Tablero tablero, int[] from, int[] to) {
        Pieza pieza = tablero.obtenerTablero()[from[0]][from[1]];
        if (pieza != null && pieza.esBlanco() == this.esBlanco) {
            if (tablero.moverPieza(from, to)) {
                mediator.notificar(this, "cambioTurno");
            } else {
                mediator.notificar(this, "movimientoInvalido");
            }
        } else {
            mediator.notificar(this, "movimientoInvalido");
        }
    }
}
