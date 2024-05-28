import java.util.Scanner;

public class ChessGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GestorJuego gestor = GestorJuego.obtenerInstancia();
        Pieza[][] board = gestor.obtenerTablero();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Elegir color");
            System.out.println("2. Jugar");
            System.out.println("3. Reiniciar");
            System.out.println("4. Salir");
            System.out.print("Elige una opción: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Clear the buffer

            switch (option) {
                case 1:
                    chooseColor(scanner, gestor);
                    break;
                case 2:
                    play(scanner, gestor, board);
                    break;
                case 3:
                    gestor = GestorJuego.obtenerInstancia();
                    System.out.println("El tablero se ha reiniciado.");
                    break;
                case 4:
                    System.out.println("Saliendo del juego.");
                    return;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private static void chooseColor(Scanner scanner, GestorJuego gestor) {
        System.out.println("Elige tu color:");
        System.out.println("1. Blanco");
        System.out.println("2. Negro");
        System.out.print("Elige una opción: ");
        int color = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer
        boolean esBlanco = color == 1;
        // Actualizar el turno del gestor
        if (!esBlanco) {
            gestor.cambiarTurno();
        }
    }

    private static void play(Scanner scanner, GestorJuego gestor, Pieza[][] board) {
        while (true) {
            printBoard(board);
            System.out.println("Es el turno de las " + (gestor.esTurnoBlanco() ? "blancas" : "negras"));
            System.out.print("Ingresa tu movimiento (ej. e2 e4) o 'menu' para volver al menú: ");
            String move = scanner.nextLine();

            if (move.equals("menu")) {
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

            Pieza pieza = board[from[0]][from[1]];
            if (pieza == null || pieza.esBlanca() != gestor.esTurnoBlanco()) {
                System.out.println("Movimiento no válido. Inténtalo de nuevo.");
                continue;
            }

            if (pieza.movimientoValido(from, to, board)) {
                ComandoMovimiento comando = new MovimientoPieza(board, from, to);
                comando.ejecutar();
                gestor.cambiarTurno();
            } else {
                System.out.println("Movimiento no válido. Inténtalo de nuevo.");
            }
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
                    System.out.print(pieza.esBlanca() ? "T " : "t ");
                } else if (pieza instanceof Alfil) {
                    System.out.print(pieza.esBlanca() ? "A " : "a ");
                } else if (pieza instanceof Caballo) {
                    System.out.print(pieza.esBlanca() ? "H " : "h ");
                }
            }
            System.out.println();
        }
    }
}
