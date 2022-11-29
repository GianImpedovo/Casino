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
		// Ojo aca porque fijate que estas imprimiento una lista como si fuese string 
		// ojo como manejas los tipos de datos
		
		String infoPremio = " ";
		String casillaPremio = " ";
		
		for (int i =0 ; i < combinacionVista.size() ; i++) {
			casillaPremio +=  combinacionVista.get(i) + "-";
			 
		}
		
		infoPremio = "Casillas :" + casillaPremio  + "/" + monto;
		
		return infoPremio;
		
		
	}
}
