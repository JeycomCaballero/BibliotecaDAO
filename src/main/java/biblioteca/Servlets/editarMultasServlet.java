package biblioteca.Servlets;

import biblioteca.Dao.MultaDAOImpl;
import biblioteca.model.ClMulta;
import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "editarMultasServlet", urlPatterns = {"/editarMultasServlet"})
public class editarMultasServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idMulta = Integer.parseInt(request.getParameter("id"));

        MultaDAOImpl dao = new MultaDAOImpl();

        ClMulta multa = dao.buscarMulta(idMulta);

        if (multa != null) {
            double montoCalculado = dao.obtenerMontoPorPrestamo(multa.getIdPrestamo().getIdPrestamo());
            multa.setMonto(montoCalculado);
        }

        request.setAttribute("multa", multa);
        request.getRequestDispatcher("editarMulta.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        int idMulta = Integer.parseInt(request.getParameter("idMulta"));
        double monto = Double.parseDouble(request.getParameter("monto"));
        String fechaPagoStr = request.getParameter("fechaPago");
        int estado = Integer.parseInt(request.getParameter("estado"));

        Date fechaPago = null;
        if (fechaPagoStr != null && !fechaPagoStr.isEmpty()) {
            fechaPago = Date.valueOf(fechaPagoStr);
        }

        ClMulta multa = new ClMulta();
        multa.setIdMulta(idMulta);
        multa.setMonto(monto);
        multa.setFechaPago(fechaPago);
        multa.setEstado(estado);

        MultaDAOImpl dao = new MultaDAOImpl();
        boolean result = dao.actualizarMulta(multa);

        if (result) {
            response.sendRedirect("listaMultasServlet?result=editado");
        } else {
            response.sendRedirect("listaMultasServlet?result=error");
        }
    }

    @Override
    public String getServletInfo() {
        return "Servlet para editar multas y precargar monto dinámico";
    }
}