import java.util.Arrays;

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
        for (int j = 0; j < 8; j++) {
            board[1][j] = PiezaFactory.crearPieza("Peon", true);
            board[6][j] = PiezaFactory.crearPieza("Peon", false);
        }
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
        System.out.println("  a b c d e f g h");
        for (int i = 7; i >= 0; i--) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < 8; j++) {
                Pieza pieza = board[i][j];
                if (pieza == null) {
                    System.out.print(". ");
                } else {
                    char representacion = obtenerRepresentacionPieza(pieza);
                    System.out.print(representacion + " ");
                }
            }
            System.out.println((i + 1));
        }
        System.out.println("  a b c d e f g h");
    }

    private static char obtenerRepresentacionPieza(Pieza pieza) {
        if (pieza instanceof Peon) {
            return pieza.esBlanco() ? 'P' : 'p';
        } else if (pieza instanceof Rey) {
            return pieza.esBlanco() ? 'R' : 'r';
        } else if (pieza instanceof Reina) {
            return pieza.esBlanco() ? 'D' : 'd';
        } else if (pieza instanceof Torre) {
            return pieza.esBlanco() ? 'T' : 't';
        } else if (pieza instanceof Alfil) {
            return pieza.esBlanco() ? 'A' : 'a';
        } else if (pieza instanceof Caballo) {
            return pieza.esBlanco() ? 'C' : 'c';
        }
        return '?';
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
