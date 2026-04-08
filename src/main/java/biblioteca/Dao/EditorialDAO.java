package biblioteca.Dao;

import biblioteca.model.ClEditorial;
import java.util.List;

public interface EditorialDAO {

    List<ClEditorial> listarTodos();

    boolean registrarEditorial(ClEditorial e);

    boolean eliminarEditorial(int idE);

    boolean actualizarEditorial(ClEditorial e);

    ClEditorial buscarEditorial(int id);
}

