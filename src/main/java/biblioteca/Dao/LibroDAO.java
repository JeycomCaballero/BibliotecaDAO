
package biblioteca.Dao;

import biblioteca.model.ClLibro;
import java.util.List;


public interface LibroDAO {
    List<ClLibro> listarTodos ();
    boolean registrarL (ClLibro libro, int idAutor);
    boolean borrarLibro (int idLibro);
    boolean actualizarLibro (ClLibro libro);
    ClLibro buscarLibroN (int id);
    boolean estaDisponible(int idLibro);
    
}
