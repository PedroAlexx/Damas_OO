/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dama;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
/**
 *
 * @author ice
 */
import java.util.*;
import javax.swing.*;

public class Interface extends JFrame 
{
    private Jogador   jogador_A,jogador_B;
    Tabuleiro tab;    
    //JButton[] bt = new JButton[64];
    JPanel painel;

    int ORDEM = 8;
    
    public Interface()
    {
        this.painel = new JPanel();
        tab = new Tabuleiro();
        tab.InicializaTabuleiro( jogador_A, jogador_B);
        tab.MostraTabuleiro();
    }
    
    public void desenha(){
        
        painel.setLayout(new GridLayout(ORDEM, ORDEM));
        for (int i = 0; i < ORDEM; i++) {
            for (int j = 0; j < ORDEM; j++) {
                Peca peca = new Peca(i, j);
                peca.setX(i);
                peca.setY(j);
                
//                casa.addActionListener(new Jogar(campo));
                //peca.addMouseListener(new Jogar(tab, this));
                
                peca.setPreferredSize(new Dimension(50, 50));
                peca.setBorder(BorderFactory.createLineBorder(Color.black));
                painel.add(peca);
            }
        }
        
        this.add(painel);
        this.setVisible(true);
        this.setSize(500,500);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        this.repaint();
        this.pack();
    }
    
    
    
    
    public static void main(String[] args) 
    {
        new Interface();
        
    }
}
