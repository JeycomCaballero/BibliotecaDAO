package biblioteca.Dao;

import biblioteca.model.ClAutor;
import biblioteca.util.conexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAOImpl implements AutorDAO {

    @Override
    public List<ClAutor> listarTodos() {

        List<ClAutor> lista = new ArrayList<>();
        String sql = "SELECT * FROM autor";

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
    public boolean registrarAutor(ClAutor a) {

        String sql = "INSERT INTO autor (nombres, apellidos, nacionalidad, fechaNacimiento) VALUES (?,?,?,?)";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, a.getNombres());
            ps.setString(2, a.getApellidos());
            ps.setString(3, a.getNacionalidad());
            ps.setDate(4, a.getFechaNacimiento());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarAutor(int id) {

        String sql = "DELETE FROM autor WHERE idAutor = ?";

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
    public boolean actualizarAutor(ClAutor a) {

        String sql = "UPDATE autor SET nombres=?, apellidos=?, nacionalidad=?, fechaNacimiento=? WHERE idAutor=?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, a.getNombres());
            ps.setString(2, a.getApellidos());
            ps.setString(3, a.getNacionalidad());
            ps.setDate(4, a.getFechaNacimiento());
            ps.setInt(5, a.getId());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ClAutor buscarAutor(int id) {

        String sql = "SELECT * FROM autor WHERE idAutor = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ClAutor mapear(ResultSet rs) throws SQLException {
        return new ClAutor(
                rs.getInt("idAutor"),
                rs.getString("nombres"),
                rs.getString("apellidos"),
                rs.getString("nacionalidad"),
                rs.getDate("fechaNacimiento")
        );
    }
}
