package biblioteca.Servlets;

import biblioteca.Dao.MultaDAO;
import biblioteca.Dao.MultaDAOImpl;
import biblioteca.model.ClMulta;
import biblioteca.model.ClPrestamo;
import biblioteca.model.ClUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "multasUsuarioServlet", urlPatterns = {"/multasUsuarioServlet"})
public class multasUsuarioServlet extends HttpServlet {

    MultaDAO multaDAO = new MultaDAOImpl();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String correo = (String) session.getAttribute("usuario");

        List<ClMulta> lista = multaDAO.listarPorUsuario(correo);

        double totalDineroPendiente = 0;
        int totalMultas = 0;
        int multasActivas = 0;
        int multasPagadas = 0;

        List<ClMulta> listaMultas = new ArrayList<>();

        for (ClMulta m : lista) {

            if (m.getDias() > 0 || m.getEstado() == 1) {

                totalMultas++;

                if (m.getEstado() == 0) {
                    multasActivas++;
                    totalDineroPendiente += m.getMonto();
                } else {
                    multasPagadas++;
                }

                listaMultas.add(m);
            }
        }

        request.setAttribute("listaMultas", listaMultas);
        request.setAttribute("totalDineroPendiente", totalDineroPendiente);
        request.setAttribute("totalMultas", totalMultas);
        request.setAttribute("multasActivas", multasActivas);
        request.setAttribute("multasPagadas", multasPagadas);

        request.getRequestDispatcher("multasUsuario.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));

        double montoReal = multaDAO.obtenerMontoPorPrestamo(idPrestamo);

        ClPrestamo prestamo = new ClPrestamo();
        prestamo.setIdPrestamo(idPrestamo);

        ClMulta multa = new ClMulta();
        multa.setMonto(montoReal);
        multa.setFechaPago(new Date(System.currentTimeMillis()));
        multa.setIdPrestamo(prestamo);

        multaDAO.pagarMulta(multa);
        response.sendRedirect("multasUsuarioServlet?success=pagado");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
