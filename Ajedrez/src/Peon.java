public class Peon extends Pieza {
    public Peon(boolean esBlanca) {
        super(esBlanca);
    }

    @Override
    public boolean movimientoValido(int[] from, int[] to, Pieza[][] board) {
        if (esBlanca) {
            // Movimiento hacia adelante
            if (from[1] == to[1] && board[to[0]][to[1]] == null) {
                if (from[0] == 1 && to[0] == 3 && board[2][from[1]] == null) {
                    return true; // Movimiento de dos casillas desde la posición inicial
                }
                if (to[0] == from[0] + 1) {
                    return true; // Movimiento de una casilla
                }
            }
            // Captura diagonal
            if (to[0] == from[0] + 1 && Math.abs(from[1] - to[1]) == 1 && board[to[0]][to[1]] != null && !board[to[0]][to[1]].esBlanca()) {
                return true;
            }
        } else {
            // Movimiento hacia adelante
            if (from[1] == to[1] && board[to[0]][to[1]] == null) {
                if (from[0] == 6 && to[0] == 4 && board[5][from[1]] == null) {
                    return true; // Movimiento de dos casillas desde la posición inicial
                }
                if (to[0] == from[0] - 1) {
                    return true; // Movimiento de una casilla
                }
            }
            // Captura diagonal
            if (to[0] == from[0] - 1 && Math.abs(from[1] - to[1]) == 1 && board[to[0]][to[1]] != null && board[to[0]][to[1]].esBlanca()) {
                return true;
            }
        }
        return false;
    }
}