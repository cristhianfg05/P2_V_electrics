package dades;

public class LlistaEstacionsRecarrega {
	private EstacioRecarregaVE[] lista;
	private int numEstacions;

	public void afegirEstacions(EstacioRecarregaVE e) {
		if (numEstacions >= this.lista.length) {
			EstacioRecarregaVE[] listaCopia = new EstacioRecarregaVE[numEstacions + 2];
			for (int i = 0; i < numEstacions; i++) {
				listaCopia[i] = this.lista[i];
			}
			this.lista = listaCopia;
		}
		this.lista[numEstacions] = e;
		numEstacions++;
	}

	public void eliminarConjuntEstacions(String pob) {// No distinguiremos de provincias o municipios
		for (int i = 0; i < lista.length; i++) {
			if (lista[i].esTrobaEnAquestaProvincia(pob) || lista[i].esTrobaEnAquestMunicipi(pob)) {
				lista[i] = null;
				// Algoritmo para desplazar todos los elementos para atras a partir del elemento
				// eliminado
				// Despues del algoritmo de desplazamiento restamos 1 por cada elemento
				// coincidente que encuentre
				numEstacions--;
			}
		}

	}

	public String toStringPoblacio(String pob) {
		String aux = "";
		for (int i = 0; i < lista.length; i++) {
			if (lista[i].esTrobaEnAquestaProvincia(pob) || lista[i].esTrobaEnAquestMunicipi(pob))
				aux = aux + lista[i] + "\n";
		}
		return aux;
	}

	public EstacioRecarregaVE toStringPrimeraEstacion(String pob) {
		EstacioRecarregaVE aux = null;
		int i = 0;
		boolean trobat = false;
		while (i < lista.length && !trobat) {
			if (lista[i].esTrobaEnAquestaProvincia(pob) || lista[i].esTrobaEnAquestMunicipi(pob)) {
				aux = lista[i];
				trobat = true;
			}

		}
		return aux;
	}

}