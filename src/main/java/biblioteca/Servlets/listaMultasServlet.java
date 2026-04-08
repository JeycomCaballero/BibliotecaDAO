package biblioteca.Servlets;

import biblioteca.Dao.MultaDAOImpl;
import biblioteca.model.ClMulta;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet(name = "listaMultasServlet", urlPatterns = {"/listaMultasServlet"})
public class listaMultasServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MultaDAOImpl multaDAO = new MultaDAOImpl();
        List<ClMulta> lista = multaDAO.listarTodos();
        List<ClMulta> listaFiltrada = new ArrayList<>();

        double totalDineroPendiente = 0;
        int totalMultas = 0;
        int multasActivas = 0;
        int multasPagadas = 0;

        for (ClMulta m : lista) {
            if (m.getMonto() > 0 || m.getIdMulta() > 0) {
                listaFiltrada.add(m);
                totalMultas++;

                if (m.getEstado() == 0) {
                    multasActivas++;
                    totalDineroPendiente += m.getMonto();
                } else {
                    multasPagadas++;
                }
            }
        }

        request.setAttribute("multas", listaFiltrada);
        request.setAttribute("totalDineroPendiente", totalDineroPendiente);
        request.setAttribute("totalMultas", totalMultas);
        request.setAttribute("multasActivas", multasActivas);
        request.setAttribute("multasPagadas", multasPagadas);

        request.getRequestDispatcher("multas.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
