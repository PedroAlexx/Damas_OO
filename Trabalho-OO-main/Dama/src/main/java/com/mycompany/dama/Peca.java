package com.mycompany.dama;

import javax.swing.JButton;

import java.util.*;
import javax.swing.JOptionPane;

public class Peca extends JButton 
{

    private String cor = "N";
    private final int vermelhas = 1, pretas = 2, damaVermelha = 3, damaPreta = 4, vazia = 5;
    int x, y;
    boolean dama;
    boolean eliminada;
    List<Movimento> movimentos; // lista para armazenar possíveis movimentos
    int tabuleiro[][] = new int [8][8];
    
    String peca;
    
    public int     getX()     {return this.x;};
    public void    setX(int x) {this.x = this.x + x;};
    public int     getY()     {return this.y;};
    public void    setY(int y) {this.y = this.y + y;};
    public boolean getD()     {return dama;};
    	
	public String getColor() {
		return cor;
	}
	public int getVazia() {
		return vazia;
	}
	public int getVermelhas() {
		return vermelhas;
	}
	public int getPretas() {
		return pretas;
	}
	public int getDamaVermelha() {
		return damaVermelha;
	}
	public int getDamaPreta() {
		return damaPreta;
	}
    public void setCor(String cor){
        this.cor = cor;
    }
    public String getCor() {
        return cor;
}
    
    
    public void MovePeca(int DestinoLinha, int DestinoColuna)
    {
        if (validaPosicao(DestinoLinha, DestinoColuna) == true)
        {
            setX(DestinoLinha - getX()); 
            setY(DestinoColuna - getY());
            Movimento movimento = new Movimento(getX(),getY(),DestinoColuna,DestinoLinha);
            movimentos.add(movimento);
        }
        else
        {
            System.out.println("Posição inválida!!!!");
        }
    }
    
    private boolean validaPosicao(int x_des, int y_des)
    {
        if(getX() + x_des < 8 && getX() + x_des >= 0 && getY() + y_des < 8 && getY() + y_des >= 0)
        {
            if(x_des == getX()+1 && y_des == getY()+1 || x_des == getX()-1 && y_des == getY()+1)
                return true;
            
            else if(x_des == getX()+1 && y_des == getY()-1 || x_des == getX()-1 && y_des == getY()-1 && getD() == true)
                return true;
            
            else
                return false;
        }
        
        else
            return false;
    }
    	public boolean verificar(String cor) {
		int contadorN = 0, contadorR = 0;
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro[0].length; j++) {
				if (tabuleiro[i][j] == vermelhas || tabuleiro[i][j] == damaVermelha) {
					contadorR++;
				} else if (tabuleiro[i][j] == pretas || tabuleiro[i][j] == damaPreta) {
					contadorN++;
				}
			}
		}
		imprimir(contadorN, contadorR, cor);
		if (contadorN == 0 && contadorR > 0) {
			JOptionPane.showMessageDialog(null, "ganan las rojas");
			return true;
		} else if (contadorR == 0 && contadorN > 0) {
			JOptionPane.showMessageDialog(null, "ganan las negras");
			return true;
		}
		return false;
	}
    public void AlternaVez() {
		if(cor == "V") {
			cor = "P";
		}else {
			cor = "V";
		}
	}
    public void possivel_dama() {
            for (int j = 0; j < this.tabuleiro[0].length; j++) {
                    if (tabuleiro[0][getY()] == pretas) {
                            tabuleiro[0][j] = damaPreta;
                    } else if (tabuleiro[7][getX()] == vermelhas) {
                            tabuleiro[7][j] = damaVermelha;
                    }
		}
	}
        public void busca_peça(int i,int j) {
		if(tabuleiro[i][j] == pretas) {
			System.out.println("preta");
		}else if(tabuleiro[i][j] == vermelhas){
			System.out.println("vermelha");
		}
	}
        public void poe_fichas() {	
		for (int i = 0; i < tabuleiro.length; i++) {
			for (int j = 0; j < tabuleiro.length; j++) {
				if (j % 2 == 0) {
					tabuleiro[1][j] = vermelhas;
					tabuleiro[7][j] = pretas;
					tabuleiro[5][j] = vazia;
					tabuleiro[3][j] = vazia;
				} else {
					tabuleiro[0][j] = vermelhas;
					tabuleiro[6][j] = pretas;
					tabuleiro[2][j] = vazia;
					tabuleiro[4][j] = vazia;
				}
			}
		}
	}
        public void imprimir(int contadorN, int contadorR, String color) {
//		System.out.println();
//		for (int i = 0; i < tabla.length; i++) {
//			System.out.println("----------------------------------------");
//			for (int j = 0; j < tabla[0].length; j++) {
//				if (tabla[i][j] == rojas) {
//					System.out.print(" R  |");
//				} else if (tabla[i][j] == negras) {
//					System.out.print(" N  |");
//				} else if (tabla[i][j] == reinaR) {
//					System.out.print(" RR |");
//				} else if (tabla[i][j] == reinaN) {
//					System.out.print(" RN |");
//				} else if (tabla[i][j] == 5) {
//					System.out.print("----|");
//				} else if (i == 4 && j == 7) {
//					System.out.print("    | Negras:");
//				} else if (i == 5 && j == 7) {
//					System.out.print("    | Rojas:");
//				} else if (i == 3 && j == 7 && color == 'R') {
//					System.out.print("    | Rojas");
//				} else if (i == 3 && j == 7 && color == 'N') {
//					System.out.print("    | Negras");
//				} else {
//					System.out.print("    |");
//				}
//				if (i == 2 && j == 7) {
//					System.out.print(" Turno De:");
//				}
//			}
//
//			System.out.println();
//		}
	}

}
