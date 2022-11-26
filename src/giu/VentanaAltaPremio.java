package giu;

import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import excepciones.MaquinaExcepcion;
import vista.CasillaView;
import vista.MaquinaView;

public class VentanaAltaPremio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel titulo, txtFruta;
	private MaquinaView mv;
	private JComboBox<String>  opciones;
	private JButton aceptar;
	//JLabel credito;
	
	
	public VentanaAltaPremio(MaquinaView mv) throws MaquinaExcepcion{
		this.mv = mv;
		
		//Valores de la ventana maquina
		this.setTitle("Casino");
		ImageIcon imgMoneda = new ImageIcon(getClass().getResource("/img/coin-solid-24.png"));
		this.setIconImage(imgMoneda.getImage());
		this.setBounds(100,100,800,600);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
        String[] listaOpciones = {"Banana", "Frutilla", "Manzana", "Sandia", "Uva", "Guinda"};
        this.opciones = new JComboBox<>(listaOpciones);
        ((JLabel)opciones.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		

	}
	
	public void configuracion() {
		int x = 100, y = 100, ancho = 100, alto = 100;
		
		Container c = this.getContentPane();
		for ( int i = 0; i < 1; i++) {
			txtFruta = new JLabel("Nombre");
			txtFruta.setBounds(x,y,ancho, alto);
			c.add(txtFruta);
		}
		
		
		

	}
	
	
	

		
}