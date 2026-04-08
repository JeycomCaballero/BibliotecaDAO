package biblioteca.Servlets;

import biblioteca.Dao.LibroAutorDAOImpl;
import biblioteca.Dao.LibroDAOImpl;
import biblioteca.Dao.PrestamoDAOImpl;
import biblioteca.Dao.UsuarioDaoImpl;
import biblioteca.model.ClLibro;
import biblioteca.model.ClLibroAutor;
import biblioteca.model.ClPrestamo;
import biblioteca.model.ClUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "registroPrestamoServlet", urlPatterns = {"/registroPrestamoServlet"})
public class registroPrestamoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LibroAutorDAOImpl listarLibros = new LibroAutorDAOImpl();
        List<ClLibroAutor> librosL = listarLibros.listarTodos();
        request.setAttribute("libros", librosL);
        request.getRequestDispatcher("reservation.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        if (sesion == null || sesion.getAttribute("usuario") == null) {
            request.setAttribute("error", "debes Iniciar Sesion para  registrar un Prestamo");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        try {

            int idLibro = Integer.parseInt(request.getParameter("libro"));
            String fechaE = request.getParameter("fechaDevolucion");

            Date fechaEsperada;
            try {
                fechaEsperada = Date.valueOf(fechaE);
            } catch (Exception e) {
                request.setAttribute("error", "Formato de fecha inválido");

                LibroAutorDAOImpl listarLibros = new LibroAutorDAOImpl();
                request.setAttribute("libros", listarLibros.listarTodos());

                request.getRequestDispatcher("reservation.jsp").forward(request, response);
                return;
            }

            LibroDAOImpl libroDAO = new LibroDAOImpl();
            if (!libroDAO.estaDisponible(idLibro)) {

                request.setAttribute("error", "El libro no está disponible");

                LibroAutorDAOImpl listarLibros = new LibroAutorDAOImpl();
                request.setAttribute("libros", listarLibros.listarTodos());

                request.getRequestDispatcher("reservation.jsp").forward(request, response);
                return;
            }

            String usuario = (String) sesion.getAttribute("usuario");
            UsuarioDaoImpl buscar = new UsuarioDaoImpl();
            int idUsuario = buscar.buscarId(usuario);

            ClUsuario usuarioId = new ClUsuario();
            usuarioId.setId(idUsuario);

            Date fechaC = Date.valueOf(LocalDate.now());

            ClLibro libroId = new ClLibro();
            libroId.setId(idLibro);

            ClPrestamo oPrestamo = new ClPrestamo();
            oPrestamo.setFechaPrestamo(fechaC);
            oPrestamo.setFechaDevolucionEsperada(fechaEsperada);
            oPrestamo.setEstado(1);
            oPrestamo.setLibro(libroId);
            oPrestamo.setUsuario(usuarioId);

            PrestamoDAOImpl registrarP = new PrestamoDAOImpl();
            registrarP.registrarPrestamo(oPrestamo);

            response.sendRedirect("listaPrestamoServlet");

        } catch (Exception e) {
            e.printStackTrace();

            request.setAttribute("error", "Error al registrar el préstamo");

            LibroAutorDAOImpl listarLibros = new LibroAutorDAOImpl();
            request.setAttribute("libros", listarLibros.listarTodos());

            request.getRequestDispatcher("reservation.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
