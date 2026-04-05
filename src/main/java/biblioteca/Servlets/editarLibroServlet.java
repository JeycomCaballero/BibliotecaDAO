
package biblioteca.Servlets;

import biblioteca.Dao.AutorDAOImpl;
import biblioteca.Dao.CategoriaDAOImpl;
import biblioteca.Dao.EditorialDAOImlp;
import biblioteca.Dao.LibroAutorDAOImpl;
import biblioteca.model.ClAutor;
import biblioteca.model.ClCategoria;
import biblioteca.model.ClEditorial;
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


@WebServlet(name = "editarLibroServlet", urlPatterns = {"/editarLibroServlet"})
public class editarLibroServlet extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idL = Integer.parseInt(request.getParameter("id"));
        LibroAutorDAOImpl libroAutor = new LibroAutorDAOImpl();
        ClLibroAutor l = libroAutor.listarPorId(idL);
        CategoriaDAOImpl categorias = new CategoriaDAOImpl();
        EditorialDAOImlp  editoriales = new EditorialDAOImlp();
        AutorDAOImpl autores = new AutorDAOImpl();
        List<ClCategoria> listaC = categorias.listarTodos();
        List<ClEditorial>listaE = editoriales.listarTodos();
        List<ClAutor> listaA = autores.listarTodos();
        request.setAttribute("categorias", listaC);
        request.setAttribute("editoriales", listaE);
        request.setAttribute("autores", listaA);
        request.setAttribute("libro", l);        
        request.getRequestDispatcher("editarLibro.jsp").forward(request, response);
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
