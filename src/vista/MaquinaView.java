package vista;

import java.util.ArrayList;

import negocio.Casilla;
import negocio.Premio;

public class MaquinaView {
	
	private int nroMaquina;
	private ArrayList<Premio> premios;
	private int cantCasillas;
	private ArrayList<CasillaView> casillas;
	private float saldoJugador;
	private float costeJugada;
	private boolean gano;
	
	
	public MaquinaView(int nroMaquina, ArrayList<Premio> premios, int cantCasillas, ArrayList<CasillaView> casillas, 
			float saldoJugador, float costeJugada, boolean gano) {
		this.nroMaquina = nroMaquina;
		this.premios = premios;
		this.cantCasillas = cantCasillas;
		this.casillas = casillas;
		this.saldoJugador = saldoJugador;
		this.costeJugada = costeJugada;
		this.gano = gano;
		
	}

	public int getNroMaquina() {
		return nroMaquina;
	}
	
	public int getCantCasillas() {
		return cantCasillas;
	}
	
	public float getCosteJugada() {
		return costeJugada;
	}
	
	public ArrayList<Premio> getPremios() {
		return premios;
	}
	public void setPremios(ArrayList<Premio> premios) {
		this.premios = premios;
	}
	public ArrayList<CasillaView> getCasillas() {
	return casillas;
	}
	public void setCasillas(ArrayList<CasillaView> casillas) {
		this.casillas = casillas;
	}
	public float getSaldoJugador() {
		return saldoJugador;
	}
	public void setSaldoJugador(float saldoJugador) {
		this.saldoJugador = saldoJugador;
	}
	
	public String toString() {
		String datos = "casillas: " + cantCasillas + " | coste: " + costeJugada;
		return datos;
	}
	
	public String obtenerNombre() {
		return Integer.toString(nroMaquina);
	}
	
	public boolean obtenerGano() {
		return gano;
	}

}
