
package biblioteca.Dao;

import biblioteca.model.ClPrestamo;
import java.util.List;


public interface PrestamoDAO {
    
    List<ClPrestamo> listarTodos (); 
    boolean registrarPrestamo (ClPrestamo prestamoR);
    boolean eliminarPrestamo(int idU);
    boolean actualizarPrestamo (ClPrestamo prestamoR);
    ClPrestamo buscarPrestamo (int idU);
    List<ClPrestamo> listarPrestamosPorCorreo (String correo);
    List<ClPrestamo> listarPrestamosPorNombre (String nombre);
    
    
}
