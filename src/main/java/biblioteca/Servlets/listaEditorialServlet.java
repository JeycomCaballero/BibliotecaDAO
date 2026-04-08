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
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "listaEditorialServlet", urlPatterns = {"/listaEditorialServlet"})
public class listaEditorialServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EditorialDAO dao = new EditorialDAOImlp();

        List<ClEditorial> lista = dao.listarTodos();

        request.setAttribute("editoriales", lista);

        request.getRequestDispatcher("editoriales.jsp").forward(request, response);
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
