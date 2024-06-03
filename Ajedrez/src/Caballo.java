public class Caballo extends Pieza {
    public Caballo(boolean esBlanco) {
        super(esBlanco);
    }

    @Override
    public boolean movimientoValido(int[] from, int[] to, Pieza[][] board) {
        int deltaX = Math.abs(from[0] - to[0]);
        int deltaY = Math.abs(from[1] - to[1]);
        return (deltaX == 2 && deltaY == 1) || (deltaX == 1 && deltaY == 2) && (board[to[0]][to[1]] == null || board[to[0]][to[1]].esBlanco() != this.esBlanco);
    }
}
