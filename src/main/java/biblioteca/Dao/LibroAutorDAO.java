
package biblioteca.Dao;

import biblioteca.model.ClLibroAutor;
import java.util.List;


public interface LibroAutorDAO {
    List<ClLibroAutor> listarTodos ();
    ClLibroAutor listarPorId (int id);
}
