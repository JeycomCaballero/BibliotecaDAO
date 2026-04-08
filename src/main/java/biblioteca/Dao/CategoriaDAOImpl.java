package biblioteca.Dao;

import biblioteca.model.ClCategoria;
import biblioteca.util.conexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO {

    @Override
    public List<ClCategoria> listarTodos() {
        List<ClCategoria> lista = new ArrayList<>();

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement("select * from categoria"); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (Exception e) {
        }
        return lista;
    }

    @Override
    public boolean registrarCategoria(ClCategoria categorias) {

        String consulta = "insert into categoria (nombre,descripcion) values (?,?)";
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)) {
            ps.setString(1, categorias.getNombre());
            ps.setString(2, categorias.getDescripcion());
            ps.executeUpdate();
            ps.close();
            cn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error agregando categoria");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminarCategoria(int categorias) {

        String consulta = "delete from categoria where idCategoria = ?";
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)) {
            ps.setInt(1, categorias);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("Error eliminando categoria");
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public boolean actualizarCategoria(ClCategoria categorias) {

        String consulta = "update categoria set nombre = ?, descripcion = ? where idCategoria = ?";
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)) {
            ps.setString(1, categorias.getNombre());
            ps.setString(2, categorias.getDescripcion());
            ps.setInt(3, categorias.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error actualizando categoria");
            e.printStackTrace();
            return false;

        }
    }

    @Override
    public ClCategoria buscarCategoria(int id) {

        String consulta = "SELECT idCategoria, nombre, descripcion FROM categoria WHERE idCategoria = ?";
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapear(rs);
            } else {
                System.out.println("Categoria no encontrada");
            }
            System.out.println("Busqueda completa");
        } catch (SQLException e) {
            System.out.println("Error buscando Categoria");
            e.printStackTrace();
        }
        return null;
    }

    private ClCategoria mapear(ResultSet rs) throws SQLException {
        return new ClCategoria(
                rs.getInt("idCategoria"),
                rs.getString("nombre"),
                rs.getString("descripcion")
        );
    }
}
