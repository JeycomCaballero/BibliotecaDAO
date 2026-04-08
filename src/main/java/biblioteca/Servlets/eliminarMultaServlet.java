
package biblioteca.Servlets;

import biblioteca.Dao.MultaDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "eliminarMultaServlet", urlPatterns = {"/eliminarMultaServlet"})
public class eliminarMultaServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idMulta = Integer.parseInt(request.getParameter("id"));

        MultaDAOImpl dao = new MultaDAOImpl();
        boolean eliminado = dao.eliminarMulta(idMulta);

        if (eliminado) {
            response.sendRedirect("listaMultasServlet?resultado=eliminado");
        } else {
            response.sendRedirect("listaMultasServlet?resultado=error");
        }
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
