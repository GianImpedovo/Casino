package giu;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controlador.Casino;
import excepciones.MaquinaExcepcion;

public class Ventana extends JFrame{
	
	private static final long serialVersionUID = -6710917183940403534L;
	private JLabel titulo;
	private JLabel m1, m2, m3;
	private JButton jugar, modificar;
	
	
	public Ventana() throws MaquinaExcepcion {

		// Valores de la ventana principal
		this.setTitle("Casino");
		ImageIcon imgMoneda = new ImageIcon(getClass().getResource("/img/coin-solid-24.png"));
		this.setIconImage(imgMoneda.getImage());
		this.setBounds(100,100,800,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Container con grilla
		Container c = this.getContentPane();
		c.setLayout(new GridLayout(3,3));
		
		// Valores de cada Label
		this.titulo = new JLabel("Casino", SwingConstants.CENTER);
		titulo.setFont(new Font("Serif", Font.PLAIN, 50));
		
		ImageIcon maquinaI = new ImageIcon(getClass().getResource("/img/maquina_200x200.png"));
		
		JPanel panel_1 = new JPanel(new GridLayout(3,1));
		JPanel panel_2 = new JPanel(new GridLayout(3,1));
		JPanel panel_3 = new JPanel(new GridLayout(3,1));
		
		
		
	}


}
