package giu;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controlador.Casino;
import excepciones.MaquinaExcepcion;
import vista.MaquinaView;

public class Ventana extends JFrame{
	
	private static final long serialVersionUID = -6710917183940403534L;
	private JLabel titulo, txtMaquina, txtOpciones, txtCredito, datosMaquina;
	private JTextField credito;
	private JButton aceptar;
	private Timer timer;
	
	
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
		c.setLayout(new GridLayout(1,3));
		
		// Valores de cada Label
		titulo = new JLabel("Casino", SwingConstants.CENTER);
		titulo.setFont(new Font("Serif", Font.PLAIN, 40));
		
		ImageIcon maquinaI = new ImageIcon(getClass().getResource("/img/maquina_200x200.png"));
		
		JPanel panel_Lbl = new JPanel(new GridLayout(5,1));
		JPanel panel_Opciones = new JPanel(new GridLayout(5,1, 70, 70));
		JPanel panel_Datos = new JPanel(new GridLayout(3,1));
		
		txtMaquina = new JLabel("Maquina", SwingConstants.CENTER);
		txtMaquina.setFont(new Font("Serif", Font.BOLD, 30));
		txtOpciones = new JLabel("Opciones", SwingConstants.CENTER);
		txtOpciones.setFont(new Font("Serif", Font.BOLD, 30));
		txtCredito = new JLabel("Crédito", SwingConstants.CENTER);
		txtCredito.setFont(new Font("Serif", Font.BOLD, 30));
		
		panel_Lbl.add(new JLabel());
		panel_Lbl.add(txtMaquina);
		panel_Lbl.add(txtOpciones);
		panel_Lbl.add(txtCredito);
		panel_Lbl.add(new JLabel());
		
		titulo = new JLabel("Casino", SwingConstants.CENTER);
		titulo.setFont(new Font("Serif", Font.BOLD, 40));

		
        Integer[] nros = {Casino.getInstancia().getMaquinaView(1).getNroMaquina(),
        				 Casino.getInstancia().getMaquinaView(2).getNroMaquina(),
        			     Casino.getInstancia().getMaquinaView(3).getNroMaquina()};
        JComboBox<Integer> nroMaquinas = new JComboBox<>(nros);
        ((JLabel)nroMaquinas.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        
        String[] listaOpciones = {"Jugar", "Dar Alta Premio", "Dar Baja Premio"};
        JComboBox<String>  opciones = new JComboBox<>(listaOpciones);
        ((JLabel)opciones.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
        
        credito = new JTextField();
        aceptar = new JButton("Aceptar");
        
        panel_Opciones.add(titulo);
        panel_Opciones.add(nroMaquinas);
        panel_Opciones.add(opciones);
        panel_Opciones.add(credito);
        panel_Opciones.add(aceptar);
		
        
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
        
        panel_Datos.add(datosMaquina);
        panel_Datos.add(new JLabel(maquinaI));        
        panel_Datos.add(new JLabel());
        
		
		c.add(panel_Lbl);
		c.add(panel_Opciones);
		c.add(panel_Datos);
		
	}


}
