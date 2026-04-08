package biblioteca.Servlets;

import biblioteca.Dao.AutorDAOImpl;
import biblioteca.Dao.CategoriaDAOImpl;
import biblioteca.Dao.EditorialDAOImlp;
import biblioteca.Dao.LibroAutorDAOImpl;
import biblioteca.Dao.LibroDAOImpl;
import biblioteca.model.ClAutor;
import biblioteca.model.ClCategoria;
import biblioteca.model.ClEditorial;
import biblioteca.model.ClLibro;
import biblioteca.model.ClLibroAutor;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.SimpleFormatter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

@WebServlet(name = "editarLibroServlet", urlPatterns = {"/editarLibroServlet"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 15
)
public class editarLibroServlet extends HttpServlet {

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

        int idL = Integer.parseInt(request.getParameter("id"));
        LibroAutorDAOImpl libroAutor = new LibroAutorDAOImpl();
        ClLibroAutor l = libroAutor.listarPorId(idL);
        CategoriaDAOImpl categorias = new CategoriaDAOImpl();
        EditorialDAOImlp editoriales = new EditorialDAOImlp();
        AutorDAOImpl autores = new AutorDAOImpl();
        List<ClCategoria> listaC = categorias.listarTodos();
        List<ClEditorial> listaE = editoriales.listarTodos();
        List<ClAutor> listaA = autores.listarTodos();
        request.setAttribute("categorias", listaC);
        request.setAttribute("editoriales", listaE);
        request.setAttribute("autores", listaA);
        request.setAttribute("libro", l);
        request.getRequestDispatcher("editarLibro.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        ClLibro oLibro = new ClLibro();

        int id = Integer.parseInt(request.getParameter("id"));
        String titulo = request.getParameter("titulo");
        String isbn = request.getParameter("isbn");
        int paginas = Integer.parseInt(request.getParameter("paginas"));

        String fechaStr = request.getParameter("anio");
        Date año = null;
        if (fechaStr != null && !fechaStr.isEmpty()) {
            año = Date.valueOf(fechaStr);
        }

        int categoria = Integer.parseInt(request.getParameter("idCategoria"));
        int editorial = Integer.parseInt(request.getParameter("idEditorial"));
        int autor = Integer.parseInt(request.getParameter("idAutor"));
        int disponibilidad = Integer.parseInt(request.getParameter("disponible"));
        String imagenAntigua = request.getParameter("");
        
        Part filePart = request.getPart("imagen");

        if (filePart != null && filePart.getSize() > 0) {

            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();

            String fileNameFinal = System.currentTimeMillis() + "_" + fileName;

            String uploadPath = getServletContext().getRealPath("/img");

            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            filePart.write(uploadPath + File.separator + fileNameFinal);

            oLibro.setImagen("img/" + fileNameFinal);

        } else if (filePart == null || filePart.getSize() <= 0) {
            oLibro.setImagen(imagenAntigua);
        } else {
            request.setAttribute("hecho", false);
            request.setAttribute("accion", "actualizado");
            request.getRequestDispatcher("libros.jsp").forward(request, response);
        }

        oLibro.setId(id);
        oLibro.setTitulo(titulo);
        oLibro.setIsbn(isbn);
        oLibro.setNumeroPaginas(paginas);
        oLibro.setAñoL(año);
        oLibro.setDisponible(disponibilidad);

        ClCategoria cat = new ClCategoria();
        cat.setId(categoria);
        oLibro.setCategoria(cat);

        ClEditorial edi = new ClEditorial();
        edi.setId(editorial);
        oLibro.setEditorial(edi);

        ClAutor aut = new ClAutor();
        aut.setId(autor);

        LibroDAOImpl daoL = new LibroDAOImpl();
        LibroAutorDAOImpl dao = new LibroAutorDAOImpl();

        boolean resultL = daoL.actualizarLibro(oLibro);
        boolean resultA = dao.actualizar(id, autor);

        if (resultL && resultA) {
            response.sendRedirect("listaLibrosServlet?result=editado");
        } else {
            response.sendRedirect("listaLibrosServlet?result=error");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
