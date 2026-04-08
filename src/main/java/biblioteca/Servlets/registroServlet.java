
package biblioteca.Servlets;

import biblioteca.Dao.UsuarioDaoImpl;
import biblioteca.model.ClUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "registroServlet", urlPatterns = {"/registroServlet"})
public class registroServlet extends HttpServlet {

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
        int documento = Integer.parseInt(request.getParameter("documento"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        String contrasenia = request.getParameter("contrasenia");
        String confirmar = request.getParameter("confirmar");

        if (!contrasenia.equals(confirmar)) {
            request.setAttribute("error", "Las contraseñas no coinciden");
            request.setAttribute("correoPreRegistro", email);
            request.getRequestDispatcher("registro.jsp").forward(request, response);
            return;
        }

        ClUsuario usuario = new ClUsuario();
        usuario.setDocumento(documento);
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setEmail(email);
        usuario.setTelefono(telefono);
        usuario.setContraseña(contrasenia);
        usuario.setEstado(1);

        UsuarioDaoImpl dao = new UsuarioDaoImpl();
        boolean registrado = dao.registrarUsuario(usuario);

        if (registrado) {
            response.sendRedirect("login.jsp?registro=ok");
        } else {
            request.setAttribute("error", "Error al registrar usuario");
            request.setAttribute("correoPreRegistro", email);
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
    }

   
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
