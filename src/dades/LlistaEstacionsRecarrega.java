package dades;

/**
 * @author Cristhian Fajardo
 * @author Arnau Papiol
 */
public class LlistaEstacionsRecarrega {
	private EstacioRecarregaVE[] lista;
	private int numEstacions;
	private static double esPropera = 30; 

	/**
	 * Constructor lista vacia y 0 estaciones
	 */
	public LlistaEstacionsRecarrega() {
		this.lista = new EstacioRecarregaVE[0];
		this.numEstacions = 0;
	}

	/**
	 * @param EstacioRecarregaVE e
	 * 
	 * Añade una estación al final pero si no hay espacio en la lista
	 * redimensiona esta de manera rudimentaria, creamos una nueva lista de 2
	 * mas de longitud, se rellena con los datos que tenemos y esta pasa a ser
	 * la lista principal
	 * 
	 */
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

	/**
	 * @param String poblacion
	 * 
	 * Elimina todas las estaciones que sean de ese municipio, nunca dejará un
	 * espacio vacio en la lista con lo que desplazara hacia la izquierda todos
	 * los elementos cada vez que se encuentre una estación a eliminar
	 * 
	 */
	public void eliminarConjuntEstacions(String pob) {
		for (int i = 0; i < numEstacions; i++) {
			if (lista[i].esTrobaEnAquestMunicipi(pob)) {
				for (int j = i; j <= numEstacions-2; j++) {
					lista[j] = lista[j + 1];
				}
				numEstacions--;
			}
		}

	}

	/**
	 * @param String poblacion
	 * @return un print con los datos de todas las estaciones que esten en esa población
	 */
	public String toStringPoblacio(String pob) {
		String aux = "";
		for (int i = 0; i < numEstacions; i++) {
			if (lista[i].esTrobaEnAquestMunicipi(pob))
				aux = aux + lista[i] + "\n";
		}
		return aux;
	}

	/**
	 * @param String poblacion
	 * @return primera instancia de estación que encuentre en ese municipio
	 */
	public EstacioRecarregaVE Instancia_PrimeraEstacion(String pob) {
		EstacioRecarregaVE aux = null;
		int i = 0;
		boolean trobat = false;
		while (i < numEstacions && !trobat) {
			if (lista[i].esTrobaEnAquestMunicipi(pob)) {
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
	/**
	 * @param latitud
	 * @param longitud
	 * @return instancia de l'estació mes propera
	 */
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
	
	/**
	 * @param double proper
	 * 
	 * Modifica la variable static de la clase, que ens diu la distancia per dir
	 * que la estació esta a prop nostra.
	 * 
	 */
	public static void setEsPropera(double p) {
		esPropera = p;
	}

	//Metodo 9 Retorna una llista de estacions properes a naltros (se envia lat y long) se dira que es cercana
	//Si esta distancia es menor a la variable estatica esPropera
}
