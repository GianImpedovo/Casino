package vista;

public class TicketView {
	
	private int nroTicket;
	private float monto;

	public TicketView(int nroTicket, float monto) {
		this.nroTicket = nroTicket;
		this.monto = monto;
	}
	
	public float getMonto() {
		return monto;
	}
	
	public void setMonto(float monto) {
		this.monto = monto;
	}
	
	public int getNroTicket() {
		return nroTicket;
	}
	
	public String toString() {
		String informacion = "Ticket nro: " + nroTicket + " | Crédito: " + monto;
		return informacion;
	}
	

}
