package giu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controlador.Casino;
import excepciones.MaquinaExcepcion;
import negocio.Casilla;
import negocio.Maquina;
import vista.CasillaView;
import vista.MaquinaView;

public class VentanaMaquina extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel titulo;
	private JLabel casilla;
	private MaquinaView mv;
	//JLabel credito;
	
	
	public VentanaMaquina(MaquinaView mv) throws MaquinaExcepcion{
		this.mv = mv;
		
		//Valores de la ventana maquina
		this.setTitle("Casino");
		ImageIcon imgMoneda = new ImageIcon(getClass().getResource("/img/coin-solid-24.png"));
		this.setIconImage(imgMoneda.getImage());
		this.setBounds(100,100,800,600);
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	
	
		
		JPanel panel = new JPanel();
		//JPanel panelCasillas = new JPanel();
		JPanel botonJugar = new JPanel();
		JPanel panelImagenes = new JPanel();
		panel.setLayout(new GridLayout(3,1));
;		//panelCasillas.setLayout (new GridLayout(1,mv.getCantCasillas(),20,20));
		panelImagenes.setLayout(new GridLayout(1,mv.getCantCasillas(),20,20));
		botonJugar.setLayout(new GridLayout(3,3));
		
	
		Color marron = new Color (153, 77, 19);
		Color oro = new Color (168, 139, 0);
		botonJugar.setBackground(marron);
		panel.setBackground(marron);
		panelImagenes.setBackground(oro);
		this.getContentPane().add(panel);
		
		//boton
		JButton jugar = new JButton("Jugar");
		jugar.setBackground(Color.GREEN);
		
		jugar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
			
		});
			
		
		
		
		
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		botonJugar.add(jugar);
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		
		
		
		this.titulo = new JLabel("MAQUINA N° "+ mv.getNroMaquina(), SwingConstants.CENTER);
		titulo.setFont(new Font("Serif",Font.BOLD,40));
		
		
		for (int i=0; i< mv.getCantCasillas();i++) {
		// VER EL PANEL DE LAS CASILLAS STRING, SON NECESARIAS?
			//this.casilla = new JLabel(mv.getCasillas().get(i).obtenerFruta(),SwingConstants.CENTER);
			//panelCasillas.add(casilla);
			
			String fruta = mv.getCasillas().get(i).getFruta();
			if (fruta == "Frutilla") {
				ImageIcon frutilla = new ImageIcon(getClass().getResource("/img/frutilla.jpg"));
				panelImagenes.add(new JLabel(frutilla));
			}
			if (fruta == "Banana") {
				ImageIcon banana = new ImageIcon(getClass().getResource("/img/banana.jpg"));
				panelImagenes.add(new JLabel(banana));
			}
			if (fruta == "Manzana") {
				ImageIcon manzana = new ImageIcon(getClass().getResource("/img/manzana.jpg"));
				panelImagenes.add(new JLabel(manzana));
			}
			if (fruta == "Sandia") {
				ImageIcon sandia = new ImageIcon(getClass().getResource("/img/sandia.jpg"));
				panelImagenes.add(new JLabel(sandia));
			}
			if (fruta == "Uva") {
				ImageIcon uva = new ImageIcon(getClass().getResource("/img/uva.jpg"));
				panelImagenes.add(new JLabel(uva));
			}
			if (fruta == "Guinda") {
				ImageIcon guinda = new ImageIcon(getClass().getResource("/img/guinda.jpg"));
				panelImagenes.add(new JLabel(guinda));
			}
			
			
		}
		
		panel.add(titulo);
		panel.add(panelImagenes);
		panel.add(botonJugar);
		

	}
	
	
	

		
}


