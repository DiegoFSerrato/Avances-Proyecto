import java.util.Scanner;

public class ChessGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorJuego gestor = GestorJuego.obtenerInstancia();
        Tablero tablero = gestor.obtenerTablero();

        System.out.println("Ingrese nombre del jugador blanco:");
        String nombreBlanco = scanner.nextLine();
        System.out.println("Ingrese nombre del jugador negro:");
        String nombreNegro = scanner.nextLine();

        Jugador jugadorBlanco = new Jugador(nombreBlanco, true);
        Jugador jugadorNegro = new Jugador(nombreNegro, false);

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Jugar");
            System.out.println("2. Reiniciar");
            System.out.println("3. Salir");
            System.out.print("Elige una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (option) {
                case 1:
                    jugar(scanner, gestor, tablero, jugadorBlanco, jugadorNegro);
                    break;
                case 2:
                    reiniciarJuego();
                    tablero = gestor.obtenerTablero(); // Obtener la nueva instancia del tablero
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

    private static void jugar(Scanner scanner, GestorJuego gestor, Tablero tablero, Jugador jugadorBlanco, Jugador jugadorNegro) {
        while (true) {
            printBoard(tablero.obtenerTablero());
            System.out.println("Es el turno de las " + (gestor.esTurnoBlanco() ? "blancas" : "negras"));
            System.out.print("Ingresa tu movimiento (ej. e2 e4) o '9' para volver al menú: ");
            String move = scanner.nextLine();

            if (move.equals("9")) {
                break;
            }

            String[] positions = move.split(" ");
            if (positions.length != 2) {
                System.out.println("Movimiento no válido. Inténtalo de nuevo.");
                continue;
            }

            int[] from = parsePosition(positions[0]);
            int[] to = parsePosition(positions[1]);

            if (from == null || to == null) {
                System.out.println("Posición no válida. Inténtalo de nuevo.");
                continue;
            }

            Jugador jugadorActual = gestor.esTurnoBlanco() ? jugadorBlanco : jugadorNegro;
            jugadorActual.hacerMovimiento(tablero, from, to);

            gestor.cambiarTurno();
        }
    }

    private static int[] parsePosition(String pos) {
        if (pos.length() != 2) return null;
        char col = pos.charAt(0);
        char row = pos.charAt(1);

        if (col < 'a' || col > 'h' || row < '1' || row > '8') return null;

        return new int[]{row - '1', col - 'a'};
    }

    private static void printBoard(Pieza[][] board) {
        for (int i = 7; i >= 0; i--) {
            for (int j = 0; j < 8; j++) {
                Pieza pieza = board[i][j];
                if (pieza == null) {
                    System.out.print(". ");
                } else if (pieza instanceof Peon) {
                    System.out.print(pieza.esBlanca() ? "P " : "p ");
                } else if (pieza instanceof Rey) {
                    System.out.print(pieza.esBlanca() ? "K " : "k ");
                } else if (pieza instanceof Reina) {
                    System.out.print(pieza.esBlanca() ? "Q " : "q ");
                } else if (pieza instanceof Torre) {
                    System.out.print(pieza.esBlanca() ? "R " : "r ");
                } else if (pieza instanceof Alfil) {
                    System.out.print(pieza.esBlanca() ? "B " : "b ");
                } else if (pieza instanceof Caballo) {
                    System.out.print(pieza.esBlanca() ? "N " : "n ");
                }
            }
            System.out.println();
        }
    }

    private static void reiniciarJuego() {
        GestorJuego.reiniciarInstancia();
    }
}
