
package biblioteca.Dao;

import biblioteca.model.ClAutor;
import biblioteca.model.ClLibro;
import biblioteca.model.ClLibroAutor;
import java.util.List;


public interface LibroAutorDAO {
    List<ClLibroAutor> listarTodos ();
    ClLibroAutor listarPorId (int id);
    boolean actualizar (int l, int au);
}
