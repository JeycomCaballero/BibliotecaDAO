package biblioteca.Servlets;

import biblioteca.Dao.PrestamoDAOImpl;
import biblioteca.model.ClPrestamo;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "listaPrestamoServlet", urlPatterns = {"/listaPrestamoServlet"})
public class listaPrestamoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        String correo = (String) session.getAttribute("usuario");
        String nombreBusqueda = request.getParameter("usuario");

        PrestamoDAOImpl dao = new PrestamoDAOImpl();
        List<ClPrestamo> lista;


        if (nombreBusqueda != null && !nombreBusqueda.trim().isEmpty()) {
            lista = dao.listarPrestamosPorNombre(nombreBusqueda);
        } 

        else {
            lista = dao.listarPrestamosPorCorreo(correo);
        }

        request.setAttribute("prestamos", lista);
        request.getRequestDispatcher("listaP.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para listar y buscar prestamos";
    }
}