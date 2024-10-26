package soumission;

import labyrinthe.code_squelette.*;

public class DIROgue {

	public static void main(String[] args) {

		// Cette invocation est ici juste pour vous demontrer quelques choses
		// TODO: supprimez l'appel a la methode expliquerQuelquesChoses()
		expliquerQuelquesChoses();

	}

	/*
	 * Cette methode n'est pas necessaire pour le TP, c'est ici juste pour vous
	 * demontrer comment utiliser la classe Exterieur.
	 */
	private static void expliquerQuelquesChoses() {
		// ceci est la seule facon de creer une instance de la classe Exterieur!
		Exterieur lExterieur = Exterieur.getExterieur();
		// l'exterieur contient le type de rencontre RencontreType.RIEN
		System.out.println(lExterieur.getRencontreType() == RencontreType.RIEN);
	}

	public static String genererRapport(Aventure a) {
		// TODO: à remplir!
		return null;
	}

	public static String genererScenario(Aventure a) {
		// TODO: à remplir!
		return null;
	}

}
