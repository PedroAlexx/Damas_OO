/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dama;

/**
 *
 * @author ice
 */
public class Casa {
    private boolean valida;
    private int linha;
    private int coluna;
    
    public Casa(int linha, int coluna) {
        if((linha%2==0 && coluna % 2 != 0) || (linha % 2 != 0 && coluna % 2 == 0)){
            valida = true;
        } else {
            valida = false;
        }
    }

    public boolean isValida() {
        return valida;
    }


    public int getLinha() {
        return linha;
    }

    public void setLinha(int linha) {
        this.linha = linha;
    }

    public int getColuna() {
        return coluna;
    }

    public void setColuna(int coluna) {
        this.coluna = coluna;
    }
    
}

