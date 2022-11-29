package negocio;

import java.util.ArrayList;

import vista.CasillaView;
import vista.PremioView;

public class Premio {
	
	private float monto;
	private ArrayList<Casilla> combinacion;
	
	public Premio(float monto, ArrayList<Casilla> combinacion) {
		this.monto = monto;
		this.combinacion = combinacion;
		System.out.println("\n Alta premio \n");
		//System.out.println("Se dio de alta el premio monto : " + this.monto);
		for ( Casilla c: this.combinacion) {
			System.out.println("fruta: " + c.obtenerFruta());
		}
	}
	
	public void mostrarCombinacion() {
		
		for ( Casilla c: combinacion) {
			System.out.print("  "+c.obtenerFruta());
			
		}
	}
	public ArrayList<Casilla> obtenerCombinacion(){
		return combinacion;
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
	
	public PremioView toView() {
		ArrayList<CasillaView> combinacionVista = new ArrayList<CasillaView>();
		for(Casilla c: combinacion) {
			combinacionVista.add(c.toView());
		}
		
		PremioView pv = new PremioView(monto, combinacionVista);
		return pv;
		
	}
}
