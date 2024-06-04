package gestion;

import piezas.Pieza;
import java.util.Stack;
import java.util.Arrays;

public class GestorJuego {
    private static GestorJuego instancia;
    private Tablero tablero;
    private boolean turnoBlanco;
    private Stack<Pieza[][]> historial;

    private GestorJuego() {
        this.tablero = Tablero.obtenerInstancia();
        this.turnoBlanco = true; // Asegurar que los blancos siempre empiecen
        this.historial = new Stack<>();
    }

    public static GestorJuego obtenerInstancia() {
        if (instancia == null) {
            instancia = new GestorJuego();
        }
        return instancia;
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
        this.historial.clear(); // Limpiar el historial al reiniciar el tablero
    }

    public void guardarEstado() {

    }
}
