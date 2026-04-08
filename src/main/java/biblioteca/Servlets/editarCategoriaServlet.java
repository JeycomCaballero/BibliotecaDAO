/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
import javax.servlet.http.HttpSession;

@WebServlet(name = "editarCategoriaServlet", urlPatterns = {"/editarCategoriaServlet"})
public class editarCategoriaServlet extends HttpServlet {

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
        int idC = Integer.parseInt(request.getParameter("id"));
        CategoriaDAOImpl categoria = new CategoriaDAOImpl();
        ClCategoria oCategoria = categoria.buscarCategoria(idC);
        request.setAttribute("categoria", oCategoria);
        request.getRequestDispatcher("editarCategoria.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String descripcion = request.getParameter("descripcion");
        CategoriaDAOImpl categoria = new CategoriaDAOImpl();
        ClCategoria oCategoria = new ClCategoria();
        oCategoria.setId(id);
        oCategoria.setNombre(nombre);
        oCategoria.setDescripcion(descripcion);

        boolean result = categoria.actualizarCategoria(oCategoria);
        if (result) {
            response.sendRedirect("listaCategoriasServlet?result=editado");
        } else {
            response.sendRedirect("listaCategoriasServlet?result=error");

        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
