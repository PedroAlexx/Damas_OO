/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.damas;
 
/**
 * Tabuleiro.java
 *
 * Classe que contem a estrutura de um tabuleiro (matriz) métodos de inicialização,
 * procura de jogadas admissíveis (operadores de estado), método de avaliação,
 * Minimax, aplica (aplicar operador a tabuleiro).
 *
 * @author Rui Manuel Santos Rodrigues Leite
 *
 * Original by: https://web.fe.up.pt/~eol/IA/DAMAS/RELATORIO/relatorio.html
 * Last modified: 26-april-2016 by J. Marcos B.
 */
 
import java.util.*;
 
public class Tabuleiro {
 
    public int matriz[][];
    Lista_jogadas admissiveis;
 
    // Constroi um tabuleiro com a posicao inicial
    public Tabuleiro() {
 
        int i, j;
        admissiveis = new Lista_jogadas();
        matriz = new int[8][];
 
        for (i = 0; i < 8; i++)
 
            matriz[i] = new int[8];
 
        for (i = 0; i < 8; i++)
 
            for (j = 0; j < 8; j++)
                matriz[i][j] = 0;
 
        for (i = 0; i < 3; i++)
 
            for (j = 0; j < 8; j++) {
 
                if ((i + j) % 2 == 0)
                    matriz[i][j] = 1;
 
                if ((7 - i + j) % 2 == 0)
                    matriz[7 - i][j] = -1;
            }
    }
 
    /*********************************************/
    /*********************************************/
    /*********************************************/
 
    public boolean Lista_admissiveis(Tabuleiro t1, int jogador,
            Lista_jogadas lista_jogadas) {
 
        /* jogador = 1 V jogador = -1 */
        int i, j, dama;
        score best_score = new score(0);
        List <par> movimentos = new ArrayList <>();
        lista_jogadas.movimentos.removeAll(movimentos);
        lista_jogadas.instavel.removeAll(movimentos);
 
        for (i = 0; i < 8; i++)
            for (j = 0; j < 8; j++) {
 
                if (t1.matriz[i][j] * jogador <= 0)
                    continue; /*
                             * isto acontece quando são de sinais contrários os
                             * um é zero
                             */
 
                dama = Math.abs(t1.matriz[i][j]);
 
                if (dama == 1) {
 
                    movimentos.removeAll(movimentos);
 
                    explora_jogada_peca(jogador, i, j, t1, movimentos,
                            lista_jogadas, best_score, 0);
 
                }
 
                if (dama == 2) {
 
                    movimentos.removeAllElements();
 
                    explora_jogada_dama(i, j, t1, movimentos, lista_jogadas,
                            best_score, 0);
 
                }
 
            }
 
        return true;
 
    }
 
    public void explora_jogada_peca_movendo(int jogador, int x, int y,
            Tabuleiro t1, List<par> movimentos, Lista_jogadas lista_jogadas,
            score best_score, int score) {
 
        List<par> mov;
        int fl;
        fl = 0;
 
        if (p_move(x, y, 1, 1, t1) == true) {
 
            movimentos.removeAllElements();
            movimentos.add(new par(x, y));
            movimentos.add(new par(x + jogador, y + 1));
            mov = new Vector<par>();
            mov = (Vector) movimentos.clone();
            lista_jogadas.movimentos.add(mov);
 
            // coloca elemento que designa a estabilidade desta jogada
            // verifica se pode entrar em dama
            /* pretas */
            if (jogador == 1 && x + jogador == 6) {
 
                if (y + 1 < 8 && t1.matriz[7][y + 1] == 0)
                    fl = 1;
 
                if (y + 1 >= 0 && t1.matriz[7][y - 1] == 0)
                    fl = 1;
 
            } else if (jogador == 1 && x + jogador == 0) {
 
                if (y + 1 < 8 && t1.matriz[0][y + 1] == 0)
                    fl = 1;
 
                if (y + 1 >= 0 && t1.matriz[0][y - 1] == 0)
                    fl = 1;
 
            }
 
            if (fl == 1)
 
                lista_jogadas.instavel.add(new Boolean(true));
 
            else
 
                lista_jogadas.instavel.add(new Boolean(false));
 
            movimentos.removeAll(movimentos);
 
        }
 
        fl = 0;
 
        if (p_move(x, y, 1, -1, t1) == true) {
 
            movimentos.removeAllElements();
            movimentos.add(new par(x, y));
            movimentos.add(new par(x + jogador, y - 1));
            mov = new ArrayList <par>();
            mov = (Vector) movimentos.clone();
            lista_jogadas.movimentos.add(mov);
 
            // coloca elemento que designa a estabilidade desta jogada
            // verifica se pode entrar em dama
            /* pretas */
            if (jogador == 1 && x + jogador == 6) {
 
                if (y + 1 < 8 && t1.matriz[7][y + 1] == 0)
                    fl = 1;
 
                if (y + 1 <= 0 && t1.matriz[7][y - 1] == 0)
                    fl = 1;
 
            } else if (jogador == 1 && x + jogador == 0) {
 
                if (y + 1 < 8 && t1.matriz[0][y + 1] == 0)
                    fl = 1;
 
                if (y + 1 >= 0 && t1.matriz[0][y - 1] == 0)
                    fl = 1;
 
            }
 
            if (fl == 1)
 
                lista_jogadas.instavel.add(new Boolean(true));
 
            else
 
                lista_jogadas.instavel.add(new Boolean(false));
 
            movimentos.removeAllElements();
 
        }
 
    }
 
