package biblioteca.Dao;

import biblioteca.model.ClAutor;
import java.util.List;

public interface AutorDAO {

    List<ClAutor> listarTodos();

    boolean registrarAutor(ClAutor a);

    boolean eliminarAutor(int id);

    boolean actualizarAutor(ClAutor a);

    ClAutor buscarAutor(int id);
}
