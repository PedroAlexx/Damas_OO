/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.damas;

/**
 *
 * @author ice
 */
 
/**
 * Interface_tabuleiro.java
 *
 * Classe que contém os métodos de desenho do tabuleiro e indicações da jogada,
 * controla o fluxo do programa (ciclo do jogo), aceita as jogadas do utilizador,
 * dá o resultado do jogo.
 *
 * @author Rui Manuel Santos Rodrigues Leite
 *
 * Original by: https://web.fe.up.pt/~eol/IA/DAMAS/RELATORIO/relatorio.html
 * Last modified: 26-april-2016 by J. Marcos B.
 */
 
import java.awt.*;
import java.util.*;
 
// Esta classe destina-se a fazer a apresentação de um tabuleiro.
public class Interface_tabuleiro extends Canvas {
//public class Interface_tabuleiro extends JPanel {
    private static final long serialVersionUID = 1L;
 
    int level;
    // Damas controller;
    damas controller;
    Tabuleiro tab, tab_visual;
    int jogador, njog;
    ArrayList <par> escolha_jogada;
    Lista_jogadas admissiveis;
    int menor;
    int inter;
    int cursorx, cursory;
    int acabou; /*
                 * 0 - não acabou
                 *
                 * 1 - ganharam as pretas
                 *
                 * 2 - ganharam as brancas
                 */
 
    public int computador; /*
                             * -1 - o mesmo joga com as brancas
                             *
                             * 1 - o mesmo joga com as pretas
                             */
 
    public void recomeca(int jog) {
 
        njog = 1;
        jogador = -1;
        computador = jog;
        acabou = 0;
        tab = new Tabuleiro();
        tab_visual = new Tabuleiro();
        tab.copia(tab_visual);
        cursorx = -1;
        cursory = -1;
        inter = 1;
        admissiveis = new Lista_jogadas();
        tab.Lista_admissiveis(tab, jogador, admissiveis);
        escolha_jogada = new ArrayList<par>();
 
        repaint();
 
    }
 
    // public Interface_tabuleiro(Damas controller, Tabuleiro tab, int jogador)
    // {
    public Interface_tabuleiro(damas controller, Tabuleiro tab, int jogador) {
 
        super();
 
        int i, j;
        ArrayList<?> rui;
        // tabuleiro result=new tabuleiro();
        cursorx = -1;
        cursory = -1;
        njog = 1;
        inter = 1;
        this.controller = controller;
        this.tab = tab;
        this.jogador = jogador;
        tab_visual = new Tabuleiro();
        tab.copia(tab_visual);
        admissiveis = new Lista_jogadas();
        tab.Lista_admissiveis(tab, jogador, admissiveis);
        escolha_jogada = new ArrayList<par>();
    }
 
    public boolean mouseDown(Event evt, int x, int y) {
 
        ArrayList v1;
 
        if (computador == 10)
            return false;
 
        if (jogador == computador)
            return false;
 
        if (acabou > 0)
            return false;
 
        if (admissiveis.movimentos.size() == 0) {
 
            acabou = computador;
 
            repaint();
 
            return false;
 
        }
 
        int resul, wx, wy, m = menor / 9;
 
        wx = x / m;
        wy = y / m;
 
        if ((wx + wy) % 2 == 1)
            return true;
 
        if (wx > 7 || wy > 7 || wx < 0 || wy < 0)
            return true;
 
        if (escolha_jogada.size() == 0 && tab.matriz[wy][wx] * jogador <= 0)
            return false;
 
        escolha_jogada.add(new par(wy, wx));
 
        // 0 se não , 1 se sim incompleta, 2 se sim completa
        tab.copia(tab_visual);
 
        resul = verifica_jogada(escolha_jogada, tab_visual);
 
        if (resul == 0) {
            cursorx = -1;
            cursory = -1;
            escolha_jogada.removeAll(escolha_jogada);
            repaint();
            return false;
        }
 
        cursorx = wx;
 
        cursory = wy;
 
        if (resul == 1) {
            repaint();
            return false;
        }
 
        cursorx = -1;
 
        repaint();
 
        tab_visual.copia(tab);
 
        escolha_jogada.removeAllElements();
 
        jogador = -jogador;
 
        njog++;
 
        repaint();
 
        return true;
 
    }
 
