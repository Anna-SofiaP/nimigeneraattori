/**
 * 
 */
package nimigeneraattori;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/**
 * @author Ansku ja Nea
 * @version 17.11.22
 *
 */
public class Generaattori {
	
	/**
	 * Generoi keksityn, randomin nimen annetun vokaalilistan, konsonanttilistan ja
	 * nimen pituuden perusteella
	 * @param vokaalit vokaalien lista
	 * @param konsonantit konsonanttien lista
	 * @param pituus nimen haluttu pituus
	 * @return generoitu nimi
	 */
	public static String generoiNimi(ArrayList<String> vokaalit, ArrayList<String> konsonantit, int pituus) {
		ArrayList<String> aakkoset = new ArrayList<String> (Arrays.asList("a", "u", "e", "i", "o", "y", "b", 
				"c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "z"));
		StringBuilder nimi = new StringBuilder("");
		//boolean onkoVokaali = false;
		//boolean onkoKonsonantti = false;
		int kasiteltavaKirjain = 0;
		
		//Valitseen randomilla nimen ensimmäiseksi kirjaimeksi vokaalin tai konsonantin.
		Random random = new Random();
		if (random.nextInt(0, 10) < 5)
			nimi.append(vokaalit.get(random.nextInt(vokaalit.size()-1)));
		else
			nimi.append(konsonantit.get(random.nextInt(konsonantit.size()-1)));
		

		//Jos alkukirjain on vokaali, niin silloin toiseksi kirjaimeksi voi laittaa minkä tahansa kirjaimen.
		if (vokaalit.contains(Character.toString(nimi.charAt(kasiteltavaKirjain))) == true)
			nimi.append(aakkoset.get(random.nextInt(aakkoset.size()-1)));
		else 
			nimi.append(vokaalit.get(random.nextInt(vokaalit.size()-1)));
		

		kasiteltavaKirjain++;
		
		
		while(kasiteltavaKirjain < pituus-1) {
			if (vokaalit.contains(Character.toString(nimi.charAt(kasiteltavaKirjain))) == true) {
				nimi.append(konsonantit.get(random.nextInt(konsonantit.size()-1)));
			}
			if (konsonantit.contains(Character.toString(nimi.charAt(kasiteltavaKirjain))) == true) {
				nimi.append(vokaalit.get(random.nextInt(vokaalit.size()-1)));
			}
			kasiteltavaKirjain++;
		}
		
		String alkukirjain = Character.toString(nimi.charAt(0));
		String generoituNimi = alkukirjain.toUpperCase() + nimi.substring(1, pituus);
		
		return generoituNimi;
	}
	
	
	/**
	 * Kysyy käyttäjältä kuinka pitkän nimen hän haluaa generoida.
	 * @return genroitavan nimen pituus
	 */
	public static int kysyPituus() {
		int pituus = 0;
		System.out.println("Anna numero valilta 4-7:");
		
		BufferedReader lukija = new BufferedReader(new InputStreamReader(System.in));
		try {
			pituus = Integer.parseInt(lukija.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Virhe!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Virhe!");
			e.printStackTrace();
		}
		
		return pituus;
	}
	

	/**
	 * @param args ei kaytossa
	 */
	public static void main(String[] args) {
		ArrayList<String> vokaalit = new ArrayList<String> (Arrays.asList("a", "u", "e", "i", "o", "y"));
		ArrayList<String> konsonantit = new ArrayList<String> (Arrays.asList("b", "c", "d", "f", "g", "h", 
				"j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "z"));
		
		int pituus = kysyPituus();
		
		String nimi = generoiNimi(vokaalit, konsonantit, pituus);
		System.out.println(nimi);
	}

}
