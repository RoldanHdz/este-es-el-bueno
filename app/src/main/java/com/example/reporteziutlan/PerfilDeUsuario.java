package com.example.reporteziutlan;

public class PerfilDeUsuario {
    private int perfilId;
    private int usuarioId;
    private int totalReportes;
    private int totalApoyos;

    // Constructor
    public PerfilDeUsuario(int perfilId, int usuarioId, int totalReportes, int totalApoyos) {
        this.perfilId = perfilId;
        this.usuarioId = usuarioId;
        this.totalReportes = totalReportes;
        this.totalApoyos = totalApoyos;
    }

    // Getters y setters
    public int getPerfilId() { return perfilId; }
    public void setPerfilId(int perfilId) { this.perfilId = perfilId; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public int getTotalReportes() { return totalReportes; }
    public void setTotalReportes(int totalReportes) { this.totalReportes = totalReportes; }

    public int getTotalApoyos() { return totalApoyos; }
    public void setTotalApoyos(int totalApoyos) { this.totalApoyos = totalApoyos; }
}
