package giu;


import java.awt.Container;
import java.awt.Font;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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
	private JTextField credito;
	private JButton aceptar;
	private JComboBox<String>  opciones, nroMaquinas;

	
	public Ventana() throws MaquinaExcepcion {

		// Valores de la ventana principal
		this.setTitle("Casino");
		ImageIcon imgMoneda = new ImageIcon(getClass().getResource("/img/coin-solid-24.png"));
		this.setIconImage(imgMoneda.getImage());
		this.setBounds(100,100,800,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		configuracion();
		
	} 
	
	public void configuracion() throws MaquinaExcepcion {
		Container c = this.getContentPane();
		c.setLayout(new GridLayout(1,3));
		
		// Valores de cada Label
		titulo = new JLabel("Casino", SwingConstants.CENTER);
		titulo.setFont(new Font("Serif", Font.PLAIN, 40));
		
        String[] nros = {Casino.getInstancia().getMaquinaView(1).obtenerNombre(),
				 Casino.getInstancia().getMaquinaView(2).obtenerNombre(),
			     Casino.getInstancia().getMaquinaView(3).obtenerNombre()};
        nroMaquinas = new JComboBox<>(nros);
        ((JLabel)nroMaquinas.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		
		
		JPanel panel_Lbl = inicializaPanelDeLbl1();
		JPanel panel_Opciones = inicializaPanelDeOpciones();
		JPanel panel_Datos = inicializaPanelDeDatos();

        
		
		c.add(panel_Lbl);
		c.add(panel_Opciones);
		c.add(panel_Datos);
		
	}
	
	public JPanel inicializaPanelDeLbl1() {
		
		JPanel panelLbl = new JPanel(new GridLayout(5,1));
		txtMaquina = new JLabel("Maquina", SwingConstants.CENTER);
		txtMaquina.setFont(new Font("Serif", Font.BOLD, 30));
		txtOpciones = new JLabel("Opciones", SwingConstants.CENTER);
		txtOpciones.setFont(new Font("Serif", Font.BOLD, 30));
		txtCredito = new JLabel("Crédito", SwingConstants.CENTER);
		txtCredito.setFont(new Font("Serif", Font.BOLD, 30));
		
		panelLbl.add(new JLabel());
		panelLbl.add(txtMaquina);
		panelLbl.add(txtOpciones);
		panelLbl.add(txtCredito);
		panelLbl.add(new JLabel());
		
		return panelLbl;
	}
	
	public JPanel inicializaPanelDeOpciones() throws MaquinaExcepcion {
		JPanel panel_Opciones = new JPanel(new GridLayout(5,1, 70, 70));
		
		titulo = new JLabel("Casino", SwingConstants.CENTER);
		titulo.setFont(new Font("Serif", Font.BOLD, 40));
        
        String[] listaOpciones = {"Jugar", "Dar Alta Premio", "Dar Baja Premio"};
        opciones = new JComboBox<>(listaOpciones);
        ((JLabel)opciones.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        
        credito = new JTextField();
        aceptar = new JButton("Aceptar");
        
        panel_Opciones.add(titulo);
        panel_Opciones.add(nroMaquinas);
        panel_Opciones.add(opciones);
        panel_Opciones.add(credito);
        panel_Opciones.add(aceptar);
        
        ManejoBotonAceptar accionBtn = new ManejoBotonAceptar(this);
        aceptar.addActionListener(accionBtn);
        
        return panel_Opciones;
	}
	
	public JPanel inicializaPanelDeDatos() throws MaquinaExcepcion {
		JPanel panel_Datos = new JPanel(new GridLayout(3,1));
        datosMaquina = new JLabel(Casino.getInstancia().getMaquinaView(1).toString(), SwingConstants.CENTER);
        datosMaquina.setFont(new Font("Serif", Font.BOLD, 20));
        
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
        
        panel_Datos.add(datosMaquina);
        panel_Datos.add(new JLabel(maquinaI));        
        panel_Datos.add(new JLabel());
        
        return panel_Datos;
	}
	
	class ManejoBotonAceptar implements ActionListener {
		
		private JFrame ventana;
		VentanaMaquina vm;
		
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
						vm = new VentanaMaquina(Casino.getInstancia().getMaquinaView(Integer.parseInt(maquinaElegida)));
					} catch (MaquinaExcepcion e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					vm.setVisible(true);
				} else if ( creditoIngresado.isEmpty() ) {
					JOptionPane.showMessageDialog(ventana, " No ingresaste nada de credito. ");
				}
				
				if ( opcionElegida == "Dar Alta Premio") {
					// Crear la ventana de alta premio
					JOptionPane.showMessageDialog(ventana, "Ventana alta premio");
				}
				
				if ( opcionElegida == "Dar Baja Premio" ) {
					// Crear la ventana para baja premio
					JOptionPane.showMessageDialog(ventana, "Ventana baja premio");
					
				}
					
				
				
			}
			
		}
		
	}
}


