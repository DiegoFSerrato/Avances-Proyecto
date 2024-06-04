package gestion;

import mediadores.Mediator;

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
        if (tablero.moverPieza(from, to)) {
            mediator.notificar(this, "cambioTurno");
        } else {
            mediator.notificar(this, "movimientoInvalido");
        }
    }
}
