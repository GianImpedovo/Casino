package negocio;

import java.util.ArrayList;

public class Premio {
	
	private float monto;
	private ArrayList<Casilla> combinacion;
	
	public Premio(float monto, ArrayList<Casilla> combinacion) {
		this.monto = monto;
		this.combinacion = combinacion;
	}
	
	public void mostrarCombinacion() {
		
		for ( Casilla c: combinacion) {
			System.out.print("  "+c.obtenerFruta());
		}
	}
	
	public float obtenerMonto() {
		return monto;
	}
	
	public boolean soyEsePremio(ArrayList<Casilla> c) {
		boolean encontrado = true;
		//System.out.print("\n" + c.get(0).obtenerFruta());
		//System.out.print("\n" + c.get(1).obtenerFruta());
		//System.out.print("\n" + combinacion.get(0).obtenerFruta());
		//System.out.print("\n" + combinacion.get(1).obtenerFruta());
		//if ( combinacion.equals(c)) {
		//	System.out.print(" Encontrado ");
		//	encontrado = true;			
		//}
		//if (!c.equals(combinacion)) {
		//	encontrado = false;
		//}
		
		for (int i = 0; i < combinacion.size(); i++) {
			
			if (!combinacion.get(i).obtenerFruta().equals(c.get(i).obtenerFruta())){
				encontrado = false;
			}
		}
		
		return encontrado;
	}
	

}