    public int verifica_jogada(ArrayList<par> escolha, Tabuleiro tabuleiro1) {
 
        int ex, ey, vx, vy, i, j;
 
        ArrayList<?> v1;
 
        if (escolha.size() == 1) {
 
            // só indicou a peça a mover
            for (i = 0; i < admissiveis.movimentos.size(); i++) {
 
                v1 = (ArrayList<?>)admissiveis.movimentos.get(i);
 
                ex = ((par) escolha.get(0)).x;
 
                ey = ((par) escolha.get(0)).y;
 
                vx = ((par) v1.get(0)).x;
 
                vy = ((par) v1.get(0)).y;
 
                if (ex == vx && ey == vy)
                    return 1;
 
            }
 
            return 0;
        }
 
        for (i = 0; i < admissiveis.movimentos.size(); i++) {
 
            v1 = (ArrayList<?>) admissiveis.movimentos.get(i);
 
            if (v1.size() < escolha.size())
                continue;
 
            for (j = 0; j < escolha.size(); j++) {
 
                ex = ((par) escolha.get(j)).x;
 
                ey = ((par) escolha.get(j)).y;
 
                vx = ((par) v1.get(j)).x;
 
                vy = ((par) v1.get(j)).y;
 
                if (ex != vx || ey != vy)
                    break;
 
            }
 
            if (j == escolha.size()) {
 
                aplica(escolha, tabuleiro1);
 
                if (v1.size() == escolha.size())
                    return 2;
 
                return 1;
 
            }
 
        } /* fim do for */
 
        return 0;
 
    }
 
    public void aplica(ArrayList<par> movimentos, Tabuleiro tabuleiro1) {
 
        int i, cx, cy, ix, iy;
 
        int x1, y1, x2, y2, guarda;
 
        if (movimentos.size() == 0)
            return;
 
        x1 = ((par) movimentos.get(0)).x;
 
        y1 = ((par) movimentos.get(0)).y;
 
        guarda = tabuleiro1.matriz[x1][y1];
 
        for (i = 0; i < movimentos.size() - 1; i++) {
 
            x1 = ((par) movimentos.get(i)).x;
 
            y1 = ((par) movimentos.get(i)).y;
 
            x2 = ((par) movimentos.get(i + 1)).x;
 
            y2 = ((par) movimentos.get(i + 1)).y;
 
            if (x2 < x1)
                ix = 1;
            else
                ix = -1;
 
            if (y2 > y1)
                iy = 1;
            else
                iy = -1;
 
            for (cx = x1, cy = y1; cx != x2; cx += ix, cy += iy)
 
                tabuleiro1.matriz[cx][cy] = 0;
 
            tabuleiro1.matriz[x2][y2] = guarda;
 
        }
 
        x2 = ((par) movimentos.get(movimentos.size() - 1)).x;
 
        y2 = ((par) movimentos.get(movimentos.size() - 1)).y;
 
        // promove peça se chegar ao fim
        if (Math.abs(tabuleiro1.matriz[x2][y2]) == 2)
            return;
 
        if (guarda > 0 && x2 == 7)
            tabuleiro1.matriz[x2][y2] *= 2;
 
        if (guarda < 0 && x2 == 0)
            tabuleiro1.matriz[x2][y2] *= 2;
 
    }
 
     public void paint(Graphics g) {
    //public void paintComponent(Graphics g) {
        //super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        //g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
            //    RenderingHints.VALUE_ANTIALIAS_ON);
 
        int i, j, m;
 
        if (computador == 10)
            return;
 
        // The method size() from the type Component is deprecated
        // if (size().width &lt; size().height)
        if (this.getSize().width < this.getSize().height)
            // The method size() from the type Component is deprecated
            // menor = size().width;
            menor = this.getSize().width;
        else
            // The method size() from the type Component is deprecated
            // menor = size().height;
            menor = this.getSize().height;
 
        m = menor / 9;
 
        if (acabou != 0) {
 
            g2d.setColor(Color.white);
 
            // The method size() from the type Component is deprecated
            // g.fillRect(0, 0, size().width, size().height);
            g2d.fillRect(0, 0, this.getSize().width, this.getSize().height);
 
            g2d.setColor(Color.black);
 
            if (acabou == 1)
 
                if (computador == 1) {
                    g2d.setFont(new Font("Arial", Font.BOLD, 48));
                    g2d.drawString("Ganhei o Jogo!!!! :-)", 4 * m, 4 * m);
 
                } else
                    g2d.drawString("Perdi o Jogo!!!! :-(", 4 * m, 4 * m);
 
            if (acabou == -1)
 
                if (computador == -1) {
                    g2d.setFont(new Font("Arial", Font.BOLD, 48));
                    g2d.drawString("Ganhei o Jogo!!!! :-)", 4 * m, 4 * m);
 
                } else
                    g2d.drawString("Perdi o Jogo!!!! :-(", 4 * m, 4 * m);
 
            computador = 10;
 
            return;
 
        }
 
        for (i = 0; i < 8; i++)
 
            for (j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0) {
                    // Desenha as casas da Dama
 
                    // Espessura da Linha.
                    g2d.setStroke(new BasicStroke(1.0f));
 
                    // Cor das casas
                    g2d.setColor(Color.gray);
                    g2d.fillRect(i * m, j * m, m + 1, m + 1);
                } else {
                    g2d.setColor(Color.gray);
                    g2d.drawRect(i * m, j * m, m, m);
                }
 
                if (tab_visual.matriz[j][i] == 1)
                    drawPeca(i, j, 1, g2d);
 
                if (tab_visual.matriz[j][i] == -1)
                    drawPeca(i, j, 0, g2d);
 
                if (tab_visual.matriz[j][i] == 2)
                    drawDama(i, j, 1, g2d);
 
                if (tab_visual.matriz[j][i] == -2)
                    drawDama(i, j, 0, g2d);
 
            }
 