    public void explora_jogada_dama_movendo(int x, int y, Tabuleiro tabuleiro1,
            List<par> movimentos, Lista_jogadas lista_jogadas,
            score best_score, int score) {
 
        int ncasas, dirx, diry;
        int wx, wy;
        List<par> mov;
 
        for (dirx = -1; dirx <= 1; dirx += 2)
 
            for (diry = -1; diry <= 1; diry += 2)
 
                for (ncasas = 1;; ncasas++) {
 
                    wx = x + ncasas * dirx;
 
                    wy = y + ncasas * diry;
 
                    if (wx < 0 || wx > 7 || wy < 0 || wy > 7)
                        break;
 
                    if (tabuleiro1.matriz[wx][wy] != 0)
                        break;
 
                    movimentos.removeAllElements();
                    movimentos.add(new par(x, y));
                    movimentos.add(new par(wx, wy));
                    mov = new Vector <par>();
                    mov = (Vector) movimentos.clone();
                    lista_jogadas.movimentos.add(mov);
 
                    // coloca elemento que designa a estabilidade desta jogada
                    lista_jogadas.instavel.add(new Boolean(false));
                }
        movimentos.removeAllElements();
    }
 
    public void explora_jogada_peca(int jogador, int x, int y, Tabuleiro t1, List <par> movimentos, Lista_jogadas lista_jogadas, score best_score, int score) {
 
        explora_jogada_peca_comendo(jogador, x, y, t1, movimentos,
                lista_jogadas, best_score, score);
 
        if (best_score.valor == 0)
            explora_jogada_peca_movendo(jogador, x, y, t1, movimentos,
                    lista_jogadas, best_score, score);
 
    }
 
    public void explora_jogada_dama(int x, int y, Tabuleiro t1,
            List<par> movimentos, Lista_jogadas lista_jogadas, score best_score,
            int score)
 
    {
 
        explora_jogada_dama_comendo(0, x, y, t1, movimentos, lista_jogadas,
                best_score, 0);
 
        if (best_score.valor == 0)
            explora_jogada_dama_movendo(x, y, t1, movimentos, lista_jogadas,
                    best_score, score);
 
    }
 
