import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class App {
  static String url = "jdbc:mysql://localhost:3306/javaDB";
  static String userName = "root";
  static String password = "root2004";

  public static void main(String[] args) {
    try (Connection conn = DriverManager.getConnection(url, userName, password);
         Scanner in = new Scanner(System.in)) {
      EstudianteServices service = new EstudianteServices();
      int opcion = 0;

      while (opcion != 6) {
        System.out.println("\n===== MENU ESTUDIANTES =====");
        System.out.println("1. Insertar Estudiante");
        System.out.println("2. Actualizar Estudiante");
        System.out.println("3. Eliminar Estudiante");
        System.out.println("4. Consultar todos");
        System.out.println("5. Consultar por correo");
        System.out.println("6. Salir");
        System.out.print("Seleccione una opción: ");
        opcion = in.nextInt();
        in.nextLine(); 

        switch (opcion) {
          case 1 -> service.insertarEstudiante(conn);
          case 2 -> service.actualizarEstudiante(conn);
          case 3 -> service.eliminarEstudiante(conn);
          case 4 -> service.consultarTodos(conn);
          case 5 -> service.consultarPorCorreo(conn);
          case 6 -> System.out.println("👋 Saliendo...");
          default -> System.out.println("❌ Opción inválida");
        }
      }

    } catch (Exception e) {
      System.out.println("❌ Error: " + e.getMessage());
    }
  }
}
