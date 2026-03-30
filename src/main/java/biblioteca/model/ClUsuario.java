package biblioteca.model;

public class ClUsuario {

    private int id;
    private int documento;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private int estado;
    private String contraseña;
    private ClRol rol;

    public ClUsuario() {
    }

    public ClUsuario(int id, int documento, String nombre, String apellido, String email, String telefono, int estado, String contraseña, ClRol rol) {
        this.id = id;
        this.documento = documento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.telefono = telefono;
        this.estado = estado;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public ClRol getRol() {
        return rol;
    }

    public void setRol(ClRol rol) {
        this.rol = rol;
    }

   
   
}
