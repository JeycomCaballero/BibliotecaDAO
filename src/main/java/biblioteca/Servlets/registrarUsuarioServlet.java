/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package biblioteca.Servlets;

import biblioteca.Dao.RolDAOImpl;
import biblioteca.Dao.UsuarioDaoImpl;
import biblioteca.model.ClRol;
import biblioteca.model.ClUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "registrarUsuarioServlet", urlPatterns = {"/registrarUsuarioServlet"})
public class registrarUsuarioServlet extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
 
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RolDAOImpl rolDao = new RolDAOImpl();
        List<ClRol> listaRoles = rolDao.listarTodos();

        request.setAttribute("roles", listaRoles);

        request.getRequestDispatcher("registroUsuario.jsp").forward(request, response);
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int documento = Integer.parseInt(request.getParameter("documento"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String contrasenia = request.getParameter("contrasenia");
        int estado = Integer.parseInt(request.getParameter("estado"));
        int idRol = Integer.parseInt(request.getParameter("rol"));

        ClRol rol = new ClRol();
        rol.setId(idRol);

        ClUsuario u = new ClUsuario();
        u.setDocumento(documento);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setEmail(email);
        u.setTelefono(telefono);
        u.setEstado(estado);
        u.setRol(rol);
        u.setContraseña(contrasenia);

        UsuarioDaoImpl dao = new UsuarioDaoImpl();
        boolean r = dao.registrarUsuario(u);

        if (r) {
            response.sendRedirect("listaUsuariosServlet?regist=ok");
        } else {
            response.sendRedirect("listaUsuariosServlet?regist=error");
        }
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
