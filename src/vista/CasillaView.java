package vista;

public class CasillaView {
	
	private String fruta;
	
	public CasillaView(String fruta) {
		this.fruta = fruta;
	}
	
	public String getFruta() {
		
		return fruta;
		
	}
	public String toString() {
		String fruta = " ";
		 fruta = getFruta();
		return fruta;
	}
	
}
