package com.example.reporteziutlan;

public class EntidadOficial {
    private int entidadId;
    private String nombre;
    private String categoria;
    private String urlOficial;  // URL oficial

    // Constructor
    public EntidadOficial(int entidadId, String nombre, String categoria, String urlOficial) {
        this.entidadId = entidadId;
        this.nombre = nombre;
        this.categoria = categoria;
        this.urlOficial = urlOficial;
    }

    // Getters y setters
    public int getEntidadId() { return entidadId; }
    public void setEntidadId(int entidadId) { this.entidadId = entidadId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public String getUrlOficial() { return urlOficial; }
    public void setUrlOficial(String urlOficial) { this.urlOficial = urlOficial; }
}

