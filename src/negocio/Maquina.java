package negocio;

import java.util.ArrayList;

import vista.CasillaView;
import vista.MaquinaView;
import vista.PremioView;

public class Maquina {
	
	private static int proximoNroMaquina = 1;
	private int nroMaquina;
	private float recaudacion;
	private ArrayList<Premio> premios;
	private int cantCasillas;
	private ArrayList<Casilla> casillas;
	private float saldoJugador;
	private float costeJugada;
	private boolean gano;
	
	public Maquina(float recaudacion, int cantCasillas, float costeJugada){
		System.out.println("Se creo maquina");
		this.recaudacion = recaudacion;
		this.premios = new ArrayList<Premio>();
		this.cantCasillas = cantCasillas;
		this.casillas = new ArrayList<Casilla>();
		this.saldoJugador = 0;
		this.costeJugada = costeJugada;
		this.nroMaquina = proximoNroMaquina;
		this.gano = false;

		aumentarNroMaquina();

	}
	
	public int obtenerNroMaquina() {
		return nroMaquina;
	}
	
	public void aumentarNroMaquina(){
		proximoNroMaquina++;
	}
	
	public boolean soyEsaMaquina(int nroMaquina) {
		boolean encontrada = false;
		if ( nroMaquina == this.nroMaquina )
			encontrada = true;
		return encontrada;
	}
	
	public void iniciarJuego(float saldoJ){
		saldoJugador = saldoJ;
		gano = false;
		float monto = 0;
		
		if ( !alcanzaRecaudacion() ) 
			System.out.print("\nEs posible que no se pueda pagar el proximo premio");
		
		if (saldoJugador >= costeJugada) {
			generarCasillas();
			for ( Premio p: premios){
				if(p.soyEsePremio(casillas)) {
					monto = p.obtenerMonto();
					System.out.print("\nObtuviste premio de : " + monto );
					gano = true;
			}
			}
			if(!gano)
				System.out.print("\nNo obtuviste ningun premio - saldo: " + saldoJugador);		

			actualizarSaldo(monto);
			actualizarRecaudacion(monto);
			
		}
		
		casillas.clear();
		System.out.print("\nSaldo Final " + saldoJugador);
	}
	
	public void iniciarJuego(){
		float monto = 0;
		gano = false;
		if ( !alcanzaRecaudacion() ) 
			System.out.print("\nEs posible que no se pueda pagar el proximo premio");
		
		if (saldoJugador >= costeJugada){
			generarCasillas();
			for ( Premio p: premios){
				if (p.soyEsePremio(casillas)) {
					monto = p.obtenerMonto();	
					System.out.print("\nObtuviste premio de : " + monto );
					gano = true;
				} 
			}
			if(!gano)
				System.out.print("\nNo obtuviste ningun premio - saldo: " + saldoJugador);
			actualizarSaldo(monto);
			actualizarRecaudacion(monto);
		}
		
		casillas.clear();
		System.out.print("\nSaldo Final " + saldoJugador);
	}
	
	public void generarCasillas() {
		//Casilla c1 = new Casilla("Banana");
		//Casilla c2 = new Casilla("Manzana");
		//casillas.add(c1);
		//casillas.add(c2);
		for (int i = 0; i < this.cantCasillas; i++) {
			Casilla c = new Casilla();
			casillas.add(c);
		}
		
		//System.out.print("\n Las casillas de la maquina son : ");
		//for (Casilla c: casillas) {
		//	System.out.println("\n " + c.obtenerFruta());
		//	
		//}
	}
	
	private void actualizarSaldo(float monto) {
		if (gano)
			System.out.print("\n Aumenta saldo : " + monto );
			this.saldoJugador += monto;
		System.out.print("\n menos saldo : " + costeJugada );
		this.saldoJugador -= this.costeJugada;
	
	}
	
	private void actualizarRecaudacion(float monto) {
		if (gano)
			this.recaudacion -= monto;
		this.recaudacion += this.costeJugada;
		
	}
	
	public void darAltaPremio(float monto, ArrayList<Casilla> combinacion) {
		Premio p = new Premio(monto, combinacion);
		premios.add(p);
		System.out.println("\nSe dio de alta el premio con monto : " + p.obtenerMonto());
	}
	
	public void darBajaPremio(ArrayList<CasillaView> combinacion) {
		int i = -1;
		ArrayList<Casilla> combinacionCasilla = new ArrayList<Casilla>();
		for (CasillaView c: combinacion) {
			combinacionCasilla.add(new Casilla(c.getFruta()));
		}
		for ( Premio p: premios ) {
			if (p.soyEsePremio(combinacionCasilla)) {
				i = premios.indexOf(p);
				System.out.print("\nSe da de baja el premio con monto: " + premios.get(i).obtenerMonto() + "\n");
			}
		}
		if ( i != -1 ) {
			premios.remove(i);
		}
	}
	
	public Comprobante generarComprobante() {
		Comprobante c = new Comprobante(saldoJugador);
		return c;
	}
	
	private boolean alcanzaRecaudacion() {
		boolean alcanza = true;
		for (Premio p: premios) {
			if (p.obtenerMonto() > this.recaudacion )
				alcanza = false;
		}
		return alcanza;
	}
	
	
	public void reiniciarSaldoJugador() {
		saldoJugador = 0;
	}
	
	public MaquinaView toView() {
		generarCasillas();
		ArrayList<CasillaView> casillasVista = new ArrayList<CasillaView>();
		for (Casilla c: casillas) {
			casillasVista.add(c.toView());
		}
		ArrayList<PremioView> premiosView = new ArrayList<PremioView>();
		for ( Premio p: premios) {
			premiosView.add(p.toView());
		}
		//System.out.println(premios.get(0).toView().obtenerMonto());
		MaquinaView mv = new MaquinaView(nroMaquina, premiosView, cantCasillas, casillasVista, saldoJugador, costeJugada, gano,recaudacion);
		return mv;
	}

}
