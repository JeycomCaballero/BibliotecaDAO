/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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

/**
 *
 * @author jeyco
 */
@WebServlet(name = "registroPrestamoServlet", urlPatterns = {"/registroPrestamoServlet"})
public class registroPrestamoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LibroAutorDAOImpl listarLibros = new LibroAutorDAOImpl();
        List<ClLibroAutor> librosL = listarLibros.listarTodos();
        request.setAttribute("libros", librosL);
        request.getRequestDispatcher("reservation.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        String libro = request.getParameter("libro");
        int idLibro = 0;
        idLibro = Integer.parseInt(libro);
        String fechaE = request.getParameter("fechaDevolucion");
        Date fechaEsperada = Date.valueOf(fechaE);
        String usuario = (String) sesion.getAttribute("usuario");
        UsuarioDaoImpl buscar = new UsuarioDaoImpl();
        int idUsuario = buscar.buscarId(usuario);
        ClUsuario usuarioId = new ClUsuario();
        usuarioId.setId(idUsuario);
        LocalDate fechaActual = LocalDate.now();
        Date fechaC = Date.valueOf(fechaActual);
        ClLibro libroId = new ClLibro();
        libroId.setId(idLibro);

        PrestamoDAOImpl registrarP = new PrestamoDAOImpl();
        ClPrestamo oPrestamo = new ClPrestamo();
        oPrestamo.setFechaPrestamo(fechaC);
        oPrestamo.setFechaDevolucionEsperada(fechaEsperada);
        oPrestamo.setEstado(1);
        oPrestamo.setLibro(libroId);
        oPrestamo.setUsuario(usuarioId);
        registrarP.registrarPrestamo(oPrestamo);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
