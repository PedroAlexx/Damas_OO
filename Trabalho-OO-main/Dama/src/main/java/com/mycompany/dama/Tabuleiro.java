package com.mycompany.dama;


import javax.swing.JFrame;
/**
 *
 * @author ice
 */
public class Tabuleiro
{
    int dimensao = 8;
    
    
    private static final String Alfabeto = "0ABCDEFGH"; // util para as coordenadas
    
    
    Peca tabuleiro[][] = new Peca [dimensao][dimensao];
    
    public Tabuleiro() 
    {   
            

            // Todas as posições inicialmente começam com "-"

    }
    
    public void InicializaTabuleiro(Jogador A,Jogador B)
    {
        int A_pecaIni =  0,B_pecaIni = 0;
        
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {               
                    Casa casa = new Casa(i, j);
                    if(casa.isValida())
                    {
                        Peca peca = new Peca(i, j);
                    }                                
            }
        }
    }
    public void MostraTabuleiro(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print(tabuleiro[i][j].cor + " ");
            }
            System.out.println();
        }
        System.out.println("******************FIM DO TABULEIRO******************");
    }
        
}
