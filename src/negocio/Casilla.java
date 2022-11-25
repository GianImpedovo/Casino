package negocio;

import java.util.ArrayList;
import java.util.Random;

import vista.CasillaView;

public class Casilla {
	
	private static ArrayList<String> frutas = new ArrayList<String>();
	private String fruta;
	
	public Casilla() {
		frutas.add("Banana");
		frutas.add("Manzana");
		frutas.add("Frutilla");
		frutas.add("Guinda");
		frutas.add("Sandia");
		frutas.add("Uva");
		
		Random r = new Random();
		int i = r.nextInt(frutas.size());
		fruta = frutas.get(i);
	}
	
	public Casilla(String fruta){
		this.fruta = fruta;
	}
	
	public String obtenerFruta () {
		return fruta;
	}
	
	public CasillaView toView() {
		CasillaView cv = new CasillaView(fruta);
		return cv;
	}
}
