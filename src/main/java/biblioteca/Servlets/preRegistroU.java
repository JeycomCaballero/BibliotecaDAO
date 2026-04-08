package biblioteca.Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

@WebServlet(name = "preRegistroU", urlPatterns = {"/preRegistroU"})
public class preRegistroU extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);    
        
        if (sesion == null || sesion.getAttribute("usuario")!= null) {
            request.setAttribute("error", "Ya iniciaste sesion, no es posible registrar un usuario");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        String correoRegistro = request.getParameter("correoR");

        request.setAttribute("correoPreRegistro", correoRegistro);

        RequestDispatcher redireccion = request.getRequestDispatcher("registro.jsp");
        redireccion.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
