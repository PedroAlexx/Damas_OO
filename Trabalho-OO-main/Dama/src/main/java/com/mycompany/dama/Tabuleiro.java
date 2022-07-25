package com.mycompany.dama;


import javax.swing.JFrame;
/**
 *
 * @author ice
 */
public class Tabuleiro
{
    int dimensao = 8;
    
       
    
    Peca tabuleiro[][] = new Peca [dimensao][dimensao];
    
    public Tabuleiro(int dimensao) 
    {                 
        this.dimensao = dimensao;
        for(int i = 0; i < dimensao; i++){
            for(int j = 0; j < dimensao; j++){
                tabuleiro[i][j] = new Peca();
                tabuleiro[i][j].setCor(" ");
            }
        }
    }
    
    public void MostraTabuleiro(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print(tabuleiro[i][j].peca + " ");
            }
            System.out.println();
        }
        System.out.println("******************FIM DO TABULEIRO******************");
    }
        
}
