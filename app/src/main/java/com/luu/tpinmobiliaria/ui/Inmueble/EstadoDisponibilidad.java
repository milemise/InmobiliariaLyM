package com.luu.tpinmobiliaria.ui.Inmueble;

public class EstadoDisponibilidad {
    private String texto;
    private int icono;
    private int fondo;
    private boolean estadoBooleano;

    public EstadoDisponibilidad(String texto, int icono, int fondo, boolean estadoBooleano) {
        this.texto = texto;
        this.icono = icono;
        this.fondo = fondo;
        this.estadoBooleano = estadoBooleano;
    }

    public String getTexto() { return texto; }
    public int getIcono() { return icono; }
    public int getFondo() { return fondo; }
    public boolean isEstadoBooleano() { return estadoBooleano; }
}