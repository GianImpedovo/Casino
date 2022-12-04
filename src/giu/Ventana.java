package giu;


import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.Casino;
import excepciones.MaquinaExcepcion;
import negocio.Casilla;
import negocio.Comprobante;
import negocio.Maquina;
import negocio.Premio;
import vista.CasillaView;
import vista.ComprobanteView;
import vista.MaquinaView;


public class Ventana extends JFrame{
	
	private static final long serialVersionUID = -6710917183940403534L;
	private JLabel titulo, tituloInicial, opcionJugar,txtMaquina, txtOpciones, txtCredito, datosMaquina, tituloMaquina, creditoDisponible, msjPremio, txtRecaudacion,txtCantCasillas, txtCosteJugada;
	private JTextField credito, montoPremio, recaudacion, cantCasillas, costeJugada;
	private JButton aceptar, agregarPremio, atras , eliminarPremio, jugar, btnCliente, btnAdministrador, btnAgregarMaquina, btnEliminarMaquina;
	private JComboBox<String>  opciones, nroMaquinas, nombresFrutas;
	private JPanel panelPrincipal, panelCabecera, panelMaquina, panelAltaPremio, panelBajaPremio, panelImagenes, panelCrearMaquina, panelInicio, panelEliminarMaquina;
	private Container c;
	private ArrayList<JComboBox<String>> listaDeOpciones ;
	private ArrayList<JCheckBox> listaCheckBox ;
	private JCheckBox premiosBaja, maquinaBaja;
	private ArrayList<String> combinacionCasillas;
	private boolean soyCliente;
	private ArrayList<JPanel> pila;


	
	public Ventana() throws MaquinaExcepcion {

		// Valores de la ventana principal
		
		this.setTitle("Casino");
		ImageIcon imgMoneda = new ImageIcon(getClass().getResource("/img/coin-solid-24.png"));
		this.setIconImage(imgMoneda.getImage());
		this.setBounds(100,100,1000,700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.soyCliente = false;
		this.pila = new ArrayList<JPanel>();
		
		this.combinacionCasillas = new ArrayList<String>();
		configuracion();
		
	} 
	
	public void configuracion() throws MaquinaExcepcion {
		c = this.getContentPane();
		c.setLayout(null);

		listaDeOpciones = new ArrayList<JComboBox<String>>();
		inicializarPanelCabecera();
		inicializarPanelPrincipal();
		inicializarPanelCrearMaquina();
		inicializarPanelAltaPremio();
		inicializarPanelBajaPremio();
		inicializarPanelMaquina();
		inicializarPanelInicio();
		inicializarPanelEliminarMaquina();
		
		
		panelCabecera.setVisible(false);
		panelPrincipal.setVisible(false);
		panelMaquina.setVisible(false);
		panelAltaPremio.setVisible(false);
		panelBajaPremio.setVisible(false);
		panelCrearMaquina.setVisible(false);
		panelEliminarMaquina.setVisible(false);
		panelInicio.setVisible(true);
		
		
		c.add(panelInicio);
		c.add(panelCabecera);
		c.add(panelCrearMaquina);
		c.add(panelPrincipal);
		c.add(panelMaquina);
		c.add(panelAltaPremio);
		c.add(panelBajaPremio);
		c.add(panelEliminarMaquina);
		
		
	}
	
	public void inicializarPanelInicio() {
		if (panelInicio != null ) {
			c.remove(panelInicio);
		}
		
		
		
		panelInicio = new JPanel();
		panelInicio.setLayout(null);
		panelInicio.setBounds(0,0,1000,800);
		
		tituloInicial = new JLabel("Elegi una opcion:");
		tituloInicial.setFont(new Font("Serif", Font.PLAIN, 40));
		tituloInicial.setBounds(350,50,300,50);
		
		btnCliente = new JButton("Cliente");
		btnCliente.setBounds(200,300,200,50);
        ManejoBotonAceptar accionBtn = new ManejoBotonAceptar(this);
        btnCliente.addActionListener(accionBtn);
		
		
		
		btnAdministrador = new JButton("Administrador");
		btnAdministrador.setBounds(600,300,200,50);
        ManejoBotonAceptar accionBtnAdmin = new ManejoBotonAceptar(this);
        btnAdministrador.addActionListener(accionBtnAdmin);
		
		panelInicio.add(tituloInicial);
		panelInicio.add(btnAdministrador);
		panelInicio.add(btnCliente);
		
		
	}
	
	public void inicializarPanelCabecera() {
		
		panelCabecera = new JPanel();
		panelCabecera.setLayout(null);
		panelCabecera.setBounds(10,10,1000,100);
		
		// Valores de cada Label
		titulo = new JLabel("Casino", SwingConstants.CENTER);
		titulo.setBounds(300,50,300,50);
		titulo.setFont(new Font("Serif", Font.PLAIN, 40));
		 
		
		atras = new JButton("Atras");
		atras.setBounds(0,0,200,50);
        ManejoBotonAceptar accionBtn = new ManejoBotonAceptar(this);
        atras.addActionListener(accionBtn);
        panelCabecera.add(atras);
		
		panelCabecera.add(titulo);
		panelCabecera.add(atras);
		panelCabecera.add(new JLabel());
		
		
	}
	
	public void inicializarPanelPrincipal() throws MaquinaExcepcion {
		
		if ( panelPrincipal != null ) {
			c.remove(panelPrincipal);
		}
		
		panelPrincipal = new JPanel();
		panelPrincipal.setLayout(null);
		panelPrincipal.setBounds(0,250,1000,800);
		
		
		JPanel panel_Lbl = inicializaPanelDeLbl1();
		JPanel panel_Opciones = inicializaPanelDeOpciones();
		JPanel panel_Datos = inicializaPanelDeDatos();

		panelPrincipal.add(panel_Lbl);
		panelPrincipal.add(panel_Opciones);
		panelPrincipal.add(panel_Datos);
		
		c.add(panelPrincipal);
		
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
		
		if ( soyCliente ) {
			panelLbl.add(txtCredito);
		}
		
		panelLbl.add(txtMaquina);
		panelLbl.add(txtOpciones);
		
		return panelLbl;
	}
	
	public JPanel inicializaPanelDeOpciones() throws MaquinaExcepcion {
		
		JPanel panel_Opciones = new JPanel();
		panel_Opciones.setLayout(null);
		panel_Opciones.setBounds(300,0,300,400);
		
		String[] nros = new String[Casino.getInstancia().getCantidadMaquinas()];
		System.out.println(Casino.getInstancia().getCantidadMaquinas());
		
		for(int i = 0; i < Casino.getInstancia().getCantidadMaquinas(); i++) {
			nros[i] = Casino.getInstancia().getMaquina(i).obtenerNombre();
		}
		
        nroMaquinas = new JComboBox<>(nros);
        
        ((JLabel)nroMaquinas.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        nroMaquinas.setBounds(0,0,300,50);
        
        credito = new JTextField();
        credito.setBounds(0,170,300,50);
        
        if ( soyCliente ) {
        	opcionJugar = new JLabel("Jugar");
        	opcionJugar.setBounds(0,85,300,50);
        	opcionJugar.setFont(new Font("Serif", Font.BOLD, 30));
        	panel_Opciones.add(opcionJugar);
            panel_Opciones.add(credito);
        } else {
        	String[] listaOpciones = {"Dar Alta Premio", "Dar Baja Premio", "Crear Maquina", "Eliminar Maquina"};
        	opciones = new JComboBox<>(listaOpciones);
        	panel_Opciones.add(opciones);
        }
        
        ((JLabel)opciones.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        opciones.setBounds(0,85,300,50);

        aceptar = new JButton("Aceptar");
        aceptar.setBounds(0,300,300,50);
       
        
        panel_Opciones.add(nroMaquinas);
        panel_Opciones.add(aceptar);


        
        ManejoBotonAceptar accionBtn = new ManejoBotonAceptar(this);
        aceptar.addActionListener(accionBtn);
        
        return panel_Opciones;
	}
	
	public JPanel inicializaPanelDeDatos() throws MaquinaExcepcion {
		String maquinaElegida = (String) nroMaquinas.getSelectedItem();
		JPanel panel_Datos = new JPanel();
		panel_Datos.setLayout(null);
		panel_Datos.setBounds(650,0,300,500);
		
        datosMaquina = new JLabel(Casino.getInstancia().getMaquinaView(Integer.parseInt(maquinaElegida)).toString(), SwingConstants.CENTER);
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
	
	public void inicializarPanelCrearMaquina() {
		if (panelCrearMaquina != null ) {
			c.remove(panelCrearMaquina);
		}
		
		
		panelCrearMaquina = new JPanel();
		panelCrearMaquina.setLayout(null);
		panelCrearMaquina.setBounds(0,250,1000,800);
		
		txtRecaudacion = new JLabel("Recaudacion : ");
		txtRecaudacion.setFont(new Font("Serif", Font.BOLD, 25));
		txtRecaudacion.setBounds(60,20,200,100);
		 
		txtCantCasillas = new JLabel("Cantidad Casillas : ");
		txtCantCasillas.setFont(new Font("Serif", Font.BOLD, 25));
		txtCantCasillas.setBounds(60,120,400,100);
		 
		txtCosteJugada = new JLabel("Coste Jugada : ");
		txtCosteJugada.setFont(new Font("Serif", Font.BOLD, 25));
		txtCosteJugada.setBounds(60,220,200,100);
		
		recaudacion = new JTextField();
		recaudacion.setFont(new Font("Serif", Font.BOLD, 25));
		recaudacion.setBounds(360,50,200,40);
		
		cantCasillas = new JTextField();
		cantCasillas.setFont(new Font("Serif", Font.BOLD, 25));
		cantCasillas.setBounds(360,150,200,40);
		
		costeJugada = new JTextField();
		costeJugada.setFont(new Font("Serif", Font.BOLD, 25));
		costeJugada.setBounds(360,250,200,40);
		
		btnAgregarMaquina = new JButton("Agregar Maquina");
		btnAgregarMaquina.setBounds(660,250,200,40);
        ManejoBotonAceptar accionBtn = new ManejoBotonAceptar(this);
        btnAgregarMaquina.addActionListener(accionBtn);
		
		panelCrearMaquina.add(txtRecaudacion);
		panelCrearMaquina.add(txtCantCasillas);
		panelCrearMaquina.add(txtCosteJugada);
		panelCrearMaquina.add(recaudacion);
		panelCrearMaquina.add(cantCasillas);
		panelCrearMaquina.add(costeJugada);
		panelCrearMaquina.add(btnAgregarMaquina);
		
		c.add(panelCrearMaquina);

	}
	
	public void inicializarPanelMaquina() throws MaquinaExcepcion{
		if (panelMaquina != null ) {
			c.remove(panelMaquina);
		}
		
		
		String maquinaElegida = (String) nroMaquinas.getSelectedItem();
		
		panelMaquina = new JPanel();
		panelMaquina.setLayout(new GridLayout(3,1));
		panelMaquina.setBounds(20,150,950,470);
		Color marron = new Color (153, 77, 19);
		panelMaquina.setBackground(marron);
		
		this.tituloMaquina = new JLabel("MAQUINA N° "+ Casino.getInstancia().getMaquinaView(Integer.parseInt(maquinaElegida)).getNroMaquina(), SwingConstants.CENTER);
		tituloMaquina.setFont(new Font("Serif",Font.BOLD,40));
		
		inicializarPanelImagenes();
		JPanel botonJugar = inicializarPanelBoton();
		
		panelMaquina.add(tituloMaquina);
		panelMaquina.add(panelImagenes);
		panelMaquina.add(botonJugar);
		
		c.add(panelMaquina);
	}
	
	
	public void inicializarPanelImagenes() throws MaquinaExcepcion {
		String maquinaElegida = (String) nroMaquinas.getSelectedItem();
		
		panelImagenes = new JPanel();
		panelImagenes.setLayout(new GridLayout(1,Casino.getInstancia().getMaquinaView(Integer.parseInt(maquinaElegida)).getCantCasillas(),20,20));
		Color oro = new Color (168, 139, 0);
		panelImagenes.setBackground(oro);
		
		for (int i=0; i<  Casino.getInstancia().getMaquinaView(Integer.parseInt(maquinaElegida)).getCantCasillas();i++) {
			ImageIcon frutilla = new ImageIcon(getClass().getResource("/img/frutilla.jpg"));
			panelImagenes.add(new JLabel(frutilla));
		}
		
		
	}
	
	
	public JPanel inicializarPanelBoton() {
		String creditoIngresado = (String)credito.getText();
		
		Color marron = new Color (153, 77, 19);
		JPanel botonJugar = new JPanel();
		botonJugar.setLayout(new GridLayout(3,3));
		botonJugar.setBackground(marron);
		
		jugar = new JButton("Jugar");
		jugar.setBackground(Color.GREEN);
		ManejoBotonAceptar accionBtn = new ManejoBotonAceptar(this);
		jugar.addActionListener(accionBtn);
		
		creditoDisponible = new JLabel("Credito Disponible : $" + creditoIngresado, SwingConstants.CENTER);
		msjPremio = new JLabel("Mensaje de premio", SwingConstants.CENTER);
		msjPremio.setFont(new Font("Serif", Font.BOLD, 30));

		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		botonJugar.add(msjPremio);
		botonJugar.add(jugar);
		botonJugar.add(creditoDisponible);
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		botonJugar.add(new JLabel());
		
		return botonJugar;
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
        
        JLabel tituloAltaPremio = new JLabel("Ingresar Fruta: ");
        tituloAltaPremio.setBounds(70,0,200,100);
        tituloAltaPremio.setFont(new Font("Serif", Font.BOLD, 20));
        panelAltaPremio.add(tituloAltaPremio);
        
        agregarPremio = new JButton("Agregar Premio");
        agregarPremio.setBounds(300,300,200,50);
        ManejoBotonAceptar accionBtn = new ManejoBotonAceptar(this);
        agregarPremio.addActionListener(accionBtn);
        panelAltaPremio.add(agregarPremio);
        
        JLabel txtCreditoTexto = new JLabel("Monto Premio: ");
        txtCreditoTexto.setBounds(550,35,130,30);
        txtCreditoTexto.setFont(new Font("Serif", Font.BOLD, 16));
        panelAltaPremio.add(txtCreditoTexto);
        
        montoPremio = new JTextField();
        montoPremio.setBounds(680,35,200,30);
        panelAltaPremio.add(montoPremio);
        
        c.add(panelAltaPremio);
	}
	
	
	public void crearCombinacionCasillaView() {
		if(combinacionCasillas != null )
			combinacionCasillas.clear();
		
		
		for (JComboBox<String> box: listaDeOpciones) {
			combinacionCasillas.add((String) box.getSelectedItem());
		}
	}
	

	public void inicializarPanelBajaPremio() throws MaquinaExcepcion {
		
		if (panelBajaPremio != null ) {
			c.remove(panelBajaPremio);
		}
		
		String maquinaElegida = (String) nroMaquinas.getSelectedItem();
		int cantPremios = Casino.getInstancia().getMaquinaView(Integer.parseInt(maquinaElegida)).getPremios().size();
		
		panelBajaPremio = new JPanel();
		panelBajaPremio.setLayout(null);
		panelBajaPremio.setBounds(0,250,1000,800);
     
		JLabel tituloBajaPremio = new JLabel("Premio a Eliminar: ");
        tituloBajaPremio.setBounds(70,0,200,100);
        tituloBajaPremio.setFont(new Font("Serif", Font.BOLD, 20));
        panelBajaPremio.add(tituloBajaPremio);
		
        
		int y = 30;
		listaCheckBox= new  ArrayList<JCheckBox>();
		
		for (int i=0;i<cantPremios;i++) {
			String infoPremio = Casino.getInstancia().getMaquinaView(Integer.parseInt(maquinaElegida)).getPremios().get(i).toString();	
			premiosBaja = new JCheckBox(infoPremio);
			listaCheckBox.add(premiosBaja);
			premiosBaja.setBounds(300,y,400,50);
			panelBajaPremio.add(premiosBaja);
			y += 60;	
		
		}	
		
		 eliminarPremio = new JButton("Eliminar Premio");
		 eliminarPremio.setBounds(700,100,200,50);
	     ManejoBotonAceptar accionBtn = new ManejoBotonAceptar(this);
	     eliminarPremio.addActionListener(accionBtn);
	     panelBajaPremio.add(eliminarPremio);
		
		c.add(panelBajaPremio);
		
	}
	
	public void inicializarPanelEliminarMaquina() throws MaquinaExcepcion {
		if (panelEliminarMaquina != null ) {
			c.remove(panelEliminarMaquina);
		}
		
		panelEliminarMaquina = new JPanel();
		panelEliminarMaquina.setLayout(null);
		panelEliminarMaquina.setBounds(0,250,1000,800);
		
		JLabel tituloBajaMaquina = new JLabel("Maquina a Eliminar: ");
		tituloBajaMaquina.setBounds(70,0,200,100);
		tituloBajaMaquina.setFont(new Font("Serif", Font.BOLD, 20));
		panelEliminarMaquina.add(tituloBajaMaquina);
		
		// Adaptar codigo : 
		String[] nros = new String[Casino.getInstancia().getCantidadMaquinas()];
		
		for(int i = 0; i < Casino.getInstancia().getCantidadMaquinas(); i++) {
			nros[i] = Casino.getInstancia().getMaquina(i).obtenerNombre();
		}    
		
        nroMaquinas = new JComboBox<>(nros);
        
        ((JLabel)nroMaquinas.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        nroMaquinas.setBounds(290,30,300,50);
		
		
		btnEliminarMaquina = new JButton("Eliminar Maquina");
		btnEliminarMaquina.setBounds(340,200,200,50);
	    ManejoBotonAceptar accionBtn = new ManejoBotonAceptar(this);
	    btnEliminarMaquina.addActionListener(accionBtn);
	     
	     
	    panelEliminarMaquina.add(btnEliminarMaquina);
	    panelEliminarMaquina.add(nroMaquinas);
		
		// fin
		c.add(panelEliminarMaquina);
	}

	
	class ManejoBotonAceptar implements ActionListener {
		
		private JFrame ventana;
		
		
		public ManejoBotonAceptar(JFrame ventana) {
			this.ventana = ventana;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			String maquinaElegida = (String) nroMaquinas.getSelectedItem();
			String opcionElegida = (String)opciones.getSelectedItem();
			String creditoIngresado = (String)credito.getText();
			
			if ( e.getActionCommand() == "Cliente") {
				pila.add(panelInicio);
				soyCliente = true;
				try {
					
					inicializarPanelPrincipal();
					panelCabecera.setVisible(true);
					panelInicio.setVisible(false);
					panelPrincipal.setVisible(true);
					
				} catch (MaquinaExcepcion e1) {
					e1.printStackTrace();
				}
			}
			
			if ( e.getActionCommand() == "Administrador") {
				pila.add(panelInicio);
				try {
					soyCliente = false;
					inicializarPanelPrincipal();
					panelCabecera.setVisible(true);
					panelInicio.setVisible(false);
					panelPrincipal.setVisible(true);
				} catch (MaquinaExcepcion e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			if(e.getActionCommand() == "Aceptar") {
				pila.add(panelPrincipal);
				try {
					//System.out.println("entro");
					eleccionPanelPrincipal( e, maquinaElegida, opcionElegida, creditoIngresado);
				} catch (MaquinaExcepcion e1) {
					e1.printStackTrace();
				}		
			}
			if(e.getActionCommand() == "Agregar Premio") {
				try {
					agregarNuevoPremio(e, maquinaElegida, opcionElegida, creditoIngresado);
					
				} catch (NumberFormatException e1) {
					e1.printStackTrace();
				} catch (MaquinaExcepcion e1) {
					e1.printStackTrace();
				}
				
				
			}
			if(e.getActionCommand() == "Eliminar Premio") {
				for(int i=0; i < listaCheckBox.size();i++) {
					if((listaCheckBox.get(i)).isSelected()) {
						try {
							ArrayList<CasillaView> listaCombinacion =	Casino.getInstancia().getMaquinaView(Integer.parseInt(maquinaElegida)).getPremios().get(i).getCombinacion();
							Casino.getInstancia().bajaPremio(Integer.parseInt(maquinaElegida), listaCombinacion);
							listaCheckBox.get(i).setVisible(false);
							listaCheckBox.remove(i);
							JOptionPane.showMessageDialog(ventana, "Se ha eliminado correctamente");
						} catch (MaquinaExcepcion e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}			
					}
				}
			}
			
			if(e.getActionCommand() == "Agregar Maquina") {
				if ( recaudacion.getText().isEmpty() || cantCasillas.getText().isEmpty() || costeJugada.getText().isEmpty() ) {
					JOptionPane.showMessageDialog(ventana, "No ingresaste algun valor.");
				} else if ( Integer.parseInt(cantCasillas.getText()) > 7 ) {
					JOptionPane.showMessageDialog(ventana, "No se pueden ingresar mas de 7 casillas.");
				}else {
					float recaudacionIngresada = Float.parseFloat(recaudacion.getText());
					int casillas = Integer.parseInt(cantCasillas.getText());
					float costeJ = Float.parseFloat(costeJugada.getText());
					Casino.getInstancia().agregarMaquina(recaudacionIngresada, casillas, costeJ);
					JOptionPane.showMessageDialog(ventana, " La Maquina se creo Correctamente ");
				}
				
				recaudacion.setText("");
				cantCasillas.setText("");
				costeJugada.setText("");

				
			}
			
			if(e.getActionCommand() == "Eliminar Maquina") {
				int nroMaquina = Integer.parseInt(maquinaElegida);
				try {
					Casino.getInstancia().eliminarMaquina(nroMaquina);
					JOptionPane.showMessageDialog(ventana, " La Maquina se elimino Correctamente ");
					inicializarPanelEliminarMaquina();
				} catch (MaquinaExcepcion e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
			if (e.getActionCommand() == "Atras") {
				try {
					inicializarPanelPrincipal();
				} catch (MaquinaExcepcion e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				JPanel ultimoPanel = new JPanel();
					if (!pila.isEmpty()) {
						ultimoPanel = pila.get(pila.size() - 1);
						pila.remove(pila.size() - 1);
					}
					
					try {
						if (ultimoPanel == panelInicio ) {
							panelPrincipal.setVisible(false);
						}
						ultimoPanel.setVisible(true);
						panelMaquina.setVisible(false);
						panelAltaPremio.setVisible(false);
						panelBajaPremio.setVisible(false);
						panelCrearMaquina.setVisible(false);
						panelEliminarMaquina.setVisible(false);
						
						if ( soyCliente ) {
							reiniciarSaldoJugador(maquinaElegida);
							credito.setText("");
						}

					
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (MaquinaExcepcion e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
			}
				
			if(e.getActionCommand() == "Jugar") {
				try {
					jugadas(e,  maquinaElegida, creditoIngresado);
				} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (MaquinaExcepcion e1) {
				// TODO Auto-generated catch block
					e1.printStackTrace();
						}
						
					}
				}
				
		}

		public void eleccionPanelPrincipal(ActionEvent e, String maquinaElegida, String opcionElegida, String creditoIngresado) throws MaquinaExcepcion {
			float costeJugada = Casino.getInstancia().getMaquinaView(Integer.parseInt(maquinaElegida)).getCosteJugada();
			Boolean alcanzaRecaudacion = Casino.getInstancia().getMaquinaView(Integer.parseInt(maquinaElegida)).alcanzaRecaudacion();
			
			
				if (opcionElegida == "Jugar" && !creditoIngresado.isEmpty()) {
					float creditoIngresadoF = Float.valueOf(creditoIngresado);
					
					if (creditoIngresadoF < costeJugada) {
						JOptionPane.showMessageDialog(this, " Crédito Insuficiente ");
						
					} else {
						 if(alcanzaRecaudacion == false) {
							 JOptionPane.showMessageDialog(this, " Es posible que no se pueda pagar el premio mayor "); 
							 
						 }
						 
						
						
						String ticket = Casino.getInstancia().generarTicket(creditoIngresadoF).toString();
						JOptionPane.showMessageDialog(this,  ticket);
						
						panelPrincipal.setVisible(false);
						inicializarPanelMaquina();
						panelMaquina.setVisible(true);
					} 
					
					
				} else if ( opcionElegida == "Jugar" && creditoIngresado.isEmpty() ) {
					JOptionPane.showMessageDialog(this, " No ingresaste nada de credito. ");
				}
				if ( opcionElegida == "Dar Alta Premio") {
					panelPrincipal.setVisible(false);
					inicializarPanelAltaPremio();
					panelAltaPremio.setVisible(true);

				}
				if ( opcionElegida == "Dar Baja Premio" ) {
					panelPrincipal.setVisible(false);
					inicializarPanelBajaPremio();
					panelBajaPremio.setVisible(true);
					
				}
				
				if( opcionElegida == "Crear Maquina") {
					panelCrearMaquina.setVisible(true);
					inicializarPanelCrearMaquina();
					panelPrincipal.setVisible(false);
				}
				
				if( opcionElegida == "Eliminar Maquina") {
					panelEliminarMaquina.setVisible(true);
					inicializarPanelEliminarMaquina();
					panelPrincipal.setVisible(false);
				}

		
		}
		
		
		public MaquinaView reiniciarSaldoJugador (String maquinaElegida) throws NumberFormatException, MaquinaExcepcion {
			MaquinaView m = Casino.getInstancia().maquinaReiniciada(Integer.parseInt(maquinaElegida));
			return m;
		}
		
		
		public void agregarNuevoPremio(ActionEvent e, String maquinaElegida, String opcionElegida, String creditoIngresado) throws NumberFormatException, MaquinaExcepcion {
			
			
			if(!((String)montoPremio.getText()).isEmpty()) {
				
				float monto = Float.parseFloat((String)montoPremio.getText());
				crearCombinacionCasillaView();
				Casino.getInstancia().altaPremio(monto, Integer.parseInt(maquinaElegida), combinacionCasillas);	
				
				JOptionPane.showMessageDialog(this, "Se ha Agregado correctamente");
				montoPremio.setText("");
			} else {
				JOptionPane.showMessageDialog(this, " No ingresaste monto para el premio ");
			}
			
		}
		
		
		public void jugadas(ActionEvent e,String maquinaElegida, String creditoIngresado) throws NumberFormatException, MaquinaExcepcion {
			
			if ( Casino.getInstancia().getMaquinaView(Integer.parseInt(maquinaElegida)).getSaldoJugador() != 0 ) {
				Casino.getInstancia().jugar(Integer.parseInt(maquinaElegida));
				
			}
			
			
			else {
				Casino.getInstancia().jugar(Integer.parseInt(maquinaElegida),
						Casino.getInstancia().generarTicket(Float.parseFloat(creditoIngresado)));
				
			}
			
			añadirCasillas(e, maquinaElegida);
			String saldoJugador = Float.toString(Casino.getInstancia().getMaquinaView(Integer.parseInt(maquinaElegida)).getSaldoJugador());
			creditoDisponible.setText("Crédito Disponible:  $" + saldoJugador);
			
			float saldoJugador2  = Casino.getInstancia().getMaquinaView(Integer.parseInt(maquinaElegida)).getSaldoJugador();
			float costeJugada2 = Casino.getInstancia().getMaquinaView(Integer.parseInt(maquinaElegida)).getCosteJugada();
			
			
			if (saldoJugador2 < costeJugada2) {
				JOptionPane.showMessageDialog(this, " Saldo Insuficiente. ");
				reiniciarSaldoJugador(maquinaElegida);
				noJuegaMas(maquinaElegida);
				
				
				}
			
			
			obtenerResultadoMaquina(e, maquinaElegida);
			
		}
		
		
		public void obtenerResultadoMaquina(ActionEvent e,String maquinaElegida) throws MaquinaExcepcion {
			boolean gano = Casino.getInstancia().getMaquinaView(Integer.parseInt(maquinaElegida)).obtenerGano();
			if ( gano ) {
				msjPremio.setText("GANASTE");
				int resp = JOptionPane.showConfirmDialog(this, "¿Quéres seguir jugando?");
				 if(resp == JOptionPane.NO_OPTION) {
					 reiniciarSaldoJugador(maquinaElegida);
					 noJuegaMas(maquinaElegida);
				 }
			}
			else {
				msjPremio.setText("PERDISTE");
			}
		}

		
		public void noJuegaMas(String maquinaElegida) throws NumberFormatException, MaquinaExcepcion {
			panelMaquina.setVisible(false);
			panelPrincipal.setVisible(true);
			String comprobante = Casino.getInstancia().obtenerComprobante().toString();
			JOptionPane.showMessageDialog(this,  comprobante); 
		}
		
		
		public void añadirCasillas(ActionEvent e,String maquinaElegida) throws NumberFormatException, MaquinaExcepcion {
			panelImagenes.removeAll();
			for (int i=0; i<  Casino.getInstancia().getMaquinaView(Integer.parseInt(maquinaElegida)).getCantCasillas();i++) {
					
					String fruta =  Casino.getInstancia().getMaquinaView(Integer.parseInt(maquinaElegida)).getCasillas().get(i).getFruta();
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


