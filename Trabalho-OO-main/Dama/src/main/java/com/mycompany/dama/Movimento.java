/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dama;

/**
 *
 * @author ice
 */
public class Movimento 
{
    private int x_i,y_i,x_f,y_f;
    
    public void setXi(int i) {this.x_i = i;};
    public void setYi(int i) {this.y_i = i;};
    public void setXf(int i) {this.x_f = i;};
    public void setYf(int i) {this.y_f = i;};
    
    Movimento(int xA,int yA,int xB,int yB)
    {
        setXi(xA);
        setYi(yA);
        setXf(xB);
        setYf(yB);
    }
}
