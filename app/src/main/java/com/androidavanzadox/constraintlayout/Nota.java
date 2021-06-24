package com.androidavanzadox.constraintlayout;

public class Nota {

    private String titulo;
    private String contenido;
    private Boolean favorita;
    private int color;

    public Nota(String titulo, String contenido, Boolean favorita, int color) {
        this.titulo = titulo;
        this.contenido = contenido;
        this.favorita = favorita;
        this.color = color;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Boolean getFavorita() {
        return favorita;
    }

    public void setFavorita(Boolean favorita) {
        this.favorita = favorita;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
