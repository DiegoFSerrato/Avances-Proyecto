public class MovimientoPieza implements ComandoMovimiento {
    private Pieza[][] board;
    private int[] from;
    private int[] to;
    private Pieza piezaMovida;
    private Pieza piezaCapturada;

    public MovimientoPieza(Pieza[][] board, int[] from, int[] to) {
        this.board = board;
        this.from = from;
        this.to = to;
        this.piezaMovida = board[from[0]][from[1]];
        this.piezaCapturada = board[to[0]][to[1]];
    }

    @Override
    public void ejecutar() {
        board[to[0]][to[1]] = piezaMovida;
        board[from[0]][from[1]] = null;
    }

    @Override
    public void deshacer() {
        board[from[0]][from[1]] = piezaMovida;
        board[to[0]][to[1]] = piezaCapturada;
    }
}
