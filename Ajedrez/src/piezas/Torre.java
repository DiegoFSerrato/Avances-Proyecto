package piezas;

public class Torre extends Pieza {
    public Torre(boolean esBlanco) {
        super(esBlanco);
    }

    @Override
    public boolean movimientoValido(int[] from, int[] to, Pieza[][] board) {
        if (from[0] == to[0] || from[1] == to[1]) {
            return caminoLibre(from, to, board);
        }
        return false;
    }

    private boolean caminoLibre(int[] from, int[] to, Pieza[][] board) {
        int deltaX = Integer.signum(to[0] - from[0]);
        int deltaY = Integer.signum(to[1] - from[1]);
        int x = from[0] + deltaX;
        int y = from[1] + deltaY;

        while (x != to[0] || y != to[1]) {
            if (board[x][y] != null) {
                return false;
            }
            x += deltaX;
            y += deltaY;
        }
        return board[to[0]][to[1]] == null || board[to[0]][to[1]].esBlanco() != this.esBlanco;
    }
}
