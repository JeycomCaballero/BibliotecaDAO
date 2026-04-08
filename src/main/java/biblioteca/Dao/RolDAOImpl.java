package biblioteca.Dao;

import biblioteca.model.ClRol;
import biblioteca.util.conexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolDAOImpl implements RolDAO {

    @Override
    public List<ClRol> listarTodos() {
        List<ClRol> lista = new ArrayList<>();

        String sql = "SELECT * FROM rol";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error listando roles");
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public boolean registrarRol(ClRol rol) {

        String sql = "INSERT INTO rol (tipoRol) VALUES (?)";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, rol.getTipoRol());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error registrando rol");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarRol(int id) {

        String sql = "DELETE FROM rol WHERE idRol = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error eliminando rol");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarRol(ClRol rol) {

        String sql = "UPDATE rol SET tipoRol = ? WHERE idRol = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, rol.getTipoRol());
            ps.setInt(2, rol.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error actualizando rol");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ClRol buscarRol(int id) {

        String sql = "SELECT * FROM rol WHERE idRol = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapear(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error buscando rol");
            e.printStackTrace();
        }

        return null;
    }

    private ClRol mapear(ResultSet rs) throws SQLException {
        return new ClRol(
                rs.getInt("idRol"),
                rs.getString("tipoRol")
        );
    }
}
