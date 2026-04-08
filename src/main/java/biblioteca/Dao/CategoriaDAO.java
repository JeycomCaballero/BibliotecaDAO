package biblioteca.Dao;

import biblioteca.model.ClCategoria;
import java.util.List;

public interface CategoriaDAO {
    List<ClCategoria> listarTodos ();

    boolean registrarCategoria(ClCategoria categoria);

    boolean eliminarCategoria(int idCategoria);

    boolean actualizarCategoria(ClCategoria categoria);

    ClCategoria buscarCategoria(int id);

}
