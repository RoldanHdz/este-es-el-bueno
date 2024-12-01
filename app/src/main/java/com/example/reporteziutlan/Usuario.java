package com.example.reporteziutlan;

public class Usuario {
    private int userId;
    private String nombre;
    private String correo;
    private String contraseña;
    private String fotoPerfilUrl;  // URL de la foto de perfil
    private String fechaRegistro;  // Fecha como String
    private String estado;

    // Constructor
    public Usuario(int userId, String nombre, String correo, String contraseña, String fotoPerfilUrl, String fechaRegistro, String estado) {
        this.userId = userId;
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
        this.fotoPerfilUrl = fotoPerfilUrl;
        this.fechaRegistro = fechaRegistro;
        this.estado = estado;
    }

    // Getters y setters
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContraseña() { return contraseña; }
    public void setContraseña(String contraseña) { this.contraseña = contraseña; }

    public String getFotoPerfilUrl() { return fotoPerfilUrl; }
    public void setFotoPerfilUrl(String fotoPerfilUrl) { this.fotoPerfilUrl = fotoPerfilUrl; }

    public String getFechaRegistro() { return fechaRegistro; }
    public void setFechaRegistro(String fechaRegistro) { this.fechaRegistro = fechaRegistro; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}

