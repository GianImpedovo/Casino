package test;

import controlador.Casino;
import excepciones.MaquinaExcepcion;
import giu.Ventana;
import giu.VentanaMaquina;
import negocio.Casilla;
import negocio.Comprobante;
import negocio.Ticket;


import java.util.ArrayList;

public class Test {

	public static void main(String[] args) throws MaquinaExcepcion {
		System.out.print(false);
		Ventana v = new Ventana();
		v.setVisible(true);
		//VentanaMaquina vm = new VentanaMaquina(Casino.getInstancia().getMaquinaView(3));
		//vm.setVisible(true);
		
		// Dar de alta Premio
		Casilla c1 = new Casilla("Banana");
		Casilla c2 = new Casilla("Manzana");
		Casilla c3 = new Casilla("Sandia");
		Casilla c4 = new Casilla("Banana");
		Casilla c5 = new Casilla("Sandia");
		Casilla c6 = new Casilla("Banana");
		
		ArrayList<Casilla> combinacion = new ArrayList<Casilla>();
		combinacion.add(c1);
		combinacion.add(c2);
		Casino.getInstancia().altaPremio(1000,1, combinacion);
		
		ArrayList<Casilla> combinacion2 = new ArrayList<Casilla>();
		combinacion2.add(c3);
		combinacion2.add(c4);
		Casino.getInstancia().altaPremio(500, 1, combinacion2);
			
		// Dar de baja Premio
		ArrayList<Casilla> combinacion3 = new ArrayList<Casilla>();
		combinacion3.add(c5);
		combinacion3.add(c6);
		Casino.getInstancia().bajaPremio(1, combinacion3);
			
		// Generamos ticket para jugar 
		Ticket t = Casino.getInstancia().generarTicket(1000);
			
		//Jugar
		System.out.print("\nComienza el juego");
		Comprobante c = Casino.getInstancia().jugar(1, t);
			
		while (Casino.getInstancia().seguirJugando()) {
			c = Casino.getInstancia().jugar(1);
			}
			
		System.out.print("\nTermino el juego");
		System.out.print("\nCobraste : " + Casino.getInstancia().cobrarDinero(c));
		

		}

	}
