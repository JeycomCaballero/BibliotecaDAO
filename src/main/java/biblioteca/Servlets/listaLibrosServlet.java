
package biblioteca.Servlets;

import biblioteca.Dao.LibroAutorDAOImpl;
import biblioteca.Dao.LibroDAOImpl;
import biblioteca.model.ClAutor;
import biblioteca.model.ClLibro;
import biblioteca.model.ClLibroAutor;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "listaLibrosServlet", urlPatterns = {"/listaLibrosServlet"})
public class listaLibrosServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
  
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LibroAutorDAOImpl libroAutor = new LibroAutorDAOImpl();
        List<ClLibroAutor> listaA = libroAutor.listarTodos();
        request.setAttribute("libros", listaA);
        request.getRequestDispatcher("libros.jsp").forward(request, response);
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
