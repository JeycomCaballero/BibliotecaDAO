/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package biblioteca.Servlets;

import biblioteca.Dao.EditorialDAO;
import biblioteca.Dao.EditorialDAOImlp;
import biblioteca.model.ClEditorial;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "editarEditorialServlet", urlPatterns = {"/editarEditorialServlet"})
public class editarEditorialServlet extends HttpServlet {

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

        EditorialDAO dao = new EditorialDAOImlp();

        ClEditorial e = dao.buscarEditorial(id);

        request.setAttribute("editorial", e);

        request.getRequestDispatcher("editarEditorial.jsp").forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        ClEditorial e = new ClEditorial();

        e.setId(Integer.parseInt(request.getParameter("id")));
        e.setNombre(request.getParameter("nombre"));
        e.setPais(request.getParameter("pais"));
        e.setSitioWeb(request.getParameter("sitioWeb"));

        EditorialDAO dao = new EditorialDAOImlp();

        boolean ok = dao.actualizarEditorial(e);

        if (ok) {
            response.sendRedirect("listaEditorialServlet?msg=editado");
        } else {
            response.sendRedirect("listaEditorialServlet?msg=error");
        }
    }

  
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
