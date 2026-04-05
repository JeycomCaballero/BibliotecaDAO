package biblioteca.Dao;

import biblioteca.model.ClAutor;
import biblioteca.model.ClCategoria;
import biblioteca.model.ClEditorial;
import biblioteca.model.ClLibro;
import biblioteca.model.ClPrestamo;
import biblioteca.model.ClRol;
import biblioteca.model.ClUsuario;
import biblioteca.util.conexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAOImpl implements PrestamoDAO {

    @Override
    public List<ClPrestamo> listarTodos() {
        List<ClPrestamo> lista = new ArrayList<>();

        String sql = "select p.idprestamo, l.titulo, u.nombre, p.fechaprestamo, p.fechadevolucionesperada, p.fechadevolucionreal, p.estado from prestamo p inner join libro l on p.idlibro = l.idlibro inner join usuario u on p.idusuario = u.idusuario";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearSimple(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public List<ClPrestamo> listarPrestamosPorCorreo(String correo) {
        List<ClPrestamo> lista = new ArrayList<>();

        String sql = "select p.idprestamo, l.titulo, u.nombre, p.fechaprestamo, p.fechadevolucionesperada, p.fechadevolucionreal, p.estado from prestamo p inner join libro l on p.idlibro = l.idlibro inner join usuario u on p.idusuario = u.idusuario where u.email = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearSimple(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public List<ClPrestamo> listarPrestamosPorNombre(String nombre) {
        List<ClPrestamo> lista = new ArrayList<>();

        String sql = "select p.idprestamo, l.titulo, u.nombre, p.fechaprestamo, p.fechadevolucionesperada, p.fechadevolucionreal, p.estado from prestamo p inner join libro l on p.idlibro = l.idlibro inner join usuario u on p.idusuario = u.idusuario where u.nombre like ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearSimple(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public boolean registrarPrestamo(ClPrestamo p) {

        String sql = "insert into prestamo(fechaprestamo, fechadevolucionesperada, fechadevolucionreal, estado, idlibro, idusuario) values (?,?,?,?,?,?)";
        String sql2 = "update libro set disponible = 0 where idLibro = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql); PreparedStatement ps2 = cn.prepareStatement(sql2)) {

            ps.setDate(1, p.getFechaPrestamo());
            ps.setDate(2, p.getFechaDevolucionEsperada());
            ps.setDate(3, p.getFechaDevolucionReal());
            ps.setInt(4, p.getEstado());
            ps.setInt(5, p.getLibro().getId());
            ps.setInt(6, p.getUsuario().getId());
            ps.executeUpdate();
            ps2.setInt(1, p.getLibro().getId());
            ps2.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean eliminarPrestamo(int idPrestamo) {

        String sql = "delete from prestamo where idprestamo = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idPrestamo);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public ClPrestamo buscarPrestamo(int idU) {
        Connection con = conexionBD.conectar();
        String consulta = "select us.nombre, pm.fecha_prestamo from usuario us join prestamo pm on us.id_usuario = pm.id_usuario where us.id_usuario = ?";
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta); ResultSet rs = ps.executeQuery()) {
            ps.setInt(1, idU);
            if (rs.next()) {
                return mapear(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean actualizarPrestamo(ClPrestamo p) {

        String sql = "update prestamo set fechadevolucionreal = ?, estado = ? where idprestamo = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setDate(1, p.getFechaDevolucionReal());
            ps.setInt(2, p.getEstado());
            ps.setInt(3, p.getIdPrestamo());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private ClPrestamo mapear(ResultSet rs) throws SQLException {
        ClEditorial editorial = new ClEditorial(
                rs.getInt("idEditorial"),
                rs.getString("nombre"),
                rs.getString("pais"),
                rs.getString("sitioWeb"));
        ClCategoria categoria = new ClCategoria(
                rs.getInt("idCategoria"),
                rs.getString("nombre"),
                rs.getString("descripcion"));
        ClLibro libro = new ClLibro(rs.getInt("idLibro"),
                rs.getString("titulo"),
                rs.getString("isbn"),
                rs.getDate("añoPublicacion"),
                rs.getInt("numPaginas"),
                rs.getInt("disponible"),
                editorial, categoria);
        ClRol rol = new ClRol(rs.getInt("idRol"),
                rs.getString("tipoRol"));
        ClAutor autor = new ClAutor(
                rs.getInt("idAutor"),
                rs.getString("nombres"),
                rs.getString("apellidos"),
                rs.getString("nacionalidad"),
                rs.getDate("fechaNacimiento")
        );
        ClUsuario usuario = new ClUsuario(
                rs.getInt("idUsuario"),
                rs.getInt("documento"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("email"), rs.getString("telefono"),
                rs.getInt("estado"), rs.getString("contraseña"), rol);
        return new ClPrestamo(rs.getInt("idPrestamo"),
                rs.getDate("fechaPrestamo"),
                rs.getDate("fechaDevolucionEsperada"),
                rs.getDate("fechaDevolucionReal"),
                rs.getInt("estado"),
                libro,
                usuario);
    }

    private ClPrestamo mapearSimple(ResultSet rs) throws SQLException {

        ClLibro libro = new ClLibro();
        libro.setTitulo(rs.getString("titulo"));

        ClUsuario usuario = new ClUsuario();
        usuario.setNombre(rs.getString("nombre"));

        return new ClPrestamo(
                rs.getInt("idprestamo"),
                rs.getDate("fechaprestamo"),
                rs.getDate("fechadevolucionesperada"),
                rs.getDate("fechadevolucionreal"),
                rs.getInt("estado"),
                libro,
                usuario
        );
    }
}
