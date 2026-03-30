package biblioteca.model;

public class ClLibroAutor {

    private int idLibroAutor;
    private ClLibro libro;
    private ClAutor autor;

    public ClLibroAutor() {
    }

    public ClLibroAutor(int idLibroAutor, ClLibro libro, ClAutor autor) {
        this.idLibroAutor = idLibroAutor;
        this.libro = libro;
        this.autor = autor;
    }

    public int getIdLibroAutor() {
        return idLibroAutor;
    }

    public void setIdLibroAutor(int idLibroAutor) {
        this.idLibroAutor = idLibroAutor;
    }

    public ClLibro getLibro() {
        return libro;
    }

    public void setLibro(ClLibro libro) {
        this.libro = libro;
    }

    public ClAutor getAutor() {
        return autor;
    }

    public void setAutor(ClAutor autor) {
        this.autor = autor;
    }

    
}