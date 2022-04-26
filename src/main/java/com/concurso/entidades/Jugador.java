/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.concurso.entidades;

/**
 *
 * @author AndresFRivero
 */
public class Jugador {
    
    private int id;
    private String nombre;
    private String apellido;
    private int nivelAlcanzado;
    private float premio;
    private boolean retiroVoluntario;

    public Jugador(int id, String nombre, String apellido, int nivelAlcanzado, float premio, boolean retiroVoluntario) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.nivelAlcanzado = nivelAlcanzado;
        this.premio = premio;
        this.retiroVoluntario = retiroVoluntario;
    }
    
    public Jugador(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getNivelAlcanzado() {
        return nivelAlcanzado;
    }

    public void setNivelAlcanzado(int nivelAlcanzado) {
        this.nivelAlcanzado = nivelAlcanzado;
    }

    public float getPremio() {
        return premio;
    }

    public void setPremio(float premio) {
        this.premio = premio;
    }

    public boolean getRetiroVoluntario() {
        return retiroVoluntario;
    }

    public void setRetiroVoluntario(boolean retiroVoluntario) {
        this.retiroVoluntario = retiroVoluntario;
    }
}
