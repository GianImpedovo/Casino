package negocio;

import vista.ComprobanteView;

public class Comprobante {
	
	private static int incrementador = 1;
	private int nroComprobante;
	private float monto;
	
	public Comprobante(float monto) {
		this.monto = monto;
		this.nroComprobante = incrementador;
		aumentarNroComprobante();
	}
	
	public void aumentarNroComprobante() {
		Comprobante.incrementador++;
	}

	public float obtenerMonto() {
		return monto;
	}
	
	public ComprobanteView toView() {
		ComprobanteView cv = new ComprobanteView(nroComprobante, monto);
		return cv;
	}
	

}
