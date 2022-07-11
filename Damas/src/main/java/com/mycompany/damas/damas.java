/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.damas;
 
/**
 * Damas.java
 *
 * Classe derivada de applet responsável pelas inicializações
 * necessárias para o programa correr (colocar a interface).
 *
 * @author Rui Manuel Santos Rodrigues Leite
 *
 * Original by: https://web.fe.up.pt/~eol/IA/DAMAS/RELATORIO/relatorio.html
 * Last modified: 26-april-2016 by J. Marcos B.
 */
 
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Event;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
//public class Damas extends Applet
public class damas extends JFrame {
 
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
 
    Tabuleiro t;
    Interface_tabuleiro i;
    int x, y;
    Button b1, b2, b3, b4, b5;
    JPanel p;
    int resposta = 0;
 
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    damas frame = new damas();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
 
    public damas() {
        setTitle("Jogo das Damas clássicas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setBounds(100, 100, 450, 300);
 
        // Tamanho da Janela Principal
        setSize(600, 600);
        // Aparecer no centro da tela
        setLocationRelativeTo(null);
 
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
 
        if (t == null)
            t = new Tabuleiro();
 
        getContentPane().setLayout(new BorderLayout());
 
        if (p == null)
            p = new JPanel();
        p.setBorder(BorderFactory.createTitledBorder(BorderFactory
                .createBevelBorder(BevelBorder.RAISED), "Painel",
                TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION, new Font(
                        "Arial", Font.BOLD, 12), new Color(0, 0, 0)));
        p.setLayout(new FlowLayout(FlowLayout.LEFT));
 
        if (b1 == null)
            b1 = new Button("Jogar c/ brancas");
        // b1.setToolTipText(&quot;Contato&quot;);
        b1.setCursor(new Cursor(Cursor.HAND_CURSOR));
 
        if (b2 == null)
            b2 = new Button("Jogar c/ pretas");
        b2.setCursor(new Cursor(Cursor.HAND_CURSOR));
 
        if (b3 == null)
            b3 = new Button("1");
        b3.setCursor(new Cursor(Cursor.HAND_CURSOR));
 
        if (b4 == null)
            b4 = new Button("2");
        b4.setCursor(new Cursor(Cursor.HAND_CURSOR));
 
        if (b5 == null)
            b5 = new Button("3");
        b5.setCursor(new Cursor(Cursor.HAND_CURSOR));
 
        p.add(b1);
        p.add(b2);
        p.add(b3);
        p.add(b4);
        p.add(b5);
 
        getContentPane().add("North", p);
 
        if (i == null)
            i = new Interface_tabuleiro(this, t, -1);
 
        i.acabou = 0;
        i.computador = 10;
        i.level = 5;
 
        getContentPane().add("Center", i);
 
    }
 
    public boolean action(Event evt, Object arg) {
 
        if ("1".equals(arg)) {
            i.level = 5;
            i.repaint();
            return true;
        }
 
        if ("2".equals(arg)) {
            i.level = 6;
            i.repaint();
            return true;
        }
 
        if ("3".equals(arg)) {
            i.level = 7;
            i.repaint();
            return true;
        }
 
        if ("Jogar c/ brancas".equals(arg))
            i.recomeca(1);
 
        else
            i.recomeca(-1);
 
        i.repaint();
 
        return true;
    }// Fim do metodo booleano
 
}
