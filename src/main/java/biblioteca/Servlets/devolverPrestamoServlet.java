/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package biblioteca.Servlets;

import biblioteca.Dao.MultaDAOImpl;
import biblioteca.Dao.PrestamoDAOImpl;
import biblioteca.model.ClMulta;
import biblioteca.model.ClPrestamo;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "devolverPrestamoServlet", urlPatterns = {"/devolverPrestamoServlet"})
public class devolverPrestamoServlet extends HttpServlet {

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
        int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));

        ClPrestamo p = new ClPrestamo();
        p.setIdPrestamo(idPrestamo);

        ClMulta multa = new ClMulta();
        multa.setIdPrestamo(p);

        MultaDAOImpl multaDAO = new MultaDAOImpl();

        if (!multaDAO.devolucionL(multa)) {
            request.setAttribute("error", "No puedes devolver el libro hasta pagar la multa.");
            request.getRequestDispatcher("listaPrestamoServlet").forward(request, response);
            return;
        }

        Date fechaDevolucionReal = Date.valueOf(LocalDate.now());

        p.setFechaDevolucionReal(fechaDevolucionReal);
        p.setEstado(0);

        PrestamoDAOImpl dao = new PrestamoDAOImpl();
        dao.actualizarPrestamo(p);

        response.sendRedirect("listaPrestamoServlet?success=devuelto");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
