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
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement("select u.*,r.tipoRol from usuario as u inner join rol as r on u.idRol = r.idRol order by u.nombre"); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public String buscarRol(String correo) {
        String sql = "select r.tipoRol from usuario as u inner join rol as r on u.idRol = r.idRol where u.email = ?";
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
        String sql = "select u.idUsuario from usuario as u inner join rol as r on u.idRol = r.idRol where u.email = ?";
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
    

    @Override
    public boolean verificarLogin(String email, String contraseña) {
        String consulta = "select * from usuario where email = ? and contraseña = ?";
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)) {
            ps.setString(1, email);
            ps.setString(2, contraseña);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean registrarUsuario(ClUsuario usuario) {

        String consulta = "call registrarUsuario (?,?,?,?,?,?,?)";
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)) {
            ps.setInt(1, usuario.getDocumento());
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getApellido());
            ps.setString(4, usuario.getEmail());
            ps.setString(5, usuario.getTelefono());
            ps.setInt(6, usuario.getEstado());
            ps.setInt(7, usuario.getRol().getId());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public ClUsuario buscarUsuario(int id) {

        String consulta = "select id_usuario, nombre, apellido, telefono from usuario where documento = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)) {

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
    public boolean actualizarUsuario(ClUsuario usuario) {

        String consulta = "update usuario set nombre = ? ,apellido = ? ,telefono = ? where idUsuario = ?";
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getTelefono());
            ps.setInt(4, usuario.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminarUsuario(int id) {

        String consulta = "delete from usuario where documento = ?";
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
