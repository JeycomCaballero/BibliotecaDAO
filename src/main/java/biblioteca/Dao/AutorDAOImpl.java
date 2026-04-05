package biblioteca.Dao;

import biblioteca.model.ClAutor;
import biblioteca.util.conexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAOImpl implements AutorDAO {

    PreparedStatement pm;

    @Override
    public List<ClAutor> listarTodos() {
        List<ClAutor> lista = new ArrayList<>();
        String sql = "select * from autor";
        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql);ResultSet rs = ps.executeQuery()){
            while (rs.next()) {
                lista.add(mapear(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    

    @Override
    public boolean registrarAutor(ClAutor autorR) {
        Connection con = conexionBD.conectar();

        String consulta = "insert into autor (nombres,apellidos,nacionalidad,fechaNacimiento) values (?,?,?,?)";
        try {
            pm = con.prepareStatement(consulta);
            pm.setString(1, autorR.getNombres());
            pm.setString(2, autorR.getApellidos());
            pm.setString(3, autorR.getNacionalidad());
            pm.setDate(4, autorR.getFechaNacimiento());
            pm.executeUpdate();
            pm.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error al registrar autor");
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean eliminarAutor(String nombre) {
        Connection con = conexionBD.conectar();

        String consulta = "delete from autor where nombres = ?";
        try {
            pm = con.prepareStatement(consulta);
            pm.setString(1, nombre);
            pm.executeUpdate();
            pm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error eliminando autor");
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public boolean actualizarAutor(ClAutor autorR, String nombre2) {
        Connection con = conexionBD.conectar();

        String consulta = "update autor set nombres = ?, apellidos = ? where nombres = ?";
        try {
            pm = con.prepareStatement(consulta);
            pm.setString(1, autorR.getNombres());
            pm.setString(2, autorR.getApellidos());
            pm.setString(3, nombre2);
            pm.executeUpdate();
            pm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error actualizando autor");
            e.printStackTrace();
        }
        return false;

    }

    @Override
    public ClAutor buscarAutor(String nombre) {
        Connection con = conexionBD.conectar();
        ClAutor autor = null;

        String consulta = "select nombres, apellidos, nacionalidad from autor where nombres = ?";
        try {
            pm = con.prepareStatement(consulta);
            pm.setString(1, nombre);
            ResultSet rs = pm.executeQuery();
            if (rs.next()) {

                autor = mapear(rs);

            } else {
                System.out.println("Autor no encontrado");
            }
            pm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error buscando autor");
            e.printStackTrace();
        }
        return autor;
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
