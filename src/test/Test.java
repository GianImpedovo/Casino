package test;

import controlador.Casino;
import excepciones.MaquinaExcepcion;
import giu.Ventana;
import negocio.Casilla;
import negocio.Comprobante;
import negocio.Ticket;


import java.util.ArrayList;

public class Test {

	public static void main(String[] args) throws MaquinaExcepcion {
		
		// Dar de alta Premio
		String c1 = new String("Banana");
		String c2 = new String("Manzana");
		String c3 = new String("Sandia");
		String c4 = new String("Banana");
		String c5 = new String("Sandia");
		String c6 = new String("Banana");
		
		ArrayList<String> combinacion = new ArrayList<String>();
		combinacion.add(c1);
		combinacion.add(c2);
		Casino.getInstancia().altaPremio(1000,1, combinacion);
		
		ArrayList<String> combinacion2 = new ArrayList<String>();
		combinacion2.add(c3);
		combinacion2.add(c4);
		Casino.getInstancia().altaPremio(500, 1, combinacion2);
		
		
		Ventana v = new Ventana();
		v.setVisible(true);
		
		//VentanaMaquina mv = new VentanaMaquina(Casino.getInstancia().getMaquinaView(1).getNroMaquina(), "1000");
		//mv.setVisible(true);

		

		}

	}