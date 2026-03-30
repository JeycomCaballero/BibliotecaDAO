package biblioteca.Dao;

import biblioteca.util.conexionBD;
import biblioteca.model.ClEditorial;
import java.sql.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;


public class EditorialDAOImlp implements EditorialDAO{



    @Override
    public List<ClEditorial> listarTodos() {
        List<ClEditorial> lista = new ArrayList<>();
        
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement("select * from editorial order by nombre"); ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
        
    }

    
    
    @Override
    public boolean  registrarEditorial(ClEditorial editorialR) {
        

        String consulta = "insert into editorial (nombre,pais,sitio_web) values (?,?,?)";
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta);ResultSet rs = ps.executeQuery()){

            ps.setString(1, editorialR.getNombre());
            ps.setString(2, editorialR.getPais());
            ps.setString(3, editorialR.getSitioWeb());
            ps.executeUpdate();
            ps.close();
            cn.close();

        } catch (SQLException e) {
            System.out.println("Error al registrar editorial");
            e.printStackTrace();
        }
        return false;
    }
    
    @Override
    public boolean  eliminarEditorial(int idE) {
 

        String consulta = "delete from editorial where id = ?";
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta);ResultSet rs = ps.executeQuery()){

            ps.setInt(1, idE);
            ps.executeUpdate();
            ps.close();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error eliminando editorial");
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean  actualizarEditorial(ClEditorial actualizarE) {

        String consulta = "update editorial set nombre = ?, sitio_web = ? where id = ?";
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta);ResultSet rs = ps.executeQuery()){
            ps.setString(1, actualizarE.getNombre());
            ps.setString(2, actualizarE.getSitioWeb());
            ps.setInt(3, actualizarE.getId());
            ps.executeUpdate();
            ps.close();
            cn.close();
        } catch (SQLException e) {
            System.out.println("Error actualizando editorial");
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean buscarEditorial(int id) {

        String consulta = "select nombre, pais, sitio_web from editorial where id = ?";
        try (Connection cn = conexionBD.conectar();PreparedStatement ps = cn.prepareStatement(consulta);ResultSet rs = ps.executeQuery()){
            ps.setInt(1, id);
            if(rs.next()){
                System.out.println("Nombre: " + rs.getString("nombre"));
                System.out.println("Pais: " + rs.getString("pais"));
                System.out.println("Web: " + rs.getString("sitio_web"));
            }
            else{
                System.out.println("Editorial no encontrada");
            }
            ps.close();
            cn.close();
            System.out.println("Busqueda completa");
        } catch (SQLException e) {
            System.out.println("Error buscando Editorial");
            e.printStackTrace();
        }
        return false;
    }
    
    private ClEditorial mapear (ResultSet rs) throws SQLException{
        
        return new ClEditorial(
                rs.getInt("id_editorial"),
                rs.getString("nombre"),
                rs.getString("pais"),
                rs.getString("sitio_web")
        );
        
    }

}
