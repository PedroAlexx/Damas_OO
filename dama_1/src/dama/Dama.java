/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package dama;

/**
 *
 * @author ice
 */
public class Dama {

    /**
     * @param args the command line arguments
     */
    private int tabuleiro[][] = new int [7][7];
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
    }

    public void CriaTabuleiro(){
        
        for(int i = 0; i < 3; i++){
            for(int j = 0; j <= 7; j++){
                if(i % 2 == 0){ // se a linha for par, insere a partir da primeira posição
                    
                }
                else{
                    // insere a partir da segunda coluna
                }
            }
        }
        for(int i = 5; i <= 7; i++){
            for(int j = 0; j <= 7; j++){
                if(i % 2 == 0){  // // se a linha for par, insere a partir da primeira posição
                    tabuleiro[i][j] = 0;
                } else{
                    // insere a partir da segunda coluna
                }
            }
        }
        
    }
    public void MostraTabuleiro(){
        for(int i = 0; i <= 7; i++){
            for(int j = 0; j <= 7; j++){
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("******************FIM DO TABULEIRO******************");
    }
    
}
