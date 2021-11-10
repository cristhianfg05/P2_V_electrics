package aplicacio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import dades.EstacioRecarregaVE;
import dades.LlistaEstacionsRecarrega;


public class Main {

	static Scanner teclat = new Scanner(System.in);

	public static void main(String[] args) throws FileNotFoundException {
		System.out.println("Indica el n�mero de l�nies a llegir del fitxer (m�xim 418)");
		int numLinies = Integer.parseInt(teclat.nextLine());
		String[] dataset = llegirLiniesFitxer(numLinies);
		LlistaEstacionsRecarrega listaActual =  new LlistaEstacionsRecarrega();
		
		for (int i = 0; i < dataset.length; i++) {
			System.out.println("Linia " + (i + 1) + " cont� " + dataset[i]);
			String datasetSplit [] = dataset[i].split(";");
			listaActual.afegirEstacions(new EstacioRecarregaVE(datasetSplit[0], datasetSplit[1], Float.parseFloat(datasetSplit[2].replace(',', '.')), Float.parseFloat(datasetSplit[3].replace(',', '.')), datasetSplit[4], datasetSplit[5], Integer.parseInt(datasetSplit[6])));
		}
		
		System.out.print(listaActual);
		
		

		// Completar el codi a partir d'aqu�

		
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
		System.out.println("El format de les dades en cada l�nia �s el seg�ent\n" + capcalera);
		for (int i = 0; i < nLinies; i++) {
			result[i] = f.nextLine();
		}
		f.close();
		return result;
	}

}