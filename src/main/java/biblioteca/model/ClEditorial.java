package biblioteca.model;

public class ClEditorial {
    
    int id;
    String nombre;
    String pais;
    String sitioWeb;

    public ClEditorial() {
    }

    public ClEditorial(int id, String nombre, String pais, String sitioWeb) {
        this.id = id;
        this.nombre = nombre;
        this.pais = pais;
        this.sitioWeb = sitioWeb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    
}