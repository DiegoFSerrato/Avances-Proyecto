import java.util.Scanner;

public class MenuManager {
    private static MenuManager instancia;
    private Scanner scanner;

    private MenuManager() {
        scanner = new Scanner(System.in);
    }

    public static MenuManager obtenerInstancia() {
        if (instancia == null) {
            instancia = new MenuManager();
        }
        return instancia;
    }

    public int mostrarMenuPrincipal() {
        System.out.println("Menu:");
        System.out.println("1. Jugar");
        System.out.println("2. Reiniciar");
        System.out.println("3. Salir");
        System.out.print("Elige una opción: ");
        int option = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer después de nextInt()
        return option;
    }

    public String obtenerMovimiento() {
        System.out.print("Ingresa tu movimiento (ej. e2 e4) o '9' para volver al menú: ");
        return scanner.nextLine().trim();
    }

    public String obtenerEntrada() {
        return scanner.nextLine().trim();
    }

    public int obtenerOpcion() {
        int option = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer después de nextInt()
        return option;
    }
}
