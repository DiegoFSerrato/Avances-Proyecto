package piezas;

public class PiezaFactory {
    public static Pieza crearPieza(String tipo, boolean esBlanco) {
        switch (tipo) {
            case "Peon":
                return new Peon(esBlanco);
            case "Rey":
                return new Rey(esBlanco);
            case "Reina":
                return new Reina(esBlanco);
            case "Torre":
                return new Torre(esBlanco);
            case "Alfil":
                return new Alfil(esBlanco);
            case "Caballo":
                return new Caballo(esBlanco);
            default:
                throw new IllegalArgumentException("Tipo de pieza desconocido: " + tipo);
        }
    }
}
