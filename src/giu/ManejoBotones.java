package giu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import controlador.Casino;

public abstract class ManejoBotones implements ActionListener{
	private JFrame VentanaMaquina;
	
	public ManejoBotones (JFrame VentanaMaquina) {
		this.VentanaMaquina = VentanaMaquina;
		
	}
	
	@Override	
	public void actionPerformed(ActionEvent jugar) {
		if(jugar.getActionCommand() == "Jugar") {
			
			
		}
		else
		{}
}
	}
