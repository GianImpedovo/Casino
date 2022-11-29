package controlador;

import negocio.Casilla;
import negocio.Comprobante;
import negocio.Maquina;
import negocio.Ticket;
import vista.CasillaView;
import vista.ComprobanteView;
import vista.MaquinaView;
import vista.TicketView;

import java.io.IOException;
import java.util.*;

import excepciones.MaquinaExcepcion;

public class Casino {
	
	
	private static Casino instancia;
	private Collection<Ticket> tickets;
	private Collection<Maquina> maquinas;
	private ArrayList<Comprobante> comprobantes;
	
	private Casino() {
		this.tickets = new ArrayList<Ticket>();
		this.maquinas = new ArrayList<Maquina>();
		this.comprobantes = new ArrayList<Comprobante>();
		Maquina m1 = new Maquina(1000, 2, 200);
		Maquina m2 = new Maquina(1000, 3, 300);
		Maquina m3 = new Maquina(1000, 4, 400);
		maquinas.addAll(Arrays.asList(m1,m2, m3));
		//System.out.print(m1.obtenerNroMaquina());
		//System.o ut.print(m2.obtenerNroMaquina());
	}
	
	public static Casino getInstancia(){
		if( instancia == null ) 
			instancia = new Casino();
		return instancia;
	}
	
	public Ticket generarTicket(float monto){
		Ticket t = new Ticket(monto);
		tickets.add(t);
		return t;
	}
	
	public ComprobanteView jugar(int nroMaquina, Ticket t) throws MaquinaExcepcion{
		Comprobante c = null;
		Maquina m = buscarMaquina(nroMaquina);
		m.iniciarJuego( t.obtenerMonto());
		c = m.generarComprobante();
		
		comprobantes.add(c);
		return c.toView();
		
	}
	
	
	public ComprobanteView jugar(int nroMaquina) throws MaquinaExcepcion {
		Comprobante c = null;
		Maquina m = buscarMaquina(nroMaquina);
		m.iniciarJuego();
		c = m.generarComprobante();
		
		comprobantes.add(c);
		return c.toView();
	}
	
	
	private Maquina buscarMaquina(int nroMaquina) throws MaquinaExcepcion {
		for(Maquina m: maquinas) 
			if ( m.soyEsaMaquina(nroMaquina) )
					return m;
		throw new MaquinaExcepcion("No se encontro la maquina solicitada");
	}
	
	public void altaPremio(float monto, int nroM, ArrayList<String> combinacion) throws MaquinaExcepcion{
		ArrayList<Casilla> nuevaCombinacionCasilla = new ArrayList<Casilla>();
		for(String fruta: combinacion) {
			nuevaCombinacionCasilla.add(new Casilla(fruta));
		}
		Maquina m = buscarMaquina(nroM);
		m.darAltaPremio(monto, nuevaCombinacionCasilla);
			
	}
	
	public void bajaPremio (int nroMaquina, ArrayList<Casilla> combinacion) throws MaquinaExcepcion {
		Maquina m = buscarMaquina(nroMaquina);
		m.darBajaPremio(combinacion);
		
	}
	public float cobrarDinero (Comprobante c) {
		comprobantes.add(c);
		return c.obtenerMonto();
	} 
	
	public boolean seguirJugando() {
		boolean continuar = false;
		System.out.print("\nIngrese 1 para seguir jugando o OTRO NUMERO para terminar: ");
		int decision = 0;

		try {
			decision = System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (decision == 1) {
		continuar = true;
		}

		//entrada.close();
		return continuar;
	
	}
	
	public MaquinaView getMaquinaView(int nroMaquina) throws MaquinaExcepcion{
		Maquina m = buscarMaquina(nroMaquina);
		return m.toView();
	}
	
	public TicketView generarTicketView(Float monto) {
		Ticket t = new Ticket(monto);
		return t.toView();
	}
	
	public ComprobanteView obtenerComprobante() {
		Comprobante aux = comprobantes.get(comprobantes.size() - 1);
		return aux.toView();
	}
	
	

}
