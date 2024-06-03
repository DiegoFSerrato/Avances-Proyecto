public class Tablero {
    private static Tablero instancia;
    private Pieza[][] board;

    private Tablero() {
        inicializarTablero();
    }

    public static Tablero obtenerInstancia() {
        if (instancia == null) {
            instancia = new Tablero();
        }
        return instancia;
    }

    public void inicializarTablero() {
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

    private void inicializarOtrasPiezas(boolean esBlanco, int fila) {
        board[fila][0] = PiezaFactory.crearPieza("Torre", esBlanco);
        board[fila][7] = PiezaFactory.crearPieza("Torre", esBlanco);
        board[fila][1] = PiezaFactory.crearPieza("Caballo", esBlanco);
        board[fila][6] = PiezaFactory.crearPieza("Caballo", esBlanco);
        board[fila][2] = PiezaFactory.crearPieza("Alfil", esBlanco);
        board[fila][5] = PiezaFactory.crearPieza("Alfil", esBlanco);
        board[fila][3] = PiezaFactory.crearPieza("Reina", esBlanco);
        board[fila][4] = PiezaFactory.crearPieza("Rey", esBlanco);
    }

    public Pieza[][] obtenerTablero() {
        return board;
    }

    public boolean moverPieza(int[] from, int[] to) {
        Pieza pieza = board[from[0]][from[1]];
        if (pieza != null && pieza.movimientoValido(from, to, board)) {
            board[to[0]][to[1]] = pieza;
            board[from[0]][from[1]] = null;
            return true;
        }
        return false;
    }

    public static void imprimirTablero(Pieza[][] board) {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                Pieza pieza = board[i][j];
                if (pieza == null) {
                    System.out.print(". ");
                } else if (pieza instanceof Peon) {
                    System.out.print(pieza.esBlanco() ? "P " : "p ");
                } else if (pieza instanceof Rey) {
                    System.out.print(pieza.esBlanco() ? "K " : "k ");
                } else if (pieza instanceof Reina) {
                    System.out.print(pieza.esBlanco() ? "Q " : "q ");
                } else if (pieza instanceof Torre) {
                    System.out.print(pieza.esBlanco() ? "R " : "r ");
                } else if (pieza instanceof Alfil) {
                    System.out.print(pieza.esBlanco() ? "B " : "b ");
                } else if (pieza instanceof Caballo) {
                    System.out.print(pieza.esBlanco() ? "N " : "n ");
                }
            }
            System.out.println();
        }
    }

    public boolean estaEnJaque(boolean esBlanco) {
        int[] posicionRey = encontrarRey(esBlanco);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pieza pieza = board[i][j];
                if (pieza != null && pieza.esBlanco() != esBlanco) {
                    if (pieza.movimientoValido(new int[]{i, j}, posicionRey, board)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean estaEnJaqueMate(boolean esBlanco) {
        if (!estaEnJaque(esBlanco)) {
            return false;
        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pieza pieza = board[i][j];
                if (pieza != null && pieza.esBlanco() == esBlanco) {
                    for (int x = 0; x < 8; x++) {
                        for (int y = 0; y < 8; y++) {
                            int[] from = new int[]{i, j};
                            int[] to = new int[]{x, y};
                            if (pieza.movimientoValido(from, to, board)) {
                                Pieza piezaDestino = board[x][y];
                                board[x][y] = pieza;
                                board[i][j] = null;
                                boolean sigueEnJaque = estaEnJaque(esBlanco);
                                board[i][j] = pieza;
                                board[x][y] = piezaDestino;
                                if (!sigueEnJaque) {
                                    return false;
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    private int[] encontrarRey(boolean esBlanco) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                Pieza pieza = board[i][j];
                if (pieza instanceof Rey && pieza.esBlanco() == esBlanco) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }
}
