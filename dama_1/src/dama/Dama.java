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
    private peca Casa[][] = new peca [7][7];
    
    
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
                    Casa[i][j] = 0;
                } else{
                    // insere a partir da segunda coluna
                }
            }
        }
        
    }
    public void MostraTabuleiro(){
        for(int i = 0; i <= 7; i++){
            for(int j = 0; j <= 7; j++){
                System.out.print(Casa[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("******************FIM DO TABULEIRO******************");
    }
    // Recupera a linha de uma coordenada
    private int getLinha(String coordenada) {
    	return Integer.valueOf(coordenada.substring(0, 1));
    }
    
    // Recupera o índice da coluna em uma coordenada
    private int getColuna(String coordenada) {
    	return ALFABETO.indexOf(coordenada.substring(1, 2));
    }
    
}
