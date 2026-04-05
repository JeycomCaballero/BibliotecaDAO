package biblioteca.Dao;

import biblioteca.model.ClLibro;
import biblioteca.util.conexionBD;
import biblioteca.model.ClEditorial;
import biblioteca.model.ClCategoria;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroDAOImpl implements LibroDAO {

    @Override
    public List<ClLibro> listarTodos() {
        List<ClLibro> lista = new ArrayList<>();
        
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement("select l.idLibro, l.titulo, l.isbn, l.añoPublicacion, l.numPaginas, l.disponible, l.idEditorial, l.idCategoria, a.idAutor, a.nombres, a.apellidos, a.nacionalidad, a.fechaNacimiento, c.idCategoria as c_id, c.nombre as c_nombre, c.descripcion as c_descripcion, e.idEditorial as e_id, e.nombre as e_nombre, e.pais, e.sitioWeb from libro l inner join libro_autor la on l.idLibro = la.idLibro inner join autor a on la.idAutor = a.idAutor inner join categoria c on l.idCategoria = c.idCategoria inner join editorial e on l.idEditorial = e.idEditorial");ResultSet rs = ps.executeQuery()){
            while(rs.next()){
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean registrarL(ClLibro libro) {

        String consulta = "insert into libro (titulo,isbn,añoPublicacion,numPaginas,disponible,idCategoria,idEditorial)";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)) {
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getIsbn());
            ps.setDate(3, libro.getAñoL());
            ps.setInt(4, libro.getNumeroPaginas());
            ps.setInt(5, libro.getDisponible());
            ps.setInt(6, libro.getCategoria().getId());
            ps.setInt(7, libro.getEditorial().getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean registrarAutorLibro(int idLibro, int idAutor) {

        String consulta = "insert into libro_autor(id_libro,id_autor) values (?,?)";
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)) {
            ps.setInt(1, idLibro);
            ps.setInt(2, idAutor);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean borrarLibro(int id) {
        String sql = "delete from libro_autor where id_libro = ?";
        String consulta = "delete from libro where id_libro = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql); PreparedStatement ps2 = cn.prepareStatement(consulta)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            ps2.setInt(1, id);
            ps2.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizarLibro(ClLibro libro) {

        String consulta = "update libro set titulo = ? , isbn = ? , añoPublicacion = ? , numPaginas = ? ,disponible = ?, idCategoria = ?, idEditorial= ? where idLibro = ?";
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)){
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getIsbn());
            ps.setDate(3, libro.getAñoL());
            ps.setInt(4, libro.getNumeroPaginas());
            ps.setInt(5, libro.getDisponible());
            ps.setInt(6, libro.getCategoria().getId());
            ps.setInt(7, libro.getEditorial().getId());
            ps.setInt(8, libro.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ClLibro buscarLibroN(int id) {

        String consulta = "select id_libro,titulo,isbn,num_paginas from libro where idLibro = ?";
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta);ResultSet rs = ps.executeQuery()) {
            ps.setInt(1, id);
            if (rs.next()) {
                return mapear(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ClLibro mapear(ResultSet rs) throws SQLException {

    ClEditorial editorial = new ClEditorial(
            rs.getInt("e_id"),
            rs.getString("e_nombre"),
            rs.getString("pais"),
            rs.getString("sitioWeb")
    );

    ClCategoria categoria = new ClCategoria(
            rs.getInt("c_id"),
            rs.getString("c_nombre"),
            rs.getString("c_descripcion")
    );

    return new ClLibro(
            rs.getInt("idLibro"),
            rs.getString("titulo"),
            rs.getString("isbn"),
            rs.getDate("añoPublicacion"),
            rs.getInt("numPaginas"),
            rs.getInt("disponible"),
            editorial,
            categoria
    );
}

}
