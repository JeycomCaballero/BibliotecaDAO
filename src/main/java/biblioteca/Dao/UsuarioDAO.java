
package biblioteca.Dao;

import biblioteca.model.ClUsuario;
import java.util.List;


public interface UsuarioDAO {
    List<ClUsuario> listarTodos ();
    boolean registrarUsuario (ClUsuario u);
    ClUsuario buscarUsuario (int id);
    boolean actualizarUsuario (ClUsuario u);
    boolean actualizarUsuarioA (ClUsuario u);
    boolean eliminarUsuario (int id);
    boolean verificarLogin (String nombre, String contraseña);
    String  buscarRol (String correo);
    int buscarId (String correo);
    
}
