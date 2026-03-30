package biblioteca.Dao;

import biblioteca.model.ClCategoria;
import biblioteca.util.conexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAOImpl implements CategoriaDAO{

    @Override
    public List<ClCategoria> listarTodos() {
        List<ClCategoria>lista = new ArrayList<>();
        
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement("select * from categoria");ResultSet rs = ps.executeQuery()){
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
        try(Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)) {
            ps.setString(1, categorias.getNombre());
            ps.setString(2, categorias.getDescripcion());
            ps.executeUpdate();
            ps.close();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error agregando categoria");
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean eliminarCategoria(int categorias) {

        String consulta = "delete from categoria where idCategoria = ?";
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)){
            ps.setInt(1, categorias);
            ps.executeUpdate();
            ps.close();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error eliminando categoria");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizarCategoria(ClCategoria categorias) {

        String consulta = "update categoria set nombre = ?, descripcion = ? where idCategoria = ?";
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)){
            ps.setString(1, categorias.getNombre());
            ps.setString(2, categorias.getDescripcion());
            ps.setInt(3, categorias.getId());
            ps.executeUpdate();
            ps.close();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error actualizando categoria");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean buscarCategoria(int id) {

        String consulta = "select nombre, descripcion from categoria where idCategoria = ?";
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta);ResultSet rs = ps.executeQuery()){
            ps.setInt(1, id);
            if (rs.next()) {
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Descripcion: " + rs.getString("descripcion"));
            } else {
                System.out.println("Categoria no encontrada");
            }
            rs.close();
            cn.close();
            System.out.println("Busqueda completa");
        } catch (SQLException e) {
            System.out.println("Error buscando Categoria");
            e.printStackTrace();
        }
        return false;
    }
    
    
    private ClCategoria mapear(ResultSet rs)throws SQLException{
        return new ClCategoria(
                rs.getInt("idCategoria"),
                rs.getString("nombre"),
                rs.getString("descripcion")
        );
    }
}
