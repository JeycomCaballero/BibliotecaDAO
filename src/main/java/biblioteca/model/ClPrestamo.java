package biblioteca.model;

import java.sql.Date;

public class ClPrestamo {

    private int idPrestamo;
    private Date fechaPrestamo;
    private Date fechaDevolucionEsperada;
    private Date fechaDevolucionReal;
    int estado;
    private ClLibro libro;
    private ClUsuario usuario;

    public ClPrestamo() {
    }

    public ClPrestamo(int idPrestamo, Date fechaPrestamo, Date fechaDevolucionEsperada, Date fechaDevolucionReal, int estado, ClLibro libro, ClUsuario usuario) {
        this.idPrestamo = idPrestamo;
        this.fechaPrestamo = fechaPrestamo;
        this.fechaDevolucionEsperada = fechaDevolucionEsperada;
        this.fechaDevolucionReal = fechaDevolucionReal;
        this.estado = estado;
        this.libro = libro;
        this.usuario = usuario;
    }

    public int getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(int idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public Date getFechaPrestamo() {
        return fechaPrestamo;
    }

    public void setFechaPrestamo(Date fechaPrestamo) {
        this.fechaPrestamo = fechaPrestamo;
    }

    public Date getFechaDevolucionEsperada() {
        return fechaDevolucionEsperada;
    }

    public void setFechaDevolucionEsperada(Date fechaDevolucionEsperada) {
        this.fechaDevolucionEsperada = fechaDevolucionEsperada;
    }

    public Date getFechaDevolucionReal() {
        return fechaDevolucionReal;
    }

    public void setFechaDevolucionReal(Date fechaDevolucionReal) {
        this.fechaDevolucionReal = fechaDevolucionReal;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public ClLibro getLibro() {
        return libro;
    }

    public void setLibro(ClLibro libro) {
        this.libro = libro;
    }

    public ClUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(ClUsuario usuario) {
        this.usuario = usuario;
    }

    
}
