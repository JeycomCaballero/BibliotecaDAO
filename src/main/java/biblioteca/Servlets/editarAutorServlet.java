
package biblioteca.Servlets;

import biblioteca.Dao.AutorDAOImpl;
import biblioteca.model.ClAutor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import javax.servlet.http.HttpSession;

@WebServlet(name = "editarAutorServlet", urlPatterns = {"/editarAutorServlet"})
public class editarAutorServlet extends HttpServlet {

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
        ClAutor autor = dao.buscarAutor(id);

        request.setAttribute("autor", autor);
        request.getRequestDispatcher("editarAutor.jsp").forward(request, response);
    }
    

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        String nombres = request.getParameter("nombres");
        String apellidos = request.getParameter("apellidos");
        String nacionalidad = request.getParameter("nacionalidad");

        String fechaStr = request.getParameter("fechaNacimiento");
        Date fecha = null;

        if (fechaStr != null && !fechaStr.isEmpty()) {
            fecha = Date.valueOf(fechaStr);
        }

        ClAutor autor = new ClAutor();
        autor.setId(id);
        autor.setNombres(nombres);
        autor.setApellidos(apellidos);
        autor.setNacionalidad(nacionalidad);
        autor.setFechaNacimiento(fecha);

        AutorDAOImpl dao = new AutorDAOImpl();
        boolean result = dao.actualizarAutor(autor);

        if (result) {
            response.sendRedirect("listaAutorServlet?result=editado");
        } else {
            response.sendRedirect("listaAutorServlet?result=error");
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
