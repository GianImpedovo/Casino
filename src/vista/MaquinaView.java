package vista;

import java.util.ArrayList;

import controlador.Casino;
import negocio.Casilla;
import negocio.Premio;

public class MaquinaView {
	
	private int nroMaquina;
	private ArrayList<PremioView> premios;
	private int cantCasillas;
	private ArrayList<CasillaView> casillas;
	private float saldoJugador;
	private float costeJugada;
	private boolean gano;
	private float recaudacion;
	
	
	public MaquinaView(int nroMaquina, ArrayList<PremioView> premios, int cantCasillas, ArrayList<CasillaView> casillas, 
			float saldoJugador, float costeJugada, boolean gano, float recaudacion) {
		this.nroMaquina = nroMaquina;
		this.premios = premios;
		this.cantCasillas = cantCasillas;
		this.casillas = casillas;
		this.saldoJugador = saldoJugador;
		this.costeJugada = costeJugada;
		this.gano = gano;
		this.recaudacion = recaudacion ;
		
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
	
	public float getRecaudacion() {
		return recaudacion;
	}
	
	public ArrayList<PremioView> getPremios() {
		return premios;
	}
	public void setPremios(ArrayList<PremioView> premios) {
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
	
	public boolean alcanzaRecaudacion(){
		boolean alcanza = true;
		for (PremioView p: premios) {
			if (p.obtenerMonto() > this.recaudacion )
				alcanza = false;
		}
		return alcanza;
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
	
	public void reiniciarSaldoJugador() {
		saldoJugador = 0;
	}

}