        drawPeca(2, 8, (jogador > 0) ? 1 : 0, g2d);
 
        g2d.setColor(Color.blue);
        g2d.drawString("Jogada: " + njog + "Nível= " + (level - 4), 5 * m, 9 * m);
 
        g2d.setColor(Color.black);
 
        // Desenha um X vermelho em cima da peça da Dama
        if (cursorx >= 0 && cursory >= 0) {
 
            int a, b;
            // Define o tipo de cursor Mão
            this.setCursor(new Cursor(Cursor.HAND_CURSOR));
            g2d.setColor(Color.red);
            a = cursorx * m;
            b = cursory * m;
            g2d.drawLine(a + m / 3, b + m / 3, a + 2 * m / 3, b + 2 * m / 3);
            g2d.drawLine(a + 2 * m / 3, b + m / 3, a + m / 3, b + 2 * m / 3);
            g2d.setColor(Color.black);
 
        }
 
        if (jogador == computador) {
 
            tab.Lista_admissiveis(tab, jogador, admissiveis);
 
            if (admissiveis.movimentos.size() == 0) {
 
                acabou = -computador;
                repaint();
 
                return;
 
            }
 
            tab.jogar_bem(tab, level, tab_visual, jogador);
            tab_visual.copia(tab);
            njog++;
            jogador = -jogador;
            tab.Lista_admissiveis(tab, jogador, admissiveis);
 
            repaint();
 
        }
 
    }
 
    public void drawPeca(int x, int y, int cor, Graphics g2d) {
 
        // cor = 0 branca; cor = 1 preta
        int pe, pe1x, pe1y;
        int m = menor / 9;
        pe = (8 * m) / 10;
        pe1x = pe;
        pe1y = (60 * pe) / 100;
 
        if (cor == 0)
            g2d.setColor(Color.white);
        else
            g2d.setColor(Color.black);
 
        g2d.fillOval(x * m + m / 2 - pe1x / 2, y * m + m / 2 - pe1y / 2, pe1x,
                pe1y);
 
        if (cor == 0)
            g2d.setColor(Color.black);
        else
            g2d.setColor(Color.white);
 
        // g.drawOval(x*menor+menor/2-pe1x/2,y*menor+menor/2-pe1y/2,pe1x,pe1y);
        g2d.drawArc(x * m + m / 2 - pe1x / 2, y * m + m / 2 - pe1y / 2, pe1x,
                pe1y, 0, -180);
 
        g2d.drawArc(x * m + m / 2 - pe1x / 2, y * m + m / 2 - pe1y / 2 + 1,
                pe1x, pe1y, 0, -180);
 
    }
 
    public void drawDama(int x, int y, int cor, Graphics g2d) {
 
        // cor=0 branca; cor=1 preta
        int pe, pe1x, pe1y;
        int m = menor / 9;
        pe = (8 * m) / 10;
        pe1x = pe;
        pe1y = (60 * pe) / 100;
        pe = pe / 5;
 
        if (cor == 0)
            g2d.setColor(Color.white);
        else
            g2d.setColor(Color.black);
 
        g2d.fillOval(x * m + m / 2 - pe1x / 2, pe + y * m + m / 2 - pe1y / 2,
                pe1x, pe1y);
 
        if (cor == 0)
            g2d.setColor(Color.black);
        else
            g2d.setColor(Color.white);
 
        // g.drawOval(x*m+m/2-pe1x/2,pe+y*m+m/2-pe1y/2,pe1x,pe1y);
        g2d.drawArc(x * m + m / 2 - pe1x / 2, pe + y * m + m / 2 - pe1y / 2,
                pe1x, pe1y, 0, -180);
 
        g2d.drawArc(x * m + m / 2 - pe1x / 2,
                pe + y * m + m / 2 - pe1y / 2 + 1, pe1x, pe1y, 0, -180);
 
        if (cor == 0)
            g2d.setColor(Color.white);
        else
            g2d.setColor(Color.black);
 
        g2d.fillOval(x * m + m / 2 - pe1x / 2, y * m + m / 2 - pe1y / 2, pe1x,
                pe1y);
 
        if (cor == 0)
            g2d.setColor(Color.black);
        else
            g2d.setColor(Color.white);
 
        // g.drawOval(x*m+m/2-pe1x/2,y*m+m/2-pe1y/2,pe1x,pe1y);
        g2d.drawArc(x * m + m / 2 - pe1x / 2, y * m + m / 2 - pe1y / 2, pe1x,
                pe1y, 0, -180);
 
        g2d.drawArc(x * m + m / 2 - pe1x / 2, y * m + m / 2 - pe1y / 2 + 1,
                pe1x, pe1y, 0, -180);
 
    }
 
}