    public void explora_jogada_peca_comendo(int jogador, int x, int y,
            Tabuleiro t1, List<par> movimentos, Lista_jogadas lista_jogadas,
            score best_score, int score){
 
        int nelem, dama, score_ant;
        Tabuleiro t2;
        List<par> mov;
        boolean moveu;
        t2 = new Tabuleiro();
        t1.copia(t2);
        score_ant = score;
        moveu = false;
 
        if (p_come(x, y, 1, 1, t1, t2) == true) {
 
            /* come dama ? */
            dama = (Math.abs(t1.matriz[x + jogador][y + 1]));
 
            if (dama == 1)
                score += 50;
 
            else
                score += 51;
 
            movimentos.add(new par(x, y));
            explora_jogada_peca_comendo(jogador, x + 2 * jogador, y + 2, t2,
                    movimentos, lista_jogadas, best_score, score);
            nelem = movimentos.size() - 1;
            movimentos.removeElementAt(nelem);
            score = score_ant;
            moveu = true;
        }
 
        t1.copia(t2);
 
        if (p_come(x, y, 1, -1, t1, t2) == true) {
 
            dama = (Math.abs(t1.matriz[x + jogador][y - 1]));
 
            if (dama == 1)
                score += 50;
 
            else
                score += 51;
 
            movimentos.add(new par(x, y));
            explora_jogada_peca_comendo(jogador, x + 2 * jogador, y - 2, t2,
                    movimentos, lista_jogadas, best_score, score);
            nelem = movimentos.size() - 1;
            movimentos.removeElementAt(nelem);
            score = score_ant;
            moveu = true;
        }
 
        if (moveu == true)
            return;
 
        if (score == 0)
            return;
 
        if (score < best_score.valor) {
 
            return;
 
        }
 
        /* O score manteve-se ou aumentou */
        movimentos.add(new par(x, y));
 
        if (score > best_score.valor) {
 
            best_score.valor = score;
            lista_jogadas.movimentos.removeAllElements();
            lista_jogadas.instavel.removeAllElements();
 
        }
 
        mov = new Vector<par>();
        mov = (Vector) movimentos.clone();
        lista_jogadas.movimentos.add(mov);
 
        // coloca elemento que designa a estabilidade desta jogada
        lista_jogadas.instavel.add(new Boolean(true));
        nelem = movimentos.size() - 1;
        movimentos.removeElementAt(nelem);
        return;
 
    }
 
    /**************************************************************************************/
 
    public void explora_jogada_dama_comendo(int proibido, int x, int y,
            Tabuleiro t1, List<par> movimentos, Lista_jogadas lista_jogadas,
            score best_score, int score){
 
        score val = new score(0);
        Tabuleiro t2;
        int nelem, dama, score_ant;
        int dirx, diry, ncasas, proi, score_parcial;
        List<par> mov;
        boolean moveu, inimigos;
        t2 = new Tabuleiro();
 
        // System.out.println(&quot;proibido=&quot;+proibido);
        // System.out.println(&quot;(x,y)=(&quot;+x+&quot;,&quot;+y+&quot;)&quot;);
 
        moveu = false;
 
        for (dirx = -1; dirx <= 1; dirx += 2)
 
            for (diry = -1; diry <= 1; diry += 2){
 
                if (proibido == 1 && dirx * diry < 0)
                    continue;
 
                if (proibido == 2 && dirx * diry > 0)
                    continue;
 
                if (x + 2 * dirx < 0 || x + 2 * dirx > 7 || y + 2 * diry < 0
                        || y + 2 * diry > 7)
                    continue;
 
                if ((t1.matriz[x + dirx][y + diry] * t1.matriz[x][y]) > 0)
                    continue; /* tem uma peça própria colada */
 
                score_parcial = 0;
                inimigos = false;
 
                /*
                 * if ( (t1.matriz[x+dirx][y+diry]*t1.matriz[x][y])&lt;0)
                 *
                 * {inimigos=true;
                 *
                 * if (Math.abs(t1.matriz[x+dirx][y+diry])==2) score_parcial=51;
                 *
                 * } else {inimigos=false;score_parcial=50;}
                 */
 
                t1.copia(t2);
                t2.matriz[x][y] = 0;
                t2.matriz[x + dirx][y + diry] = 0;
 
                for (ncasas = 1;; ncasas++){
 
                    if (x + ncasas * dirx < 0 || x + ncasas * dirx > 7
                            || y + ncasas * diry < 0 || y + ncasas * diry > 7)
                        break;
 
                    t2.matriz[x + ncasas * dirx][y + ncasas * diry] = 0;
 
                    if ((t1.matriz[x + ncasas * dirx][y + ncasas * diry] * t1.matriz[x][y]) > 0)
                        break; /* tem uma peça própria colada */
 
                    if (t1.matriz[x + ncasas * dirx][y + ncasas * diry]
                            * t1.matriz[x][y] < 0){
 
                        if (inimigos == true)
                            break;
 
                        inimigos = true;
 
                        if (Math.abs(t1.matriz[x + ncasas * dirx][y + ncasas
                                * diry]) == 2){
                            score_parcial += 51;
                        }else{
                            score_parcial += 50;
                        }
 
                        continue;
                    }
 
                    if (score_parcial == 0)
                        continue;
 
                    inimigos = false;
                    t2.matriz[x + ncasas * dirx][y + ncasas * diry] = t1.matriz[x][y];
                    movimentos.add(new par(x, y));
 
                    if (dirx * diry > 0)
                        proi = 2;
                    else
                        proi = 1;
 
                    explora_jogada_dama_comendo(proi, x + dirx * ncasas, y
                            + diry * ncasas, t2, movimentos, lista_jogadas,
                            best_score, score + score_parcial);
 
                    nelem = movimentos.size() - 1;
                    movimentos.remove(nelem);
                    moveu = true;
                }
            }
 
        if (moveu == true)
            return;
 
        if (score == 0)
            return;
 
        if (score < best_score.valor) {
 
            return;
 
        }
 
        /* O score manteve-se ou aumentou */
        movimentos.add(new par(x, y));
 
        if (score < best_score.valor) {
 
            best_score.valor = score;
            lista_jogadas.movimentos.
            lista_jogadas.instavel.removeAllElements();
        }
 
        mov = new ArrayList<par>();
        mov = (Vector<par>) movimentos.clone();
        lista_jogadas.movimentos.add(mov);
 
        // coloca elemento que designa a estabilidade desta jogada
        lista_jogadas.instavel.add(new Boolean(true));
        nelem = movimentos.size() - 1;
        movimentos.remove(nelem);
        return;
    }
 
