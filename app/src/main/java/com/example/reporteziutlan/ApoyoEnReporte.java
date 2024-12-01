package com.example.reporteziutlan;

public class ApoyoEnReporte {
    private int apoyoId;
    private int usuarioId;
    private int reporteId;
    private String fechaApoyo;  // Fecha como String

    // Constructor
    public ApoyoEnReporte(int apoyoId, int usuarioId, int reporteId, String fechaApoyo) {
        this.apoyoId = apoyoId;
        this.usuarioId = usuarioId;
        this.reporteId = reporteId;
        this.fechaApoyo = fechaApoyo;
    }

    // Getters y setters
    public int getApoyoId() { return apoyoId; }
    public void setApoyoId(int apoyoId) { this.apoyoId = apoyoId; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public int getReporteId() { return reporteId; }
    public void setReporteId(int reporteId) { this.reporteId = reporteId; }

    public String getFechaApoyo() { return fechaApoyo; }
    public void setFechaApoyo(String fechaApoyo) { this.fechaApoyo = fechaApoyo; }
}
