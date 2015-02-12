package com.example.usuario.quedapp.beans;

/**
 * Created by victor on 24/01/2015.
 */
public class Usuario {
    private int idUsuario;
    private String user;
    private String phone;
    private String email;

    public Usuario(int idUsuario, String user, String phone, String email) {
        super();
        this.idUsuario = idUsuario;
        this.user = user;
        this.phone = phone;
        this.email = email;
    }

    public Usuario() {
        super();
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
