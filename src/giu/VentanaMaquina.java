package giu;

import java.awt.Color;
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

public class VentanaMaquina extends JFrame{

	private static final long serialVersionUID = 1L;
	private JLabel titulo;
	private JLabel casilla,frutilla;
	private MaquinaView mv;
	//JLabel credito;
	
	
	public VentanaMaquina(MaquinaView mv) throws MaquinaExcepcion{
		this.mv = mv;
		iniciarComponentes();
		//Valores de la ventana maquina
		this.setTitle("Casino");
		ImageIcon imgMoneda = new ImageIcon(getClass().getResource("/img/coin-solid-24.png"));
		this.setIconImage(imgMoneda.getImage());
		this.setBounds(100,100,800,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);}
		
		
	
	private void iniciarComponentes() throws MaquinaExcepcion {
		
		JPanel panel = new JPanel();
		//JPanel panelCasillas = new JPanel();
		JPanel botonJugar = new JPanel();
		JPanel panelImagenes = new JPanel();
		panel.setLayout(new GridLayout(3,1));
;		//panelCasillas.setLayout (new GridLayout(1,mv.getCantCasillas(),20,20));
		panelImagenes.setLayout(new GridLayout(1,mv.getCantCasillas(),20,20));
		botonJugar.setLayout(new GridLayout(3,3));
		
	
		Color marron = new Color (153, 77, 19);
		botonJugar.setBackground(marron);
		panel.setBackground(marron);
		this.getContentPane().add(panel);
		
		JButton jugar = new JButton("Jugar");
		ManejoBotonesInterna m = new ManejoBotonesInterna(this);
		jugar.addActionListener(m);
		
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		botonJugar.add(jugar);
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		
		
		
		this.titulo = new JLabel("MAQUINA N° "+ Casino.getInstancia().getMaquinaView(1).getNroMaquina(), SwingConstants.CENTER);
		
		for (int i=0; i< mv.getCantCasillas();i++) {
		// VER EL PANEL DE LAS CASILLAS STRING, SON NECESARIAS?
			//this.casilla = new JLabel(mv.getCasillas().get(i).obtenerFruta(),SwingConstants.CENTER);
			//panelCasillas.add(casilla);
			
			String fruta = mv.getCasillas().get(i).obtenerFruta();
			if (fruta == "Frutilla") {
				ImageIcon frutilla = new ImageIcon(getClass().getResource("/img/frutilla.png"));
				panelImagenes.add(new JLabel(frutilla));
			}
			if (fruta == "Banana") {
				ImageIcon banana = new ImageIcon(getClass().getResource("/img/banana.png"));
				panelImagenes.add(new JLabel(banana));
			}
			if (fruta == "Manzana") {
				ImageIcon manzana = new ImageIcon(getClass().getResource("/img/manzana.png"));
				panelImagenes.add(new JLabel(manzana));
			}
			if (fruta == "Sandia") {
				ImageIcon sandia = new ImageIcon(getClass().getResource("/img/sandia.png"));
				panelImagenes.add(new JLabel(sandia));
			}
			if (fruta == "Uva") {
				ImageIcon uva = new ImageIcon(getClass().getResource("/img/uva.png"));
				panelImagenes.add(new JLabel(uva));
			}
			if (fruta == "Guinda") {
				ImageIcon guinda = new ImageIcon(getClass().getResource("/img/guinda.png"));
				panelImagenes.add(new JLabel(guinda));
			}
			
			
		}
		
		panel.add(titulo);
		panel.add(panelImagenes);
		panel.add(botonJugar);
		

	}
	
	
	class ManejoBotonesInterna implements ActionListener{

		private JFrame VentanaMaquina;
			
		public ManejoBotonesInterna(JFrame VentanaMaquina) {
				this.VentanaMaquina = VentanaMaquina;
			}
			
			@Override
			public void actionPerformed(ActionEvent jugar) {
				try {
					Casino.getInstancia().jugar(1);
				} catch (MaquinaExcepcion e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		
		}
}


