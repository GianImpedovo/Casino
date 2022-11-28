package vista;

import java.util.ArrayList;



public class PremioView {
		private Float monto;
		private ArrayList<CasillaView> combinacionVista;
	

	public PremioView(float monto, ArrayList<CasillaView> combinacionVista) {
		this.monto = monto;
		this.combinacionVista = combinacionVista;
	}
	

	public ArrayList<CasillaView> getCombinacion(){
		return combinacionVista;
	}
	public float obtenerMonto() {
		return monto;
		
	}
	public String toString() {
		String infoPremio = "Casillas :" + combinacionVista + "/" + monto;
		return infoPremio;
	}
}
