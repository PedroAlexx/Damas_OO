/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dama;

/**
 *
 * @author ice
 */
public class peca {
    String tipo;
    peca peca;
    int x, y;
    int nova_posicao[][] = new int[7][7];
    public peca(String S, int a, int b){
        this.x = a;
        this.y = b;
        this.tipo = S;
        this.peca = new peca(S, x, y);
    }
    public String retornaTipoPeca(){
        return tipo;
    }
//    public peca MovePeca(int i, int j){
//       
//    }

    
}
