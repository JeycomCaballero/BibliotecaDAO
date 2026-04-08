package biblioteca.model;

import java.sql.Date;

public class ClMulta {

    private int idMulta;
    private double monto;
    private Date fechaGeneracion;
    private Date fechaPago;
    private int estado;
    private ClPrestamo idPrestamo;
    private int dias;

    public ClMulta() {
    }

    public ClMulta(int idMulta, double monto, Date fechaGeneracion, Date fechaPago, int estado, ClPrestamo idPrestamo) {
        this.idMulta = idMulta;
        this.monto = monto;
        this.fechaGeneracion = fechaGeneracion;
        this.fechaPago = fechaPago;
        this.estado = estado;
        this.idPrestamo = idPrestamo;
    }

    public int getIdMulta() {
        return idMulta;
    }

    public void setIdMulta(int idMulta) {
        this.idMulta = idMulta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(Date fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public ClPrestamo getIdPrestamo() {
        return idPrestamo;
    }

    public void setIdPrestamo(ClPrestamo idPrestamo) {
        this.idPrestamo = idPrestamo;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

}
