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

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement("select l.idLibro, l.titulo, l.isbn, l.añoPublicacion, l.numPaginas, l.disponible, l.idEditorial, l.idCategoria, l.imagen,a.idAutor, a.nombres, a.apellidos, a.nacionalidad, a.fechaNacimiento, c.idCategoria as c_id, c.nombre as c_nombre, c.descripcion as c_descripcion, e.idEditorial as e_id, e.nombre as e_nombre, e.pais, e.sitioWeb from libro l inner join libro_autor la on l.idLibro = la.idLibro inner join autor a on la.idAutor = a.idAutor inner join categoria c on l.idCategoria = c.idCategoria inner join editorial e on l.idEditorial = e.idEditorial"); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public boolean registrarL(ClLibro libro, int idAutor) {

        String consulta = "insert into libro (titulo,isbn,añoPublicacion,numPaginas,disponible,idCategoria,idEditorial,imagen) values (?,?,?,?,?,?,?,?)";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getIsbn());
            ps.setDate(3, libro.getAñoL());
            ps.setInt(4, libro.getNumeroPaginas());
            ps.setInt(5, libro.getDisponible());
            ps.setInt(6, libro.getCategoria().getId());
            ps.setInt(7, libro.getEditorial().getId());
            ps.setString(8, libro.getImagen());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            int idLibro = 0;
            if (rs.next()) {
                idLibro = rs.getInt(1);
            }

            String consulta2 = "insert into libro_autor(idLibro,idAutor) values (?,?)";
            try (PreparedStatement ps2 = cn.prepareStatement(consulta2)) {
                ps2.setInt(1, idLibro);
                ps2.setInt(2, idAutor);
                ps2.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean borrarLibro(int id) {
        String sql = "delete from libro_autor where idLibro = ?";
        String consulta = "delete from libro where idLibro = ?";

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
    public boolean estaDisponible(int idLibro) {
        String sql = "SELECT disponible FROM libro WHERE idLibro = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idLibro);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("disponible") == 1;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean actualizarLibro(ClLibro libro) {

        String consulta = "UPDATE libro SET titulo=?, isbn=?, añoPublicacion=?, numPaginas=?, disponible=?, idCategoria=?, idEditorial=? , imagen= ? WHERE idLibro=?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)) {

            ps.setString(1, libro.getTitulo());
            ps.setString(2, libro.getIsbn());
            ps.setDate(3, libro.getAñoL());
            ps.setInt(4, libro.getNumeroPaginas());
            ps.setInt(5, libro.getDisponible());
            ps.setInt(6, libro.getCategoria().getId());
            ps.setInt(7, libro.getEditorial().getId());
            ps.setString(8, libro.getImagen());
            ps.setInt(9, libro.getId());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public ClLibro buscarLibroN(int id) {

        String consulta = "select id_libro,titulo,isbn,num_paginas from libro where idLibro = ?";
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta); ResultSet rs = ps.executeQuery()) {
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
                categoria,
                rs.getString("imagen")
        );
    }

}
