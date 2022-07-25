package com.mycompany.dama;

import javax.swing.JButton;

import java.util.*;
import java.awt.GridLayout;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Imagem_Fundo extends JPanel{

	private JLabel label;
	
	public void iniciar() {
		label = new JLabel();
	}
	
	public Imagem_Fundo() {
		iniciar();
		//47
	    JLabel imagen = new JLabel( );
        ImageIcon icono = new ImageIcon( "./imagenes/FondoInformacion.png" );
        imagen = new JLabel( "" );
        imagen.setIcon( icono );
        imagen.setBounds(100,100,100,520);
        add( imagen );

//		setLayout(new GridLayout(8, 0));
//		JPanel panel = new JPanel();
//		label.setText("                 nuevo juego                       ");
//		cambiarlabel('N');
	}
	
//	public void cambiarlabel(char color){
//		
//		if(color == 'R') {
//			label.setText("             Turno de Las Rojas               ");
//			remove(label);
//		}else if(color == 'N') {
//			label.setText("             Turno de Las Negras              ");
//			remove(label);
//		}
//		
//		add(label);
//	}
//	
}
