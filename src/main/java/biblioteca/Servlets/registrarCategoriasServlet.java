package biblioteca.Servlets;

import biblioteca.Dao.CategoriaDAOImpl;
import biblioteca.model.ClCategoria;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "registrarCategoriasServlet", urlPatterns = {"/registrarCategoriasServlet"})
public class registrarCategoriasServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");

        ClCategoria oCategoria = new ClCategoria();
        oCategoria.setNombre(nombre);
        oCategoria.setDescripcion(descripcion);

        CategoriaDAOImpl categoria = new CategoriaDAOImpl();
        boolean registrarC = categoria.registrarCategoria(oCategoria);
        if (registrarC) {
            response.sendRedirect("listaCategoriasServlet?regist=registrado");
        } else {
            response.sendRedirect("listaCategoriasServlet?regist=error");

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
