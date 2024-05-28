public class Rey extends Pieza {
    public Rey(boolean esBlanca) {
        super(esBlanca);
    }

    @Override
    public boolean movimientoValido(int[] from, int[] to, Pieza[][] board) {
        int deltaX = Math.abs(from[0] - to[0]);
        int deltaY = Math.abs(from[1] - to[1]);
        return (deltaX <= 1 && deltaY <= 1) && (board[to[0]][to[1]] == null || board[to[0]][to[1]].esBlanca() != this.esBlanca);
    }
}
