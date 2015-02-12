package com.example.usuario.quedapp;

public class Item {


    private String title;
    private String evento;


    public Item() {
        super();
    }

    public Item(String title, String evento) {
        super();

        this.title = title;
        this.evento = evento;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }



}