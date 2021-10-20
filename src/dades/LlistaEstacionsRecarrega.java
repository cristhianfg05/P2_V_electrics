package dades;

public class LlistaEstacionsRecarrega {
	private EstacioRecarregaVE[] lista;
	private int numEstacions;
	
	

	
	public void afegirEstacions(EstacioRecarregaVE e) {
		if(numEstacions >= this.lista.length) {
			EstacioRecarregaVE[]listaCopia = new EstacioRecarregaVE[numEstacions+2];
			for(int i = 0; i<numEstacions; i++) {
				listaCopia [i] = this.lista[i];
			}
			this.lista = listaCopia;
		}
		this.lista[numEstacions]=e;
		numEstacions++;
	}
	
	public void eliminarConjuntEstacions(String pob) {//No distinguiremos de provincias o municipios
		for(int i = 0; i<lista.length;i++) {
			if(lista[i].esTrobaEnAquestaProvincia(pob) || lista[i].esTrobaEnAquestMunicipi(pob)) {
				lista[i]=null;
				//Algoritmo para desplazar todos los elementos para atras a partir del elemento eliminado
			}
		}
	}
	
	
}
