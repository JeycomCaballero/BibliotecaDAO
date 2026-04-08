package biblioteca.Dao;

import biblioteca.model.ClMulta;
import java.util.List;

public interface MultaDAO {

    List<ClMulta> listarTodos();

    List<ClMulta> multaExistente(ClMulta multa);

    boolean crearMulta(ClMulta multa);

    boolean pagarMulta(ClMulta multa);

    double obtenerMontoPorPrestamo(int idPrestamo);

    boolean devolucionL(ClMulta multa);

    List<ClMulta> listarPorUsuario(String usuario);

    boolean actualizarMulta(ClMulta multa);

    ClMulta buscarMulta(int idMulta);
    boolean eliminarMulta(int idMulta);
}
