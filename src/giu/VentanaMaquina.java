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
import vista.MaquinaView;

public class VentanaMaquina extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel titulo, creditoDisponible;
	private MaquinaView mv;
	private JButton jugar;
	private JPanel panel, botonJugar, panelImagenes;
	//JLabel credito;
	
	public VentanaMaquina(int idMaquina, String creditoIngresado) throws MaquinaExcepcion{
		this.mv = Casino.getInstancia().getMaquinaView(idMaquina);
		//Valores de la ventana maquina
		this.setTitle("Casino");
		ImageIcon imgMoneda = new ImageIcon(getClass().getResource("/img/coin-solid-24.png"));
		this.setIconImage(imgMoneda.getImage());
		this.setBounds(100,100,800,600);
		this.setLocationRelativeTo(null);
		//this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		panel = new JPanel();
		botonJugar = new JPanel();
		panelImagenes = new JPanel();
		panel.setLayout(new GridLayout(3,1));
		
		panelImagenes.setLayout(new GridLayout(1,mv.getCantCasillas(),20,20));
		botonJugar.setLayout(new GridLayout(3,3));
		
	
		Color marron = new Color (153, 77, 19);
		Color oro = new Color (168, 139, 0);
		botonJugar.setBackground(marron);
		panel.setBackground(marron);
		panelImagenes.setBackground(oro);
		this.getContentPane().add(panel);
		
		//boton
		jugar = new JButton("Jugar");
		jugar.setBackground(Color.GREEN);
		
		creditoDisponible = new JLabel("Credito : $" + creditoIngresado, SwingConstants.CENTER);
		
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		botonJugar.add(jugar);
		botonJugar.add(creditoDisponible);
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		
		this.titulo = new JLabel("MAQUINA N° "+ mv.getNroMaquina(), SwingConstants.CENTER);
		titulo.setFont(new Font("Serif",Font.BOLD,40));
		
		panel.add(titulo);
		panel.add(panelImagenes);
		panel.add(botonJugar);

		jugar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand() == "Jugar") {
					try {
						Casino.getInstancia().jugar(mv.getNroMaquina(), Casino.getInstancia().generarTicket(Float.parseFloat(creditoIngresado)));
						mv = Casino.getInstancia().getMaquinaView(idMaquina);
						String saldoJugador = Float.toString(mv.getSaldoJugador());
						creditoDisponible.setText(saldoJugador);
						añadirCasillas();
					} catch (MaquinaExcepcion e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
			
		});
			
		

	}
	
	public void añadirCasillas() {
		panelImagenes.removeAll();
		for (int i=0; i< mv.getCantCasillas();i++) {
				
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
		
		panelImagenes.updateUI();
		
		
	}
	

		
}


