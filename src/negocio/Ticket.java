package negocio;

import vista.TicketView;

public class Ticket {
	
	private static int aumentoNroTicket = 0;
	private int nroTicket;
	private float monto;
	
	public Ticket(float monto) {
		this.monto = monto;
		this.nroTicket = aumentoNroTicket;
		aumentarNroTicket();
	}
	
	public static void aumentarNroTicket() {
		aumentoNroTicket++;
	}
	
	public float obtenerMonto() {
		return monto;
	}
	
	public boolean soyEseTicket(int nroTicket) {
		boolean encontrado = false;
		if ( this.nroTicket == nroTicket)
			encontrado = true;
		return encontrado;
	}
	
	public TicketView toView() {
		TicketView tv = new TicketView(nroTicket, monto);
		return tv;
	}
}
