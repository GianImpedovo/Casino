package giu;


import java.awt.Container;
import java.awt.Font;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.Casino;
import excepciones.MaquinaExcepcion;


public class Ventana extends JFrame{
	
	private static final long serialVersionUID = -6710917183940403534L;
	private JLabel titulo, txtMaquina, txtOpciones, txtCredito, datosMaquina;
	private JTextField credito, montoPremio;
	private JButton aceptar, agregarPremio;
	private JComboBox<String>  opciones, nroMaquinas, nombresFrutas;
	private JPanel panelPrincipal, panelCabecera, panelAltaPremio;
	private Container c;
	private ArrayList<JComboBox<String>> listaDeOpciones;


	
	public Ventana() throws MaquinaExcepcion {

		// Valores de la ventana principal
		this.setTitle("Casino");
		ImageIcon imgMoneda = new ImageIcon(getClass().getResource("/img/coin-solid-24.png"));
		this.setIconImage(imgMoneda.getImage());
		this.setBounds(100,100,1000,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		configuracion();
		
	} 
	
	public void configuracion() throws MaquinaExcepcion {
		c = this.getContentPane();
		c.setLayout(null);

		listaDeOpciones = new ArrayList<JComboBox<String>>();
		inicializarPanelCabecera();
		inicializarPanelPrincipal();
		inicializarPanelAltaPremio();
		panelAltaPremio.setVisible(false);

		c.add(panelCabecera);
		c.add(panelPrincipal);
		c.add(panelAltaPremio);
		
	}
	
	public void inicializarPanelCabecera() {
		
		panelCabecera = new JPanel();
		panelCabecera.setLayout(new GridLayout(1,2));
		panelCabecera.setBounds(10,10,1000,200);
		
		// Valores de cada Label
		titulo = new JLabel("Casino", SwingConstants.CENTER);
		titulo.setFont(new Font("Serif", Font.PLAIN, 40));
		
		panelCabecera.add(titulo);
		panelCabecera.add(new JLabel());
		
		
	}
	
	public void inicializarPanelPrincipal() throws MaquinaExcepcion {
		
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(null);
		panelPrincipal.setBounds(0,250,1000,800);
		
		
		JPanel panel_Lbl = inicializaPanelDeLbl1();
		JPanel panel_Opciones = inicializaPanelDeOpciones();
		JPanel panel_Datos = inicializaPanelDeDatos();

		panelPrincipal.add(panel_Lbl);
		panelPrincipal.add(panel_Opciones);
		panelPrincipal.add(panel_Datos);
		
	}
	
	public JPanel inicializaPanelDeLbl1() {
		
		JPanel panelLbl = new JPanel();
		panelLbl.setLayout(null);
		panelLbl.setBounds(0,0,300,400);
		
		txtMaquina = new JLabel("Maquina", SwingConstants.CENTER);
		txtMaquina.setFont(new Font("Serif", Font.BOLD, 30));
		txtMaquina.setBounds(30,-20,200,100);
		
		txtOpciones = new JLabel("Opciones", SwingConstants.CENTER);
		txtOpciones.setFont(new Font("Serif", Font.BOLD, 30));
		txtOpciones.setBounds(30,60,200,100);
		
		txtCredito = new JLabel("Crédito", SwingConstants.CENTER);
		txtCredito.setFont(new Font("Serif", Font.BOLD, 30));
		txtCredito.setBounds(30,140,200,100);
		
		panelLbl.add(txtMaquina);
		panelLbl.add(txtOpciones);
		panelLbl.add(txtCredito);
		
		return panelLbl;
	}
	
	public JPanel inicializaPanelDeOpciones() throws MaquinaExcepcion {
		JPanel panel_Opciones = new JPanel();
		panel_Opciones.setLayout(null);
		panel_Opciones.setBounds(300,0,300,400);
		
        String[] nros = {Casino.getInstancia().getMaquinaView(1).obtenerNombre(),
				 Casino.getInstancia().getMaquinaView(2).obtenerNombre(),
			     Casino.getInstancia().getMaquinaView(3).obtenerNombre()};
        nroMaquinas = new JComboBox<>(nros);
        ((JLabel)nroMaquinas.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        nroMaquinas.setBounds(0,0,300,50);
		
        
        String[] listaOpciones = {"Jugar", "Dar Alta Premio", "Dar Baja Premio"};
        opciones = new JComboBox<>(listaOpciones);
        ((JLabel)opciones.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        opciones.setBounds(0,85,300,50);
        
        credito = new JTextField();
        credito.setBounds(0,170,300,50);
        
        aceptar = new JButton("Aceptar");
        aceptar.setBounds(0,300,300,50);
        
        panel_Opciones.add(nroMaquinas);
        panel_Opciones.add(opciones);
        panel_Opciones.add(credito);
        panel_Opciones.add(aceptar);


        
        ManejoBotonAceptar accionBtn = new ManejoBotonAceptar(this);
        aceptar.addActionListener(accionBtn);
        
        return panel_Opciones;
	}
	
	public JPanel inicializaPanelDeDatos() throws MaquinaExcepcion {
		JPanel panel_Datos = new JPanel();
		panel_Datos.setLayout(null);
		panel_Datos.setBounds(650,0,300,500);
		
        datosMaquina = new JLabel(Casino.getInstancia().getMaquinaView(1).toString(), SwingConstants.CENTER);
        datosMaquina.setFont(new Font("Serif", Font.BOLD, 25));
        datosMaquina.setBounds(30,-20,250,100);
        
        nroMaquinas.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String)nroMaquinas.getSelectedItem().toString();
				int id = Integer.parseInt(s);
				try {
					datosMaquina.setText(Casino.getInstancia().getMaquinaView(id).toString());
				} catch (MaquinaExcepcion e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
        	
        });
        
        ImageIcon maquinaI = new ImageIcon(getClass().getResource("/img/maquina_200x200.png"));
        JLabel imgMaquina = new JLabel(maquinaI);
        imgMaquina.setBounds(30,100,200,200);
        
        
        panel_Datos.add(datosMaquina);
        panel_Datos.add(imgMaquina);        

        
        return panel_Datos;
	}
	
	public void inicializarPanelAltaPremio() throws NumberFormatException, MaquinaExcepcion {
		
		if (panelAltaPremio != null ) {
			c.remove(panelAltaPremio);
			listaDeOpciones.removeAll(listaDeOpciones);
		}
			
		
		panelAltaPremio = new JPanel();
		panelAltaPremio.setLayout(null);
		panelAltaPremio.setBounds(0,250,1000,800);
		
		panelAltaPremio.removeAll();
		
        String[] opcionesFrutas = {"Banana", "Manzana", "Frutilla", "Sandia", "Uva", "Guinda"};
        nombresFrutas = new JComboBox<String>(opcionesFrutas);
        ((JLabel)nombresFrutas.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        nombresFrutas.setBounds(300,0,200,40);
        
        String maquinaElegida = (String) nroMaquinas.getSelectedItem();
        
        int cantCasillas = Casino.getInstancia().getMaquinaView(Integer.parseInt(maquinaElegida)).getCantCasillas();
        
        int y = 30;
        for ( int i = 0; i < cantCasillas; i++ ) {
        	nombresFrutas = new JComboBox<String>(opcionesFrutas);
        	nombresFrutas.setBounds(300,y,200,40);
        	panelAltaPremio.add(nombresFrutas);
        	listaDeOpciones.add(nombresFrutas);
        	y += 60;
        }
        
        JLabel tituloAltaPremio = new JLabel("Ingresar Frutas: ");
        tituloAltaPremio.setBounds(70,0,200,100);
        tituloAltaPremio.setFont(new Font("Serif", Font.BOLD, 20));
        panelAltaPremio.add(tituloAltaPremio);
        
        agregarPremio = new JButton("Agregar Premio");
        agregarPremio.setBounds(300,300,200,50);
        panelAltaPremio.add(agregarPremio);
        
        JLabel txtCredito = new JLabel("Monto Premio: ");
        txtCredito.setBounds(550,35,130,30);
        txtCredito.setFont(new Font("Serif", Font.BOLD, 16));
        panelAltaPremio.add(txtCredito);
        
        montoPremio = new JTextField();
        montoPremio.setBounds(680,35,200,30);
        panelAltaPremio.add(montoPremio);
        
        c.add(panelAltaPremio);
	}
	
	public void inicializarPanelBajaPremio() {
		
	}
	
	class ManejoBotonAceptar implements ActionListener {
		
		private JFrame ventana;
		private VentanaMaquina vm;
		
		public ManejoBotonAceptar(JFrame ventana) {
			this.ventana = ventana;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand() == "Aceptar") {
				String maquinaElegida = (String) nroMaquinas.getSelectedItem();
				String opcionElegida = (String)opciones.getSelectedItem();
				String creditoIngresado = (String)credito.getText();

				if (opcionElegida == "Jugar" && !creditoIngresado.isEmpty()) {
					//JOptionPane.showMessageDialog(ventana, "Ventana jugar");
					try {
						vm = new VentanaMaquina(Casino.getInstancia().getMaquinaView(Integer.parseInt(maquinaElegida)).getNroMaquina(), creditoIngresado);
					} catch (MaquinaExcepcion e1) {
						e1.printStackTrace();
					}
					vm.setVisible(true);
				} else if ( opcionElegida == "Jugar" && creditoIngresado.isEmpty() ) {
					JOptionPane.showMessageDialog(ventana, " No ingresaste nada de credito. ");
				}
				
				if ( opcionElegida == "Dar Alta Premio") {
					// Crear la ventana de alta premio
					panelPrincipal.setVisible(false);
					try {
						inicializarPanelAltaPremio();
						panelAltaPremio.setVisible(true);
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MaquinaExcepcion e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					

				}
				
				if ( opcionElegida == "Dar Baja Premio" ) {
					// Crear la ventana para baja premio
					JOptionPane.showMessageDialog(ventana, "Ventana baja premio");
					
				}

			}
			
		}
		
	}
}


