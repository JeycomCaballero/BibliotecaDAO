package biblioteca.Dao;

import biblioteca.model.ClRol;
import java.util.List;

public interface RolDAO {

    List<ClRol> listarTodos();

    boolean registrarRol(ClRol rol);

    boolean eliminarRol(int id);

    boolean actualizarRol(ClRol rol);

    ClRol buscarRol(int id);
}