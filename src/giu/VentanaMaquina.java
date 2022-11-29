package giu;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

import controlador.Casino;
import excepciones.MaquinaExcepcion;
import vista.ComprobanteView;
import vista.MaquinaView;

public class VentanaMaquina extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel titulo, creditoDisponible, msjPremio;
	private MaquinaView mv;
	private JButton jugar, salir;
	private JPanel panel, botonJugar, panelImagenes;
	private int idMaquina;
	private String creditoIngresado;
	//JLabel credito;
	
	public VentanaMaquina(int idMaquina, String creditoIngresado) throws MaquinaExcepcion{
		this.mv = Casino.getInstancia().getMaquinaView(idMaquina);
		this.idMaquina = idMaquina;
		this.creditoIngresado = creditoIngresado;
		//Valores de la ventana maquina
		this.setTitle("Casino");
		ImageIcon imgMoneda = new ImageIcon(getClass().getResource("/img/coin-solid-24.png"));
		this.setIconImage(imgMoneda.getImage());
		this.setBounds(100,100,800,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); 
		
		// Cierra la ventana manualmente 
		/*
		this.addWindowListener(new WindowAdapter() { 
			@Override public void windowClosing(WindowEvent e) { 
				//Este método se deshace de la ventana una vez que el usuario hace clic en el botón de cierre. 
				dispose();
		}}); 
		*/
			
		configuracion();

	}
	
	public void configuracion() {
		
		inicializarPanelDeBoton();
		inicializarPanelImagenes();
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(3,1));
		Color marron = new Color (153, 77, 19);
		panel.setBackground(marron);
		
		this.getContentPane().add(panel);

		this.titulo = new JLabel("MAQUINA N° "+ mv.getNroMaquina(), SwingConstants.CENTER);
		titulo.setFont(new Font("Serif",Font.BOLD,40));
		
		panel.add(titulo);
		panel.add(panelImagenes);
		panel.add(botonJugar);
	}

	public void inicializarPanelImagenes() {
		panelImagenes = new JPanel();
		panelImagenes.setLayout(new GridLayout(1,mv.getCantCasillas(),20,20));
		Color oro = new Color (168, 139, 0);
		panelImagenes.setBackground(oro);
	}
	
	public void inicializarPanelDeBoton() {
		
		botonJugar = new JPanel();
		botonJugar.setLayout(new GridLayout(3,3));
		
		salir = new JButton("Salir");
		salir.setBackground(Color.GREEN);
		
		jugar = new JButton("Jugar");
		jugar.setBackground(Color.GREEN);
		
		creditoDisponible = new JLabel("Credito : $" + creditoIngresado, SwingConstants.CENTER);
		msjPremio = new JLabel("Mensaje de premio", SwingConstants.CENTER);

		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		botonJugar.add(msjPremio);
		botonJugar.add(jugar);
		botonJugar.add(creditoDisponible);
		botonJugar.add(new JLabel());
		botonJugar.add(salir);
		botonJugar.add(new JLabel());
		
		salir.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand() == "Salir") {
					setVisible(false);
				}
			}
			
		});
		
		
		
		jugar.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand() == "Jugar") {
					try {
						jugadas();
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MaquinaExcepcion e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
			}
			
		});
	}
	
	
	public void jugadas() throws NumberFormatException, MaquinaExcepcion {
		if ( Casino.getInstancia().getMaquinaView(idMaquina).getSaldoJugador() != 0 ) {
			Casino.getInstancia().jugar(idMaquina);
		} else {
			Casino.getInstancia().jugar(idMaquina,
					Casino.getInstancia().generarTicket(Float.parseFloat(creditoIngresado)));
		}
		
		añadirCasillas();
		mv = Casino.getInstancia().getMaquinaView(idMaquina);
		String saldoJugador = Float.toString(mv.getSaldoJugador());
		creditoDisponible.setText(saldoJugador);
		
		obtenerResultadoMaquina();
		
	}
	
	public void obtenerResultadoMaquina() throws MaquinaExcepcion {
		boolean gano = Casino.getInstancia().getMaquinaView(idMaquina).obtenerGano();
		if ( gano )
			msjPremio.setText("Ultima Jugada: GANASTE");
		else {
			msjPremio.setText("Ultima Jugada: PERDISTE");
		}
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


