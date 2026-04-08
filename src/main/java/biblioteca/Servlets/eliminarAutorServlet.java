
package biblioteca.Servlets;

import biblioteca.Dao.AutorDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "eliminarAutorServlet", urlPatterns = {"/eliminarAutorServlet"})
public class eliminarAutorServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
    }

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        if (sesion == null || sesion.getAttribute("usuario") == null) {
            response.sendRedirect("librosDisponiblesIndexServlet");
            return;
        }
        int id = Integer.parseInt(request.getParameter("id"));

        AutorDAOImpl dao = new AutorDAOImpl();
        boolean r = dao.eliminarAutor(id);

        if (r) {
            response.sendRedirect("listaAutorServlet?result=eliminado");
        } else {
            response.sendRedirect("listaAutorServlet?result=error");
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
    }

}
