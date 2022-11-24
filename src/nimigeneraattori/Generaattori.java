/**
 * 
 */
package nimigeneraattori;

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
	public static StringBuilder generoiNimi(String[] vokaalit, String[] konsonantit, int pituus) {
		String[] aakkoset = {"a", "u", "e", "i", "o", "y", "b", "c", "d",
							"f", "g", "h", "j", "k", "l", "m", "n", "p", 
							"q", "r", "s", "t", "v", "w", "x", "z"};
		StringBuilder nimi = new StringBuilder("");
		
		
		Random random = new Random();
		nimi.append(aakkoset[random.nextInt(aakkoset.length)]);
		
		for(int i = 0; i < vokaalit.length; i++) {
			if(nimi.toString() == vokaalit[i]) {
				nimi.append(aakkoset[random.nextInt(aakkoset.length)]);
				break;
			}
		}
		
		nimi.append(vokaalit[random.nextInt(vokaalit.length)]);
		
		
		boolean onkoVokaali = false;
		boolean onkoKonsonantti = false;
		int i = 2;
		while(nimi.length() <= pituus) {
			if (onkoVokaali(vokaalit, Character.toString(nimi.charAt(i-2))) == true) {
				if (onkoVokaali(vokaalit, Character.toString(nimi.charAt(i-1))) == true) {
					nimi.append(aakkoset[random.nextInt(aakkoset.length)]);
				}
			}
			
			break;
		}
		
		
		return nimi;
	}
	
	
	/**
	 * Tarkistaa onko aliohjelmalle parametrina tuotu kirjain vokaali
	 * @param vokaalit vokaalien lista
	 * @param kirjain kirjain joka pitää tarkistaa
	 * @return totuusarvo, onko kirjain vokaali vai ei
	 */
	public static boolean onkoVokaali(String[] vokaalit, String kirjain) {
		boolean onkoVokaali = false;
		for(int i = 0; i < vokaalit.length; i++) {
			if(kirjain == vokaalit[i]) {
				onkoVokaali = true;
				break;
			}
		}
		return onkoVokaali;
	}
	
	
	/**
	 * Tarkistaa onko aliohjelmalle parametrina tuotu kirjain konsonantit
	 * @param konsonantit konsonanttien lista
	 * @param kirjain kirjain joka pitää tarkistaa
	 * @return totuusarvo, onko kirjain konsonantti vai ei
	 */
	public static boolean onkoKonsonantti(String[] konsonantit, String kirjain) {
		boolean onkoKonsonantti = false;
		for(int i = 0; i < konsonantit.length; i++) {
			if(kirjain == konsonantit[i]) {
				onkoKonsonantti = true;
				break;
			}
		}
		return onkoKonsonantti;
	}
	
	//Testimuutos

	/**
	 * @param args ei käytössä
	 */
	public static void main(String[] args) {
		String[] vokaalit = {"a", "u", "e", "i", "o", "y"};
		String[] konsonantit = {"b", "c", "d", "f", "g", "h", "j", "k", 
								"l", "m", "n", "p", "q", "r", "s", "t", 
								"v", "w", "x", "z"};
		int pituus = 5;
		
		StringBuilder nimi = new StringBuilder(generoiNimi(vokaalit, konsonantit, pituus));
		System.out.println(nimi);
	}

}
