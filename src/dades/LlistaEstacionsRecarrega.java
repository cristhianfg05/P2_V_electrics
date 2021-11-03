package dades;

public class LlistaEstacionsRecarrega {
	private EstacioRecarregaVE[] lista;
	private int numEstacions;
	private static double esPropera = 30; 

	public LlistaEstacionsRecarrega() {
		this.lista = new EstacioRecarregaVE[0];
		this.numEstacions = 0;
	}

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

	public void eliminarConjuntEstacions(String pob) {
		for (int i = 0; i < numEstacions; i++) {
			if (lista[i].esTrobaEnAquestaProvincia(pob) || lista[i].esTrobaEnAquestMunicipi(pob)) {
				for (int j = i; j <= numEstacions-2; j++) {
					lista[j] = lista[j + 1];
				}
				numEstacions--;
			}
		}

	}

	public String toStringPoblacio(String pob) {
		String aux = "";
		for (int i = 0; i < numEstacions; i++) {
			if (lista[i].esTrobaEnAquestaProvincia(pob) || lista[i].esTrobaEnAquestMunicipi(pob))
				aux = aux + lista[i] + "\n";
		}
		return aux;
	}

	public EstacioRecarregaVE Instancia_PrimeraEstacion(String pob) {
		EstacioRecarregaVE aux = null;
		int i = 0;
		boolean trobat = false;
		while (i < numEstacions && !trobat) {
			if (lista[i].esTrobaEnAquestaProvincia(pob) || lista[i].esTrobaEnAquestMunicipi(pob)) {
				aux = lista[i];
				trobat = true;
			}

		}
		return aux;
	}

	// Metodo 5 reotrnar el numero de estaciones que disponen puntos de recarrega de
	// un tipus de estacio
	
	
	// Metodo 6 retornar la instancia de la estacion con mas plazas, en caso de
	// empate devolvemos cualquiera
	
	
	// Metodo 7 retornar un duplicat de la instancia de l'estacio mes propera a la
	// nostra posicio (Metodo distanciaA de la clase EstacioRecarregaE)
	public EstacioRecarregaVE EstacioMesPropera(int lat, int longi) {
		EstacioRecarregaVE e = lista[0].copia();
		//Ya guardamos la primera distancia para tener una con la que comparar
		//Al no ser int no disponemos del MAX_VALUE
		double distancia = lista[0].distanciaA(lat, longi);
		for (int i = 1; i < numEstacions; i++) {
			if (lista[i].distanciaA(lat, longi) < distancia) {
				distancia = lista[i].distanciaA(lat, longi);
				e = lista[i].copia();
			}

		}
		return e;
	}
	
	public static void setEsPropera(double p) {
		esPropera = p;
	}

	//Metodo 9 Retorna una llista de estacions properes a naltros (se envia lat y long) se dira que es cercana
	//Si esta distancia es menor a la variable estatica esPropera
}
