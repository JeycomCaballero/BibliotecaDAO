package biblioteca.Dao;

import biblioteca.model.ClRol;
import biblioteca.model.ClUsuario;
import biblioteca.util.conexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoImpl implements UsuarioDAO {

    @Override
    public List<ClUsuario> listarTodos() {
        List<ClUsuario> lista = new ArrayList<>();

        String sql = "SELECT u.*, r.tipoRol FROM usuario u INNER JOIN rol r ON u.idRol = r.idRol ORDER BY u.nombre";

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
    public boolean actualizarUsuarioA(ClUsuario usuario) {

        String consulta = "UPDATE usuario SET documento = ?, nombre = ?, apellido = ?, email = ?, telefono = ?, estado = ?, contraseña = ?, idRol = ? WHERE idUsuario = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)) {

            ps.setInt(1, usuario.getDocumento());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getTelefono());
            ps.setInt(6, usuario.getEstado());
            ps.setString(7, usuario.getContraseña());
            ps.setInt(8, usuario.getRol().getId());
            ps.setInt(9, usuario.getId());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean registrarUsuario(ClUsuario u) {

        String sql = "INSERT INTO usuario (documento, nombre, apellido, email, telefono, estado, contraseña, idRol) VALUES (?,?,?,?,?,?,?,2)";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, u.getDocumento());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getTelefono());
            ps.setInt(6, u.getEstado());
            ps.setString(7, u.getContraseña());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error registrando usuario");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ClUsuario buscarUsuario(int id) {

        String sql = "SELECT u.*, r.tipoRol FROM usuario u INNER JOIN rol r ON u.idRol = r.idRol WHERE u.idUsuario = ?";

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

    @Override
    public boolean actualizarUsuario(ClUsuario u) {

        String sql = "UPDATE usuario SET nombre=?, apellido=?, telefono=? WHERE idUsuario=?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, u.getNombre());
            ps.setString(2, u.getApellido());
            ps.setString(3, u.getTelefono());
            ps.setInt(4, u.getId());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarUsuario(int id) {

        String sql = "DELETE FROM usuario WHERE idUsuario = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean verificarLogin(String email, String contraseña) {

        String sql = "SELECT * FROM usuario WHERE email = ? AND contraseña = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, contraseña);

            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public String buscarRol(String correo) {

        String sql = "SELECT r.tipoRol FROM usuario u INNER JOIN rol r ON u.idRol = r.idRol WHERE u.email = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("tipoRol");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int buscarId(String correo) {

        String sql = "SELECT idUsuario FROM usuario WHERE email = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, correo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("idUsuario");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    private ClUsuario mapear(ResultSet rs) throws SQLException {

        ClRol rol = new ClRol(
                rs.getInt("idRol"),
                rs.getString("tipoRol")
        );

        return new ClUsuario(
                rs.getInt("idUsuario"),
                rs.getInt("documento"),
                rs.getString("nombre"),
                rs.getString("apellido"),
                rs.getString("email"),
                rs.getString("telefono"),
                rs.getInt("estado"),
                rs.getString("contraseña"),
                rol
        );
    }
}