    /*********************************************/
    /*********************************************/
 
    // Faz uma cópia do tabuleiro currente para o t2
    public void copia(Tabuleiro t2) {
 
        int i, j;
 
        for (i = 0; i < 8; i++)
 
            for (j = 0; j < 8; j++)
 
                t2.matriz[i][j] = this.matriz[i][j];
 
    }
 
    // Não tem score porque só pode ser 1
    boolean p_come(int x, int y, int dirx, int diry, Tabuleiro tabuleiro1,
            Tabuleiro tabuleiro2) {
 
        int wx, wy, positivo;
 
        positivo = tabuleiro1.matriz[x][y];
 
        if (positivo < 0) {
            dirx = -dirx;
        }
 
        wx = x + dirx + dirx;
        wy = y + diry + diry;
 
        if (wx > 7 || wx < 0 || wy > 7 || wy < 0)
            return false;
 
        if (tabuleiro1.matriz[wx][wy] != 0)
            return false;/* casa destino tem que estar vazia */
 
        if ((positivo * tabuleiro1.matriz[x + dirx][y + diry]) >= 0)
            return false; /* está vazia ou tem uma peça da mesma cor */
 
        tabuleiro2.matriz[wx][wy] = tabuleiro2.matriz[x][y];
        tabuleiro2.matriz[x + dirx][y + diry] = 0;
        tabuleiro2.matriz[x][y] = 0;
 
        if (positivo < 0 && wy == 7)
            tabuleiro2.matriz[wx][wy] *= 2;
 
        if (positivo < 0 && wy == 0)
            tabuleiro2.matriz[wx][wy] *= 2;
 
        return true;
 
    }
 
    /***************************************/
    /***************************************/
 
    boolean p_move(int x, int y, int dirx, int diry, Tabuleiro tabuleiro1) {
 
        int positivo, wx, wy;
 
        positivo = tabuleiro1.matriz[x][y];
 
        if (positivo < 0)
            dirx = -dirx;
 
        wx = x + dirx;
        wy = y + diry;
 
        if (wx > 7 || wx < 0 || wy > 7 || wy < 0)
            return false;
 
        if (tabuleiro1.matriz[wx][wy] != 0)
            return false;
 
        return true;
 
    }
 
    /***************************************/
 
    // Este método representa um operador de tomada de peças por parte de uma
    // Dama
    // Peca - coordenadas da Dama (Peca.x,Peca.y)
    // movim - direcção e amplitude do movimento
    // (movim.dir(movim.dir.x,movim.dir.y),movim.ncasas)
    // tabuleiro1 - tabuleiro dado
    // tabuleiro2 - tabuleiro resultado
    // score - score deste movimento (score.pecas,score.damas)
 
    /***************************************/
    /***************************************/
    /***************************************/
 
