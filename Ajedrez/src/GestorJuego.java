public class GestorJuego {
    private static GestorJuego instancia;
    private Tablero tablero;
    private boolean turnoBlanco;

    private GestorJuego() {
        this.tablero = Tablero.obtenerInstancia();
        this.turnoBlanco = true; // Asegurar que los blancos siempre empiecen
    }

    public static GestorJuego obtenerInstancia() {
        if (instancia == null) {
            instancia = new GestorJuego();
        }
        return instancia;
    }

    public static void reiniciarInstancia() {
        instancia = new GestorJuego();
    }

    public Tablero obtenerTablero() {
        return tablero;
    }

    public boolean esTurnoBlanco() {
        return turnoBlanco;
    }

    public void cambiarTurno() {
        turnoBlanco = !turnoBlanco;
    }

    public void reiniciarTablero() {
        this.tablero.inicializarTablero();
        this.turnoBlanco = true;
    }
}
