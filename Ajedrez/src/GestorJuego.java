public class GestorJuego {
    private static GestorJuego instancia;
    private Pieza[][] board;
    private boolean turnoBlanco;

    private GestorJuego() {
        inicializarTablero();
    }

    public static GestorJuego obtenerInstancia() {
        if (instancia == null) {
            instancia = new GestorJuego();
        }
        return instancia;
    }

    private void inicializarTablero() {
        board = new Pieza[8][8];
        // Inicializar peones blancos
        for (int j = 0; j < 8; j++) {
            board[1][j] = PiezaFactory.crearPieza("Peon", true);
        }
        // Inicializar peones negros
        for (int j = 0; j < 8; j++) {
            board[6][j] = PiezaFactory.crearPieza("Peon", false);
        }
        // Inicializar otras piezas
        inicializarOtrasPiezas(true, 0);
        inicializarOtrasPiezas(false, 7);
    }

    private void inicializarOtrasPiezas(boolean esBlanca, int fila) {
        board[fila][0] = PiezaFactory.crearPieza("Torre", esBlanca);
        board[fila][7] = PiezaFactory.crearPieza("Torre", esBlanca);
        board[fila][1] = PiezaFactory.crearPieza("Caballo", esBlanca);
        board[fila][6] = PiezaFactory.crearPieza("Caballo", esBlanca);
        board[fila][2] = PiezaFactory.crearPieza("Alfil", esBlanca);
        board[fila][5] = PiezaFactory.crearPieza("Alfil", esBlanca);
        board[fila][3] = PiezaFactory.crearPieza("Reina", esBlanca);
        board[fila][4] = PiezaFactory.crearPieza("Rey", esBlanca);
    }

    public Pieza[][] obtenerTablero() {
        return board;
    }

    public boolean esTurnoBlanco() {
        return turnoBlanco;
    }

    public void cambiarTurno() {
        turnoBlanco = !turnoBlanco;
    }
}
