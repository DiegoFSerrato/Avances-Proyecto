public class Jugador {
    private String nombre;
    private boolean esBlanco;

    public Jugador(String nombre, boolean esBlanco) {
        this.nombre = nombre;
        this.esBlanco = esBlanco;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean esBlanco() {
        return esBlanco;
    }

    public void hacerMovimiento(Tablero tablero, int[] from, int[] to) {
        Pieza pieza = tablero.obtenerTablero()[from[0]][from[1]];
        if (pieza != null && pieza.esBlanca() == this.esBlanco) {
            if (pieza.movimientoValido(from, to, tablero.obtenerTablero())) {
                ComandoMovimiento comando = new MovimientoPieza(tablero.obtenerTablero(), from, to);
                comando.ejecutar();
            } else {
                System.out.println("Movimiento no válido.");
            }
        } else {
            System.out.println("No es tu turno o no hay pieza en esa posición.");
        }
    }

    public void rendirse() {
        System.out.println(nombre + " se ha rendido.");
    }
}
