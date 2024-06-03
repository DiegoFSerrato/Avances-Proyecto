public class ChessGame {
    public static void main(String[] args) {
        MenuManager menuManager = MenuManager.obtenerInstancia();
        GestorJuego gestor = GestorJuego.obtenerInstancia();
        Tablero tablero = gestor.obtenerTablero();

        System.out.println("Ingrese nombre del jugador blanco:");
        String nombreBlanco = menuManager.obtenerEntrada();
        System.out.println("Ingrese nombre del jugador negro:");
        String nombreNegro = menuManager.obtenerEntrada();

        Jugador jugadorBlanco = new Jugador(nombreBlanco, true, new JuegoMediator(gestor, tablero, null, null));
        Jugador jugadorNegro = new Jugador(nombreNegro, false, new JuegoMediator(gestor, tablero, null, null));
        ((JuegoMediator)jugadorBlanco.getMediator()).setJugadores(jugadorBlanco, jugadorNegro);
        ((JuegoMediator)jugadorNegro.getMediator()).setJugadores(jugadorBlanco, jugadorNegro);

        while (true) {
            int option = menuManager.mostrarMenuPrincipal();
            switch (option) {
                case 1:
                    ((JuegoMediator)jugadorBlanco.getMediator()).jugar();
                    break;
                case 2:
                    gestor.reiniciarTablero();
                    System.out.println("El tablero se ha reiniciado.");
                    break;
                case 3:
                    System.out.println("Saliendo del juego.");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }
}
