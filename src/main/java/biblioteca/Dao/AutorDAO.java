package biblioteca.Dao;

import biblioteca.model.*;
import java.util.List;

public interface AutorDAO {

    boolean  registrarAutor(ClAutor a);

    boolean actualizarAutor(ClAutor a, String nombre);

    boolean eliminarAutor(String nombre);

    ClAutor buscarAutor(String nombre);

}
