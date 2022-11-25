package vista;

public class ComprobanteView {
	
	private int nroComprobante;
	private float monto;
	
	public ComprobanteView(int nroComprobante, float monto) {
		this.nroComprobante = nroComprobante;
		this.monto = monto;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public int getNroComprobante() {
		return nroComprobante;
	}
	
	

}
