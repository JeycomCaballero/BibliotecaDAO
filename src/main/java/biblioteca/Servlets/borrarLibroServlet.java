package biblioteca.Servlets;

import biblioteca.Dao.LibroDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "borrarLibroServlet", urlPatterns = {"/borrarLibroServlet"})
public class borrarLibroServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LibroDAOImpl eliminar = new LibroDAOImpl();
        int idEliminar = Integer.parseInt(request.getParameter("id"));
        eliminar.borrarLibro(idEliminar);
        try {
            response.sendRedirect("listaLibrosServlet?resultado=eliminado");

        } catch (Exception e) {
            response.sendRedirect("listaLibrosServlet?resultado=error");
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
