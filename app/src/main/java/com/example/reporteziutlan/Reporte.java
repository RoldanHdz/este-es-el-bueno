package com.example.reporteziutlan;
public class Reporte {

    private int reporteId;
    private String nombreUsuario;
    private String fechaReporte;
    private double ubicacionLatitud;
    private double ubicacionLongitud;
    private String tipo;
    private String fotoReferenciaUrl;
    private String descripcion;
    private String fotoReporteUrl;
    private String estadoReporte;
    private String nombreEntidad;

    // Constructor
    public Reporte(int reporteId, String nombreUsuario, String fechaReporte, double ubicacionLatitud,
                   double ubicacionLongitud, String tipo, String descripcion, String fotoReporteUrl,
                   String fotoReferenciaUrl, String estadoReporte, String nombreEntidad) {
        this.reporteId = reporteId;
        this.nombreUsuario = nombreUsuario;
        this.fechaReporte = fechaReporte;
        this.ubicacionLatitud = ubicacionLatitud;
        this.ubicacionLongitud = ubicacionLongitud;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.fotoReporteUrl = fotoReporteUrl;
        this.fotoReferenciaUrl = fotoReferenciaUrl;
        this.estadoReporte = estadoReporte;
        this.nombreEntidad = nombreEntidad;
    }

    // Getters y setters
    public int getReporteId() {
        return reporteId;
    }

    public void setReporteId(int reporteId) {
        this.reporteId = reporteId;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(String fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public double getUbicacionLatitud() {
        return ubicacionLatitud;
    }

    public void setUbicacionLatitud(double ubicacionLatitud) {
        this.ubicacionLatitud = ubicacionLatitud;
    }

    public double getUbicacionLongitud() {
        return ubicacionLongitud;
    }

    public void setUbicacionLongitud(double ubicacionLongitud) {
        this.ubicacionLongitud = ubicacionLongitud;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFotoReporteUrl() {
        return fotoReporteUrl;
    }

    public void setFotoReporteUrl(String fotoReporteUrl) {
        this.fotoReporteUrl = fotoReporteUrl;
    }

    public String getFotoReferenciaUrl() {
        return fotoReferenciaUrl;
    }

    public void setFotoReferenciaUrl(String fotoReferenciaUrl) {
        this.fotoReferenciaUrl = fotoReferenciaUrl;
    }

    public String getEstadoReporte() {
        return estadoReporte;
    }

    public void setEstadoReporte(String estadoReporte) {
        this.estadoReporte = estadoReporte;
    }

    public String getNombreEntidad() {
        return nombreEntidad;
    }

    public void setNombreEntidad(String nombreEntidad) {
        this.nombreEntidad = nombreEntidad;
    }
}
