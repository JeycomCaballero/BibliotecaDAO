package biblioteca.model;

public class ClRol {

    private int id;
    private String tipoRol;

    public ClRol() {
    }

    

    public ClRol(int id, String tipoRol) {
        this.id = id;
        this.tipoRol = tipoRol;
    
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoRol() {
        return tipoRol;
    }

    public void setTipoRol(String tipoRol) {
        this.tipoRol = tipoRol;
    }
    
}