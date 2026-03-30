
package biblioteca.Servlets;

import biblioteca.Dao.LibroAutorDAOImpl;
import biblioteca.Dao.LibroDAOImpl;
import biblioteca.model.ClLibro;
import biblioteca.model.ClLibroAutor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@WebServlet(name = "librosDisponiblesServlet", urlPatterns = {"/librosDisponiblesServlet"})
public class librosDisponiblesServlet extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LibroAutorDAOImpl listarLibros = new LibroAutorDAOImpl();
        List<ClLibroAutor> librosL = listarLibros.listarTodos();
        request.setAttribute("libros", librosL);
        System.out.println("Libros encontrados: " + librosL.size());
        request.getRequestDispatcher("menu.jsp").forward(request, response);
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
