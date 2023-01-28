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
 * Nimigeneraattoriohjelma
 * 
 * Generoi uusia, keksittyä nimiä sattumanvaraisesti valittujen vokaalien ja konsonanttien avulla.
 * Sisältää kaksi eri generoiNimi-aliohjelmaa (generoiNimi ja generoiNimi2), jotka generoivat nimiä
 * hieman toisistaan eroavien sääntöjen mukaisesti.
 * 
 * kysyPituus-aliohjelma kysyy ensin käyttäjältä, kuinka pitkän nimen hän haluaa ohjelman generoivan.
 * Sen jälkeen ohjelma generoi generoiNimi-aliohjelman mukaisen nimen ja tulostaa sen.
 * Seuraavaksi kysyPituus-aliohjelma kysyy uudestaan käyttäjältä halutun nimen pituutta.
 * Tämän jälkeen ohjelma generoi generoiNimi2-aliohjelman mukaisen nimen ja tulostaa sen.
 * Molempien generoiNimi-aliohjelmien alussa valitaan nimen ensimmäinen ja toinen kirjain erikseen
 * aliohjelman ekaJaTokaKirjain avulla.
 * 
 * @author Anna-Sofia Paavonen ja Nea Aho-Mantila
 * @version 28.1.2023
 *
 */
public class Generaattori {
	
	/**
	 * Generoi keksityn, satunnaisen nimen annetun vokaalilistan, konsonanttilistan ja
	 * nimen pituuden perusteella
	 * 
	 * Tämä aliohjelma generoi nimiä, jossa joka toinen kirjain on konsonantti ja joka toinen
	 * vokaali, poikkeuksena ensimmäinen ja toinen kirjain, jotka voivat molemmat olla esim. vokaaleja.
	 * 
	 * @param vokaalit vokaalien lista
	 * @param konsonantit konsonanttien lista
	 * @param pituus nimen haluttu pituus
	 * @return generoitu nimi
	 */
	public static String generoiNimi(ArrayList<String> vokaalit, ArrayList<String> konsonantit, int pituus) {
		StringBuilder nimi = new StringBuilder("");
		int kasiteltavaKirjain = 0;
		Random random = new Random();
		
		nimi = ekaJaTokaKirjain(vokaalit, konsonantit, nimi);

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
	 * Generoi toisenlaisen keksityn, satunnaisen nimen annetun vokaalilistan, konsonanttilistan ja
	 * nimen pituuden perusteella.
	 * 
	 * Tämä aliohjelma generoi nimiä, jossa voi olla kaksi konsonanttia peräkkäin (muttei kolme!).
	 * 
	 * @param vokaalit vokaalien lista
	 * @param konsonantit konsonanttien lista
	 * @param pituus nimen haluttu pituus
	 * @return generoitu nimi
	 */
	public static String generoiNimi2(ArrayList<String> vokaalit, ArrayList<String> konsonantit, int pituus) {
		ArrayList<String> aakkoset = new ArrayList<String> (Arrays.asList("a", "u", "e", "i", "o", "y", "b", 
				"c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "z"));
		StringBuilder nimi = new StringBuilder("");
		int kasiteltavaKirjain = 0;
		Random random = new Random();
		
		nimi = ekaJaTokaKirjain(vokaalit, konsonantit, nimi);
		
		kasiteltavaKirjain++;
		
		while (kasiteltavaKirjain < pituus-1) {
			if (konsonantit.contains(Character.toString(nimi.charAt(kasiteltavaKirjain))) == true) {
				if (vokaalit.contains(Character.toString(nimi.charAt(kasiteltavaKirjain-1))) == true) {
					nimi.append(aakkoset.get(random.nextInt(aakkoset.size()-1)));
				}
				else nimi.append(vokaalit.get(random.nextInt(vokaalit.size()-1)));
			}
			if (vokaalit.contains(Character.toString(nimi.charAt(kasiteltavaKirjain))) == true) {
				nimi.append(konsonantit.get(random.nextInt(konsonantit.size()-1)));
			}
			kasiteltavaKirjain++;
		}
		
		String alkukirjain = Character.toString(nimi.charAt(0));
		String generoituNimi = alkukirjain.toUpperCase() + nimi.substring(1, pituus);
		
		return generoituNimi;
	}
	
	
	/**
	 * Generoi nimeen ensimmäisen ja toisen kirjaimen.
	 * Jos ensimmäinen kirjain on vokaali, niin silloin toinen kirjain voi olla ihan mitä tahansa.
	 * Jos ensimmäinen kirjain on konsonantti, niin silloin toisen kirjaimen on oltava vokaali.
	 * @param vokaalit
	 * @param konsonantit
	 * @param nimi
	 * @return
	 */
	public static StringBuilder ekaJaTokaKirjain(ArrayList<String> vokaalit, 
														ArrayList<String> konsonantit, StringBuilder nimi) {
		
		ArrayList<String> aakkoset = new ArrayList<String> (Arrays.asList("a", "u", "e", "i", "o", "y", "b", 
				"c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "z"));
		
		//Valitsee sattumanvaraisesti nimen ensimmäiseksi kirjaimeksi vokaalin tai konsonantin.
		Random random = new Random();
		if (random.nextInt(0, 10) < 5)
			nimi.append(vokaalit.get(random.nextInt(vokaalit.size()-1)));
		else
			nimi.append(konsonantit.get(random.nextInt(konsonantit.size()-1)));
		

		//Jos alkukirjain on vokaali, niin silloin toiseksi kirjaimeksi voi laittaa minkä tahansa kirjaimen.
		if (vokaalit.contains(Character.toString(nimi.charAt(0))) == true)
			nimi.append(aakkoset.get(random.nextInt(aakkoset.size()-1)));
		else 
			nimi.append(vokaalit.get(random.nextInt(vokaalit.size()-1)));
		
		return nimi;
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
	 * Pääohjelma
	 * Sisältää vokaalien ja konsonanttien listat.
	 * @param args ei kaytossa
	 */
	public static void main(String[] args) {
		ArrayList<String> vokaalit = new ArrayList<String> (Arrays.asList("a", "u", "e", "i", "o", "y"));
		ArrayList<String> konsonantit = new ArrayList<String> (Arrays.asList("b", "c", "d", "f", "g", "h", 
				"j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "z"));
		
		int pituus = kysyPituus();
		String nimi = generoiNimi(vokaalit, konsonantit, pituus);
		System.out.println(nimi);
		
		int pituus2 = kysyPituus();
		String nimi2 = generoiNimi2(vokaalit, konsonantit, pituus2);
		System.out.println(nimi2);
	}

}
