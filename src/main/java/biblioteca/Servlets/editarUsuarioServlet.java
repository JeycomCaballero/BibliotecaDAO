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
import javax.servlet.http.HttpSession;

@WebServlet(name = "editarUsuarioServlet", urlPatterns = {"/editarUsuarioServlet"})
public class editarUsuarioServlet extends HttpServlet {

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

        UsuarioDaoImpl dao = new UsuarioDaoImpl();
        ClUsuario usuario = dao.buscarUsuario(id);

        RolDAOImpl rolDao = new RolDAOImpl();
        List<ClRol> listaRoles = rolDao.listarTodos();

        request.setAttribute("usuario", usuario);
        request.setAttribute("roles", listaRoles);

        request.getRequestDispatcher("editarUsuario.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        int id = Integer.parseInt(request.getParameter("id"));
        int documento = Integer.parseInt(request.getParameter("documento"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        int estado = Integer.parseInt(request.getParameter("estado"));
        String contraseña = request.getParameter("contraseña");
        int idRol = Integer.parseInt(request.getParameter("rol"));

        ClUsuario u = new ClUsuario();
        u.setId(id);
        u.setDocumento(documento);
        u.setNombre(nombre);
        u.setApellido(apellido);
        u.setEmail(email);
        u.setTelefono(telefono);
        u.setEstado(estado);
        u.setContraseña(contraseña);

        ClRol rol = new ClRol();
        rol.setId(idRol);
        u.setRol(rol);

        UsuarioDaoImpl dao = new UsuarioDaoImpl();
        boolean result = dao.actualizarUsuarioA(u);

        if (result) {
            response.sendRedirect("listaUsuariosServlet?result=editado");
        } else {
            response.sendRedirect("listaUsuariosServlet?result=error");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
