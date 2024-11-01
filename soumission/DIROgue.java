package soumission;

import code_squelette.*;

import java.util.Arrays;
import java.util.Scanner;

public class DIROgue {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MonLabyrinthe labyrintheLive = new MonLabyrinthe();
		MonAventure aventureLive = new MonAventure(labyrintheLive);


		boolean lab = true;
		while (lab) {
			/**
			 * Construisons le labyrinthe en deux parties. Nous avons la partie 1 des pieces ici.
			 */
			boolean phasePiece = true;
			while (phasePiece && scanner.hasNextLine()) {
				String ligne = scanner.nextLine();
				/**
				 * Termine si cas 4 deja, sinon passe a phase corridor si cas 3, sinon ajoute la piece.
				 */
				if (ligne.equals("FIN")) {
					lab = false;
					break;
				} else if (ligne.equals("CORRIDORS")) {
					phasePiece = false;
				} else if (ligne.startsWith("piece")) {
					String[] infos = ligne.split(" ");
					/**
					 * On confirme si c'est le bon format!
					 */
					if (infos.length == 3) {
						int id = Integer.parseInt(infos[1]);
						RencontreType rencontre = RencontreType.valueOf(infos[2].toUpperCase());
						Piece pieceNouvelle = new Piece(id, rencontre);
						labyrintheLive.piecesLabyrinthe[id] = pieceNouvelle;
					}
				}
			}
			/**
			 * Confirmer que l'on doit pas deja finir l'execution si jamais l'usage a mis fin trop vite.
			 */
			if (!lab) {
				break;
			}
			/**
			 * On debute la partie 2.
			 */
			boolean phaseCorridors = true;
			while (phaseCorridors && scanner.hasNextLine()) {
				String ligne = scanner.nextLine();

				if (ligne.equals("FIN")) {
					phaseCorridors = false;
					lab = false;
				} else if (ligne.startsWith("corridor")) {
					String[] infos = ligne.split(" ");
					/**
					 * Meme principe que plus haut.
					 */
					if (infos.length == 3) {
						int id1 = Integer.parseInt(infos[1]);
						int id2 = Integer.parseInt(infos[2]);
						if (labyrintheLive.piecesLabyrinthe[id1] == null || labyrintheLive.piecesLabyrinthe[id2] == null){
							System.out.println("L'une des pieces n'existe pas");
							break;
						} else {
							labyrintheLive.ajouteCorridor(labyrintheLive.piecesLabyrinthe[id1], labyrintheLive.piecesLabyrinthe[id2]);
						}
					}
				}
			}
		}




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
		System.out.println("Rapport:");
		System.out.println("Donjon avec " + a.getLabyrinthe().nombreDePieces() + " pieces:");
		for (Piece piece: a.getLabyrinthe().getPieces()){
			Piece [] piecesConnectees = a.getLabyrinthe().getPiecesConnectees(piece);
			System.out.println(piece + " : " + Arrays.toString(piecesConnectees));
		}
		return null;
	}

	public static String genererScenario(Aventure a) {
		// TODO: à remplir!
		return null;
	}

}