    boolean d_come(int x, int y, int dirx, int diry, int ncasas,
            Tabuleiro tabuleiro1, Tabuleiro tabuleiro2, score score) {
 
        int i, wx, wy, positivo, temp, seg;
 
        wx = x + ncasas * dirx;
 
        wy = y + ncasas * diry;
 
        if (wx > 7 || wx < 0 || wy > 7 || wy < 0)
            return false;
 
        if (tabuleiro1.matriz[wx][wy] != 0)
            return false;/* casa destino tem que estar vazia */
 
        // Na diagonal respectiva (nos limites referidos) não podem existir
        // peças do próprio jogador
        // Tem que existir pelo menos uma peça inimiga
        // Não podem existir duas peças inimigas coladas
        positivo = tabuleiro1.matriz[x][y];
 
        seg = 0;
 
        for (i = 1; i <= ncasas; i++){
 
            wx = x + i * dirx;
 
            wy = y + i * diry;
 
            if (wx > 7 || wx < 0 || wy > 7 || wy < 0)
                return false;
 
            temp = positivo * tabuleiro1.matriz[wx][wy];
 
            if (temp > 0)
                return false; /* peça da mesma cor */
 
            if (temp == 0) {
                seg = 0;
                continue;
            }
 
            tabuleiro2.matriz[wx][wy] = 0;
 
            if (seg == 1)
                return false;/* tem duas peças adversárias seguidas */
 
            seg = 1;
 
            /* acumula score */
            if (Math.abs(tabuleiro1.matriz[wx][wy]) > 1)
                score.valor += 51; /* é uma dama */
 
            else
                score.valor += 50;
 
        }
        /* fim do for */
        wx = x + ncasas * dirx;
        wy = y + ncasas * diry;
        tabuleiro2.matriz[wx][wy] = tabuleiro2.matriz[x][y];
        tabuleiro2.matriz[x][y] = 0;
        return true;
    }
 
    public void jogar_bem(Tabuleiro t1, int nivel, Tabuleiro t2, int jog) {
 
        Lista_jogadas candidatos;
 
        candidatos = new Lista_jogadas();
 
        float melhor = 0, valor, alfa = -20000, beta = +20000;
 
        int melhor_indice = 0, i;
 
        t1.Lista_admissiveis(t1, jog, candidatos);
 
        if (candidatos.movimentos.size() > 1) {
 
            for (i = 0; i < candidatos.movimentos.size(); i++) {
 
                t1.copia(t2);
 
                aplica((List<?>) candidatos.movimentos.get(i), t2);
 
                valor = minimizar(t2, alfa, beta, nivel - 1, jog);
 
                if (i == 0) {
                    melhor = valor;
                    melhor_indice = 0;
                }
 
                if (valor > melhor) {
                    melhor = valor;
                    melhor_indice = i;
                }
 
            } /* fim dos operadores */
 
            // System.out.println(&quot;Melhor=&quot;+melhor);
 
        }
 
        t1.copia(t2);
 
        aplica((List<?>) candidatos.movimentos.get(melhor_indice), t2);
 
    }
 
    public float minimizar(Tabuleiro t1, float alfa, float beta, int nivel,
            int jog) {
 
        Lista_jogadas candidatos;
 
        Tabuleiro t2 = new Tabuleiro();
 
        int i;
        float valor;
 
        candidatos = new Lista_jogadas();
 
        // if (nivel&lt;=0) System.out.println(&quot;nivel=&quot;+nivel);
 
        /*
         * if (nivel==0) {
         *
         * // verifica estabilidade Quiescence
         *
         * return t1.avalia(jog);
         *
         * }
         */
 
        t1.Lista_admissiveis(t1, -jog, candidatos);
 
        if (candidatos.movimentos.size() == 0)
            return 10000;
 
        for (i = 0; i < candidatos.movimentos.size(); i++) {
 
            // situação de estabilidade
 
            if (nivel <= 0
                    && ((Boolean) candidatos.instavel.get(i))
                            .booleanValue() == false)
                return t1.avalia(jog);
 
            t1.copia(t2);
 
            aplica((List<?>) candidatos.movimentos.get(i), t2);
 
            valor = maximizar(t2, alfa, beta, nivel - 1, jog);
 
            if (valor < beta)
                beta = valor;
 
            if (beta <= alfa)
                return alfa;
 
        } /* fim dos operadores */
 
        return beta;
 
    }
 
