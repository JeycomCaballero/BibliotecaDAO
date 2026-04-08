package biblioteca.Servlets;

import biblioteca.Dao.CategoriaDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "eliminarCategoriaServlet", urlPatterns = {"/eliminarCategoriaServlet"})
public class eliminarCategoriaServlet extends HttpServlet {

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
        CategoriaDAOImpl categoria = new CategoriaDAOImpl();
        boolean eliminar = categoria.eliminarCategoria(id);
        if (eliminar) {
            response.sendRedirect("listaCategoriasServlet?resultado=eliminado");
        } else {
            response.sendRedirect("listaCategoriasServlet?resultado=error");

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
