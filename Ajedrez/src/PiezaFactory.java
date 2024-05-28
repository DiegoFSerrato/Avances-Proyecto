public class PiezaFactory {
    public static Pieza crearPieza(String tipo, boolean esBlanca) {
        switch (tipo) {
            case "Peon":
                return new Peon(esBlanca);
            case "Rey":
                return new Rey(esBlanca);
            case "Reina":
                return new Reina(esBlanca);
            case "Torre":
                return new Torre(esBlanca);
            case "Alfil":
                return new Alfil(esBlanca);
            case "Caballo":
                return new Caballo(esBlanca);
            default:
                throw new IllegalArgumentException("Tipo de pieza no soportado.");
        }
    }
}
