package biblioteca.model;

import java.sql.Date;

public class ClLibro {

    private int id;
    private String titulo;
    private String isbn;
    private Date añoL;
    private int numeroPaginas;
    private int disponible;
    private ClEditorial editorial;
    private ClCategoria categoria;
    private String imagen;

    public ClLibro() {
    }

    public ClLibro(int id, String titulo, String isbn, Date añoL, int numeroPaginas, int disponible, ClEditorial editorial, ClCategoria categoria, String imagen) {
        this.id = id;
        this.titulo = titulo;
        this.isbn = isbn;
        this.añoL = añoL;
        this.numeroPaginas = numeroPaginas;
        this.disponible = disponible;
        this.editorial = editorial;
        this.categoria = categoria;
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Date getAñoL() {
        return añoL;
    }

    public void setAñoL(Date añoL) {
        this.añoL = añoL;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public ClEditorial getEditorial() {
        return editorial;
    }

    public void setEditorial(ClEditorial editorial) {
        this.editorial = editorial;
    }

    public ClCategoria getCategoria() {
        return categoria;
    }

    public void setCategoria(ClCategoria categoria) {
        this.categoria = categoria;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    
    

    
}
