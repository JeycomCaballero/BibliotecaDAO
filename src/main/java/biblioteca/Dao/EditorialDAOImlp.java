package biblioteca.Dao;

import biblioteca.util.conexionBD;
import biblioteca.model.ClEditorial;
import java.sql.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class EditorialDAOImlp implements EditorialDAO {

    @Override
    public List<ClEditorial> listarTodos() {

        List<ClEditorial> lista = new ArrayList<>();

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(
                "SELECT * FROM editorial ORDER BY nombre"); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapear(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public boolean registrarEditorial(ClEditorial e) {

        String sql = "INSERT INTO editorial (nombre, pais, sitioWeb) VALUES (?,?,?)";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getPais());
            ps.setString(3, e.getSitioWeb());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarEditorial(int idE) {

        String sql = "DELETE FROM editorial WHERE idEditorial = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idE);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean actualizarEditorial(ClEditorial e) {

        String sql = "UPDATE editorial SET nombre=?, pais=?, sitioWeb=? WHERE idEditorial=?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, e.getNombre());
            ps.setString(2, e.getPais());
            ps.setString(3, e.getSitioWeb());
            ps.setInt(4, e.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public ClEditorial buscarEditorial(int id) {

        String sql = "SELECT * FROM editorial WHERE idEditorial = ?";

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

    private ClEditorial mapear(ResultSet rs) throws SQLException {

        ClEditorial e = new ClEditorial();

        e.setId(rs.getInt("idEditorial"));
        e.setNombre(rs.getString("nombre"));
        e.setPais(rs.getString("pais"));
        e.setSitioWeb(rs.getString("sitioWeb"));

        return e;
    }

}
