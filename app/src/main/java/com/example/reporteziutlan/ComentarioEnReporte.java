
package com.example.reporteziutlan;

public class ComentarioEnReporte {
    private int comentarioId;
    private int usuarioId;
    private int reporteId;
    private String contenido;
    private String fechaComentario;  // Fecha como String

    // Constructor
    public ComentarioEnReporte(int comentarioId, int usuarioId, int reporteId, String contenido, String fechaComentario) {
        this.comentarioId = comentarioId;
        this.usuarioId = usuarioId;
        this.reporteId = reporteId;
        this.contenido = contenido;
        this.fechaComentario = fechaComentario;
    }

    // Getters y setters
    public int getComentarioId() { return comentarioId; }
    public void setComentarioId(int comentarioId) { this.comentarioId = comentarioId; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public int getReporteId() { return reporteId; }
    public void setReporteId(int reporteId) { this.reporteId = reporteId; }

    public String getContenido() { return contenido; }
    public void setContenido(String contenido) { this.contenido = contenido; }

    public String getFechaComentario() { return fechaComentario; }
    public void setFechaComentario(String fechaComentario) { this.fechaComentario = fechaComentario; }
}
