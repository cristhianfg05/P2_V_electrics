package aplicacio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

import dades.EstacioRecarregaVE;
import dades.LlistaEstacionsRecarrega;

public class Main {

	static Scanner teclat = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Indica el numero de linies a llegir del fitxer (maxim 418)");
		int numLinies = Integer.parseInt(teclat.nextLine());
		int opcio;
		boolean sortir = false;
		float latitud, longitud;
		String poblacio, tipus;
		boolean metodo = false;

		String[] dataset = llegirLiniesFitxer(numLinies);
		LlistaEstacionsRecarrega listaActual = new LlistaEstacionsRecarrega();

		for (int i = 0; i < dataset.length; i++) {
			String datasetSplit[] = dataset[i].split(";");
			listaActual.afegirEstacions(new EstacioRecarregaVE(datasetSplit[0], datasetSplit[1],
					Float.parseFloat(datasetSplit[2].replace(',', '.')),
					Float.parseFloat(datasetSplit[3].replace(',', '.')), datasetSplit[4], datasetSplit[5],
					Integer.parseInt(datasetSplit[6])));
		}

		System.out.println("Introdueix la latitud de la teva posició: ");
		latitud = teclat.nextFloat();
		System.out.println("Introdueix la longitud de la teva posició: ");
		longitud = teclat.nextFloat();

		while (!sortir) {
			System.out.println(
					"Escull l'opció del següent menú: \n 1.- Eliminar estacions d'una població \n 2.- Veure la primera instancia de LLeida i Barcelona i veure l'estació més propera de les dos \n 3.- Mostrar estacions d'un tipus de velocitat \n 4.- Mostrar estació amb més places \n 5.- Instància de l'estació més propera \n 6.- Estacions més properes a 30 km \n 7.- Estacions més properes a 50 km \n 8.- De les estacions properes veure l'estació amb més places \n 9.- Mostrar estacions de la llista \n 10.- Cambiar latitud y longitud\n 11.- Sortir del programa ");
			opcio = teclat.nextInt();
			switch (opcio) {

			case 1:
				System.out.print("Introdueix el nom de la població: ");
				poblacio = teclat.next();
				System.out.println("Aquestes son les estacions que estan en aquesta població: \n");
				System.out.println(listaActual.toStringPoblacio(poblacio));

				listaActual.eliminarConjuntEstacions(poblacio);
				System.out.println("\n Un cop eliminades aquestes son les estacions d'aquesta població: \n");
				System.out.println(listaActual.toStringPoblacio(poblacio));
				break;

			case 2:
				System.out.println(listaActual.Instancia_PrimeraEstacion("Lleida"));
				System.out.println(listaActual.Instancia_PrimeraEstacion("Barcelona"));
				if (listaActual.Instancia_PrimeraEstacion("Lleida") != null && listaActual.Instancia_PrimeraEstacion("Barcelona") != null) {
					if (listaActual.Instancia_PrimeraEstacion("LLeida").distanciaA(latitud, longitud) > listaActual.Instancia_PrimeraEstacion("Barcelona").distanciaA(latitud, longitud)) {
						System.out.println("L'estació més propera és " + listaActual.Instancia_PrimeraEstacion("Lleida"));
					} else {
						System.out.println("L'estació més propera és " + listaActual.Instancia_PrimeraEstacion("Barcelona"));
					}
				}else if(listaActual.Instancia_PrimeraEstacion("Barcelona") == null)
					System.out.println("L'estació més propera és " + listaActual.Instancia_PrimeraEstacion("Lleida"));
				else if(listaActual.Instancia_PrimeraEstacion("Lleida") == null)
					System.out.println("L'estació més propera és " + listaActual.Instancia_PrimeraEstacion("Barcelona")+" "+listaActual.Instancia_PrimeraEstacion("Barcelona").distanciaA(latitud, longitud));
					
				break;

			case 3: // VA BIEN
				System.out.print("Introdueix el tipus de velocitat: ");
				tipus = teclat.nextLine();
				System.out.println("\nHi han " + listaActual.numEstacionsTipus(tipus) + " del tipus " + tipus + "\n");
				break;

			case 4:
				if (listaActual.getLlista() == null) {
					System.out.println("NULL");
				} else {
					System.out.println(listaActual.InstanciaMasPlazas());
				}
				break;

			case 5: // VA BIEN
				System.out.println(listaActual.EstacioMesPropera(latitud, longitud));
				break;
			case 6:
				metodo = true;
				LlistaEstacionsRecarrega.setEsPropera(30);

				System.out.println(Arrays.toString(listaActual.llistaEstacionsProperes(latitud, longitud)));
				break;
			case 7:
				metodo = true;
				LlistaEstacionsRecarrega.setEsPropera(50);

				System.out.println(Arrays.toString(listaActual.llistaEstacionsProperes(latitud, longitud)));

				break;
			case 8:
				int maxim = 0;
				EstacioRecarregaVE aux = null;
				for (int i = 0; i < listaActual.getNumComproba(); i++) {
					if(listaActual.getLlista()[i].getNumPlaces()>maxim) {
						maxim = listaActual.getLlista()[i].getNumPlaces();
						aux = listaActual.getLlista()[i].copia();
					}
				}
				System.out.print(aux);
				break;
			case 9: // VA BIEN
				System.out.println(listaActual);
				break;
			case 10:
				System.out.println("Introdueix la latitud de la teva posició: ");
				latitud = teclat.nextFloat();
				System.out.println("Introdueix la longitud de la teva posició: ");
				longitud = teclat.nextFloat();
				break;
			case 11:
				sortir = true;
				break;
			default:
				System.out.println("El valor introduit es incorrecte");
			}
		}
		System.out.println("El programa s'ha acabat");

	}

	private static String[] llegirLiniesFitxer(int nLinies) throws FileNotFoundException {
		String[] result;
		if (nLinies < 0)
			nLinies = 0;
		if (nLinies > 418)
			nLinies = 418;
		result = new String[nLinies];
		Scanner f = new Scanner(new File("EstacionsRecarregaReduit.csv"));

		String capcalera = f.nextLine();
		System.out.println("El format de les dades en cada linia es el seguent\n" + capcalera + "\n");
		for (int i = 0; i < nLinies; i++) {
			result[i] = f.nextLine();
		}
		f.close();
		return result;
	}

}