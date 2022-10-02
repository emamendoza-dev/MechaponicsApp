package com.upiita.mechaponicsapp;

import java.io.Serializable;

public class ListElement implements Serializable {
    // Elementos a extraer de la base de datos en Firebase
    public String nodo;
    public String variable1;
    public String variable2;
    public String variable3;
    public String variable4;
    public String variable5;
    public String variable6;
    public String variable7;
    public String variable8;
    public String estado;

    // Constructor de los elementos a extraer de la base de datos
    public ListElement(String nodo, String variable1,
                       String variable2, String variable3, String variable4,
                       String variable5, String variable6, String variable7,
                       String variable8, String estado) {
        this.nodo = nodo;
        this.variable1 = variable1;
        this.variable2 = variable2;
        this.variable3 = variable3;
        this.variable4 = variable4;
        this.variable5 = variable5;
        this.variable6 = variable6;
        this.variable7 = variable7;
        this.variable8 = variable8;
        this.estado = estado;
    }

    // Gets y Sets de los elementos a extraer de la base de datos
    public String getNodo() {
        return nodo;
    }

    public void setNodo(String nodo) {
        this.nodo = nodo;
    }

    public String getVariable1() {
        return variable1;
    }

    public void setVariable1(String variable1) {
        this.variable1 = variable1;
    }

    public String getVariable2() {
        return variable2;
    }

    public void setVariable2(String variable2) {
        this.variable2 = variable2;
    }

    public String getVariable3() {
        return variable3;
    }

    public void setVariable3(String variable3) {
        this.variable3 = variable3;
    }

    public String getVariable4() {
        return variable4;
    }

    public void setVariable4(String variable4) {
        this.variable4 = variable4;
    }

    public String getVariable5() {
        return variable5;
    }

    public void setVariable5(String variable5) {
        this.variable5 = variable5;
    }

    public String getVariable6() {
        return variable6;
    }

    public void setVariable6(String variable6) {
        this.variable6 = variable6;
    }

    public String getVariable7() {
        return variable7;
    }

    public void setVariable7(String variable7) {
        this.variable7 = variable7;
    }

    public String getVariable8() {
        return variable8;
    }

    public void setVariable8(String variable8) {
        this.variable8 = variable8;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