    public float maximizar(Tabuleiro t1, float alfa, float beta, int nivel,
            int jog) {
 
        Lista_jogadas candidatos;
 
        Tabuleiro t2 = new Tabuleiro();
 
        int i;
        float valor;
 
        candidatos = new Lista_jogadas();
 
        /*
         * if (nivel==0) {
         *
         * // verifica estabilidade 'Quiescence'
         *
         * return t1.avalia(jog);
         *
         * }
         */
 
        // if (nivel&lt;=0) System.out.println(&quot;nivel=&quot;+nivel);
 
        t1.Lista_admissiveis(t1, jog, candidatos);
 
        if (candidatos.movimentos.size() == 0)
            return -10000;
 
        for (i = 0; i < candidatos.movimentos.size(); i++) {
 
            // situação de estabilidade
 
            if (nivel <= 0
                    && ((Boolean) candidatos.instavel.get(i))
                            .booleanValue() == false)
                return t1.avalia(jog);
 
            t1.copia(t2);
 
            aplica((List<?>) candidatos.movimentos.get(i), t2);
 
            valor = minimizar(t2, alfa, beta, nivel - 1, jog);
 
            if (alfa < valor)
                alfa = valor;
 
            if (alfa >= beta)
                return beta;
 
        } /* fim dos operadores */
 
        return alfa;
 
    }
 
    /* função boa para as pretas */
 
    public float avalia(int jog) {
 
        int i, j, a = 0, b = 0, tot = 0, defa = 0, defb = 0;
 
        float r = 0;
 
        for (i = 0; i < 8; i++)
 
        {
 
            for (j = 0; j < 8; j++)
 
            {
 
                if ((i + j) % 2 == 1 || matriz[i][j] == 0)
                    continue;
 
                // casas de defesa
 
                if (i == 0 && matriz[i][j] == 1)
                    defa += 1;
 
                if (i == 7 && matriz[i][j] == -1)
                    defb += 1;
 
                if (i == 1 && (j == 3 || j == 5))
                    defa += 1;
 
                if (i == 2 && j == 4)
                    defa += 1;
 
                if (i == 6 && (j == 2 || j == 4))
                    defb += 1;
 
                if (i == 5 && j == 3)
                    defb += 1;
 
                if (matriz[i][j] == 1) {
                    tot += 1;
                    a += 1;
                }
 
                if (matriz[i][j] == 2) {
                    tot += 3;
                    a += 3;
                }
 
                if (matriz[i][j] == -1) {
                    tot += 1;
                    b += 1;
                }
 
                if (matriz[i][j] == -2) {
                    tot += 3;
                    b += 3;
                }
 
            }
 
        }
 
        r = (float) (jog * (6 * (a - b) + (defa - defb)))
                / (4 * tot + defa + defb);
 
        // System.out.println(&quot;defa=&quot;+defa+&quot; defb=&quot;+defb+&quot;a=&quot;+a+&quot; b=&quot;+b+&quot; avaliacao=&quot;+r);
 
        return r;
 
    }
 
    public void aplica(List<?> movimentos, Tabuleiro tabuleiro1) {
 
        int i, cx, cy, ix, iy;
        int x1, y1, x2, y2, guarda;
        // System.out.println(&quot;comprim=&quot;+movimentos.size());
 
        if (movimentos.size() == 0)
            return;
 
        x1 = ((par) movimentos.get(0)).x;
        y1 = ((par) movimentos.get(0)).y;
        guarda = tabuleiro1.matriz[x1][y1];
 
        for (i = 0; i < movimentos.size() - 1; i++) {
 
            x1 = ((par) movimentos.get(i)).x;
            y1 = ((par) movimentos.get(i)).y;
            // System.out.println(&quot;mv=&quot;+x1+&quot;,&quot;+y1);
            x2 = ((par) movimentos.get(i + 1)).x;
            y2 = ((par) movimentos.get(i + 1)).y;
 
            if (x2 > x1)
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
 
        /* promove peça se chegar ao fim */
 
        if (Math.abs(tabuleiro1.matriz[x2][y2]) == 2)
            return;
 
        if (guarda > 0 && x2 == 7)
            tabuleiro1.matriz[x2][y2] *= 2;
 
        if (guarda < 0 && x2 == 0)
            tabuleiro1.matriz[x2][y2] *= 2;
 
    }
 
}/* fim da classe */
