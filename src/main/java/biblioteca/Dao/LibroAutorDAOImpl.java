package biblioteca.Dao;

import biblioteca.model.ClAutor;
import biblioteca.model.ClCategoria;
import biblioteca.model.ClEditorial;
import biblioteca.model.ClLibro;
import biblioteca.model.ClLibroAutor;
import biblioteca.util.conexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroAutorDAOImpl implements LibroAutorDAO {

    @Override
    public List<ClLibroAutor> listarTodos() {
        List<ClLibroAutor> lista = new ArrayList<>();

        String sql = "SELECT "
                + "l.idLibro, l.titulo, l.isbn, l.añoPublicacion, l.numPaginas, l.disponible, l.idEditorial, l.idCategoria, "
                + "e.nombre AS editorialNombre, e.pais AS editorialPais, e.sitioWeb AS editorialWeb, "
                + "c.idCategoria AS catId, c.nombre AS catNombre, c.descripcion AS catDesc, "
                + "a.idAutor, a.nombres AS autorNombres, a.apellidos AS autorApellidos, a.nacionalidad AS autorNac, a.fechaNacimiento AS autorFecha, "
                + "la.idAutorLibro "
                + "FROM libro l "
                + "LEFT JOIN libro_autor la ON l.idLibro = la.idLibro "
                + "LEFT JOIN autor a ON la.idAutor = a.idAutor "
                + "INNER JOIN categoria c ON l.idCategoria = c.idCategoria "
                + "INNER JOIN editorial e ON l.idEditorial = e.idEditorial";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public ClLibroAutor listarPorId(int id) {

        String sql = "SELECT "
                + "l.idLibro, l.titulo, l.isbn, l.añoPublicacion, l.numPaginas, l.disponible, l.idEditorial, l.idCategoria, "
                + "e.nombre AS editorialNombre, e.pais AS editorialPais, e.sitioWeb AS editorialWeb, "
                + "c.idCategoria AS catId, c.nombre AS catNombre, c.descripcion AS catDesc, "
                + "a.idAutor, a.nombres AS autorNombres, a.apellidos AS autorApellidos, a.nacionalidad AS autorNac, a.fechaNacimiento AS autorFecha, "
                + "la.idAutorLibro "
                + "FROM libro l "
                + "LEFT JOIN libro_autor la ON l.idLibro = la.idLibro "
                + "LEFT JOIN autor a ON la.idAutor = a.idAutor "
                + "INNER JOIN categoria c ON l.idCategoria = c.idCategoria "
                + "INNER JOIN editorial e ON l.idEditorial = e.idEditorial where l.idLibro = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapear(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    

    private ClLibroAutor mapear(ResultSet rs) throws SQLException {
        ClEditorial editorial = new ClEditorial(
                rs.getInt("idEditorial"),
                rs.getString("editorialNombre"),
                rs.getString("editorialPais"),
                rs.getString("editorialWeb")
        );

        ClCategoria categoria = new ClCategoria(
                rs.getInt("catId"),
                rs.getString("catNombre"),
                rs.getString("catDesc")
        );

        ClLibro libro = new ClLibro(
                rs.getInt("idLibro"),
                rs.getString("titulo"),
                rs.getString("isbn"),
                rs.getDate("añoPublicacion"),
                rs.getInt("numPaginas"),
                rs.getInt("disponible"),
                editorial,
                categoria
        );

        ClAutor autor = new ClAutor(
                rs.getInt("idAutor"),
                rs.getString("autorNombres"),
                rs.getString("autorApellidos"),
                rs.getString("autorNac"),
                rs.getDate("autorFecha")
        );

        return new ClLibroAutor(
                rs.getInt("idAutorLibro"),
                libro,
                autor
        );
    }
}
