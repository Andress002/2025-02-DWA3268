
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EstudianteServices {

    // INSERTAR
    public void insertarEstudiante(Connection conn) throws SQLException {
        try (Scanner in = new Scanner(System.in)) {
          System.out.print("Digite nombre del estudiante: ");
          String nombre = in.nextLine();
          System.out.print("Digite apellido del estudiante: ");
          String apellido = in.nextLine();
          System.out.print("Digite correo del estudiante: ");
          String correo = in.nextLine();
          System.out.print("Digite edad del estudiante: ");
          int edad = in.nextInt();
          in.nextLine(); // limpiar buffer
          System.out.print("Digite estado civil del estudiante: ");
          String estadoCivil = in.nextLine();

          String sql = "INSERT INTO estudiantes (nombre, apellido, correo, edad, estado_civil) VALUES (?,?,?,?,?)";
          var stm = conn.prepareStatement(sql);
          stm.setString(1, nombre);
          stm.setString(2, apellido);
          stm.setString(3, correo);
          stm.setInt(4, edad);
          stm.setString(5, estadoCivil);

          int rs = stm.executeUpdate();
          if (rs > 0) {
              System.out.println("✅ Registro insertado correctamente");
          } else {
              System.out.println("❌ Error en inserción");
          }
        }
    }

    // CONSULTAR TODOS
    public void consultarTodos(Connection conn) throws SQLException {
        String sql = "SELECT * FROM estudiantes";
        var stm = conn.prepareStatement(sql);
        ResultSet rs = stm.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String nombre = rs.getString("nombre");
            String apellido = rs.getString("apellido");
            String correo = rs.getString("correo");
            int edad = rs.getInt("edad");
            String estadoCivil = rs.getString("estado_civil");
            System.out.printf("ID: %d | %s %s | %s | %d años | %s\n",
                    id, nombre, apellido, correo, edad, estadoCivil);
        }
        System.out.println("✅ Consulta finalizada");
    }
    // ACTUALIZAR
    public void actualizarEstudiante(Connection conn) throws SQLException {
        try (Scanner in = new Scanner(System.in)) {
          System.out.print("Ingrese el correo del estudiante a actualizar: ");
          String correoBuscar = in.nextLine();

          System.out.print("Nuevo nombre: ");
          String nombre = in.nextLine();
          System.out.print("Nuevo apellido: ");
          String apellido = in.nextLine();
          System.out.print("Nueva edad: ");
          int edad = in.nextInt();
          in.nextLine(); // limpiar buffer
          System.out.print("Nuevo estado civil: ");
          String estado = in.nextLine();

          String sql = "UPDATE estudiantes SET nombre=?, apellido=?, edad=?, estado_civil=? WHERE correo=?";

          var stm = conn.prepareStatement(sql);
          stm.setString(1, nombre);
          stm.setString(2, apellido);
          stm.setInt(3, edad);
          stm.setString(4, estado);
          stm.setString(5, correoBuscar);

          int rs = stm.executeUpdate();
          if (rs > 0) {
              System.out.println("✅ Estudiante actualizado correctamente.");
          } else {
              System.out.println("⚠ No se encontro estudiante con ese correo.");
          }
        }
    }

    // ELIMINAR
    public void eliminarEstudiante(Connection conn) throws SQLException {
        try (Scanner in = new Scanner(System.in)) {
          System.out.print("Digite el correo del estudiante a eliminar: ");
          String correo = in.nextLine();

          String sql = "DELETE FROM estudiantes WHERE correo=?";
          var stm = conn.prepareStatement(sql);
          stm.setString(1, correo);

          int rs = stm.executeUpdate();
          if (rs > 0) {
              System.out.println("✅ Estudiante eliminado correctamente");
          } else {
              System.out.println("❌ No se encontro estudiante con ese correo");
          }
        }
    }

    // CONSULTAR POR CORREO
    public void consultarPorCorreo(Connection conn) throws SQLException {
        try (Scanner in = new Scanner(System.in)) {
          System.out.print("Digite el correo del estudiante a buscar: ");
          String correo = in.nextLine();

          String sql = "SELECT * FROM estudiantes WHERE correo=?";
          var stm = conn.prepareStatement(sql);
          stm.setString(1, correo);
          ResultSet rs = stm.executeQuery();

          if (rs.next()) {
              int id = rs.getInt("id");
              String nombre = rs.getString("nombre");
              String apellido = rs.getString("apellido");
              int edad = rs.getInt("edad");
              String estadoCivil = rs.getString("estado_civil");
              System.out.printf("ID: %d | %s %s | %s | %d años | %s\n",
                      id, nombre, apellido, correo, edad, estadoCivil);
          } else {
              System.out.println("❌ No se encontro estudiante con ese correo");
          }
        }
    }
}
