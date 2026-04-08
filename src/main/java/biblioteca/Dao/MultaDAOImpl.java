package biblioteca.Dao;

import biblioteca.model.ClLibro;
import biblioteca.model.ClMulta;
import biblioteca.model.ClPrestamo;
import biblioteca.model.ClUsuario;
import biblioteca.util.conexionBD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MultaDAOImpl implements MultaDAO {

    @Override
    public List<ClMulta> listarTodos() {
        List<ClMulta> lista = new ArrayList<>();

        String consulta = "SELECT "
                + "m.idMulta, "
                + "u.nombre AS nombreUsuario, "
                + "u.apellido AS apellidoUsuario, "
                + "l.titulo, "
                + "p.idPrestamo, "
                + "p.fechaDevolucionEsperada, "
                + "p.fechaDevolucionReal, "
                + "CASE "
                + "WHEN m.estado = 1 THEN m.monto "
                + "WHEN p.fechaDevolucionReal IS NULL AND CURDATE() > p.fechaDevolucionEsperada "
                + "THEN DATEDIFF(CURDATE(), p.fechaDevolucionEsperada) * 1000 "
                + "WHEN p.fechaDevolucionReal IS NOT NULL AND p.fechaDevolucionReal > p.fechaDevolucionEsperada "
                + "THEN DATEDIFF(p.fechaDevolucionReal, p.fechaDevolucionEsperada) * 1000 "
                + "ELSE 0 END AS montoCalculado, "
                + "m.estado, "
                + "m.fechaGeneracion, "
                + "m.fechaPago "
                + "FROM prestamo p "
                + "INNER JOIN usuario u ON p.idUsuario = u.idUsuario "
                + "INNER JOIN libro l ON p.idLibro = l.idLibro "
                + "INNER JOIN multa m ON p.idPrestamo = m.idPrestamo";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapearCompleto(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public List<ClMulta> listarPorUsuario(String usuario) {
        List<ClMulta> lista = new ArrayList<>();

        String sql = "SELECT "
                + "p.idPrestamo, "
                + "l.titulo, "
                + "p.fechaDevolucionEsperada, "
                + "p.fechaDevolucionReal, "
                + "CASE "
                + "WHEN m.estado = 1 THEN m.monto "
                + "WHEN p.fechaDevolucionReal IS NULL AND CURDATE() > p.fechaDevolucionEsperada "
                + "THEN DATEDIFF(CURDATE(), p.fechaDevolucionEsperada) * 1000 "
                + "WHEN p.fechaDevolucionReal IS NOT NULL AND p.fechaDevolucionReal > p.fechaDevolucionEsperada "
                + "THEN DATEDIFF(p.fechaDevolucionReal, p.fechaDevolucionEsperada) * 1000 "
                + "ELSE 0 END AS montoCalculado, "
                + "IFNULL(m.idMulta, 0) AS idMulta, "
                + "IFNULL(m.estado, 0) AS estado, "
                + "m.fechaGeneracion, "
                + "m.fechaPago "
                + "FROM prestamo p "
                + "INNER JOIN libro l ON p.idLibro = l.idLibro "
                + "INNER JOIN multa m ON p.idPrestamo = m.idPrestamo inner join usuario u on p.idUsuario = u.idUsuario where u.email = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, usuario);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ClMulta multa = mapear(rs);
                lista.add(multa);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public List<ClMulta> multaExistente(ClMulta multa) {
        List<ClMulta> lista = new ArrayList<>();

        String consulta = "SELECT * FROM multa WHERE idPrestamo = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)) {

            ps.setInt(1, multa.getIdPrestamo().getIdPrestamo());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapearBasico(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    @Override
    public boolean crearMulta(ClMulta multa) {

        String consulta = "INSERT INTO multa (monto, fechaGeneracion, estado, idPrestamo) VALUES (?, ?, 0, ?)";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)) {

            ps.setDouble(1, multa.getMonto());
            ps.setDate(2, multa.getFechaGeneracion());
            ps.setInt(3, multa.getIdPrestamo().getIdPrestamo());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean pagarMulta(ClMulta multa) {

        String consulta = "UPDATE multa SET estado = 1, monto = ?,fechaPago = ? WHERE idPrestamo = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)) {
            ps.setDouble(1, multa.getMonto());
            ps.setDate(2, multa.getFechaPago());
            ps.setInt(3, multa.getIdPrestamo().getIdPrestamo());

            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean devolucionL(ClMulta multa) {

        String consulta = "SELECT * FROM multa WHERE idPrestamo = ? AND estado = 0";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(consulta)) {

            ps.setInt(1, multa.getIdPrestamo().getIdPrestamo());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return false;
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public double obtenerMontoPorPrestamo(int idPrestamo) {
        String sql = "SELECT "
                + "CASE "
                + "WHEN m.estado = 1 THEN m.monto "
                + "WHEN p.fechaDevolucionReal IS NULL AND CURDATE() > p.fechaDevolucionEsperada "
                + "THEN DATEDIFF(CURDATE(), p.fechaDevolucionEsperada) * 1000 "
                + "WHEN p.fechaDevolucionReal IS NOT NULL AND p.fechaDevolucionReal > p.fechaDevolucionEsperada "
                + "THEN DATEDIFF(p.fechaDevolucionReal, p.fechaDevolucionEsperada) * 1000 "
                + "ELSE 0 END AS montoCalculado "
                + "FROM prestamo p LEFT JOIN multa m ON p.idPrestamo = m.idPrestamo "
                + "WHERE p.idPrestamo = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idPrestamo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getDouble("montoCalculado");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    @Override
    public ClMulta buscarMulta(int idMulta) {
        String sql = "SELECT idMulta, monto, fechaPago, estado, idPrestamo FROM multa WHERE idMulta = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idMulta);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapearParaEditar(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean actualizarMulta(ClMulta multa) {

        String sql = "UPDATE multa SET monto = ?, fechaPago = ?, estado = ? WHERE idMulta = ?";

        try (Connection cn = conexionBD.conectar(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setDouble(1, multa.getMonto());
            ps.setDate(2, multa.getFechaPago());
            ps.setInt(3, multa.getEstado());
            ps.setInt(4, multa.getIdMulta());

            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    @Override
    public boolean eliminarMulta(int idMulta) {
        String sql = "DELETE FROM multa WHERE idMulta = ?";
        try (Connection cn = conexionBD.conectar();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, idMulta);
            int filas = ps.executeUpdate();
            return filas > 0; 
           

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private ClMulta mapear(ResultSet rs) throws SQLException {

        ClPrestamo prestamo = new ClPrestamo();
        prestamo.setIdPrestamo(rs.getInt("idPrestamo"));

        ClLibro libro = new ClLibro();
        libro.setTitulo(rs.getString("titulo"));

        prestamo.setLibro(libro);

        ClMulta multa = new ClMulta(
                rs.getInt("idMulta"),
                rs.getDouble("montoCalculado"),
                rs.getDate("fechaGeneracion"),
                rs.getDate("fechaPago"),
                rs.getInt("estado"),
                prestamo
        );

        long dias = 0;

        Date fechaEsperada = rs.getDate("fechaDevolucionEsperada");
        Date fechaReal = rs.getDate("fechaDevolucionReal");

        if (fechaEsperada != null) {

            if (fechaReal == null) {
                dias = java.time.temporal.ChronoUnit.DAYS.between(
                        fechaEsperada.toLocalDate(),
                        java.time.LocalDate.now()
                );
            } else {
                dias = java.time.temporal.ChronoUnit.DAYS.between(
                        fechaEsperada.toLocalDate(),
                        fechaReal.toLocalDate()
                );
            }
        }

        multa.setDias((int) Math.max(dias, 0));

        return multa;
    }

    private ClMulta mapearBasico(ResultSet rs) throws SQLException {

        ClPrestamo prestamo = new ClPrestamo();
        prestamo.setIdPrestamo(rs.getInt("idPrestamo"));

        ClMulta multa = new ClMulta(
                rs.getInt("idMulta"),
                rs.getDouble("monto"),
                rs.getDate("fechaGeneracion"),
                rs.getDate("fechaPago"),
                rs.getInt("estado"),
                prestamo
        );

        long dias = 0;

        Date fechaEsperada = rs.getDate("fechaDevolucionEsperada");
        Date fechaReal = rs.getDate("fechaDevolucionReal");

        if (fechaEsperada != null) {

            if (fechaReal == null) {
                dias = java.time.temporal.ChronoUnit.DAYS.between(
                        fechaEsperada.toLocalDate(),
                        java.time.LocalDate.now()
                );
            } else {
                dias = java.time.temporal.ChronoUnit.DAYS.between(
                        fechaEsperada.toLocalDate(),
                        fechaReal.toLocalDate()
                );
            }
        }

        multa.setDias((int) Math.max(dias, 0));

        return multa;
    }

    private ClMulta mapearCompleto(ResultSet rs) throws SQLException {

        ClPrestamo prestamo = new ClPrestamo();
        prestamo.setIdPrestamo(rs.getInt("idPrestamo"));

        ClLibro libro = new ClLibro();
        libro.setTitulo(rs.getString("titulo"));
        prestamo.setLibro(libro);

        ClUsuario usuario = new ClUsuario();
        usuario.setNombre(rs.getString("nombreUsuario"));
        usuario.setApellido(rs.getString("apellidoUsuario"));
        prestamo.setUsuario(usuario);

        ClMulta multa = new ClMulta();
        multa.setIdMulta(rs.getInt("idMulta"));
        multa.setMonto(rs.getDouble("montoCalculado"));
        multa.setFechaGeneracion(rs.getDate("fechaGeneracion"));
        multa.setFechaPago(rs.getDate("fechaPago"));
        multa.setEstado(rs.getInt("estado"));
        multa.setIdPrestamo(prestamo);

        return multa;
    }

    private ClMulta mapearParaEditar(ResultSet rs) throws SQLException {
        ClMulta multa = new ClMulta();
        multa.setIdMulta(rs.getInt("idMulta"));
        multa.setMonto(rs.getDouble("monto"));
        multa.setFechaPago(rs.getDate("fechaPago"));
        multa.setEstado(rs.getInt("estado"));

        ClPrestamo prestamo = new ClPrestamo();
        prestamo.setIdPrestamo(rs.getInt("idPrestamo"));
        multa.setIdPrestamo(prestamo);

        return multa;
    }
}
