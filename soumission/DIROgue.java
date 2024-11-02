package soumission;

import code_squelette.*;

import java.util.Arrays;
import java.util.Scanner;

public class DIROgue {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		MonLabyrinthe labyrintheLive = new MonLabyrinthe();
		Exterieur lExterieur = Exterieur.getExterieur();
		labyrintheLive.piecesLabyrinthe[0] = lExterieur;
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
						} else {
							labyrintheLive.ajouteCorridor(labyrintheLive.piecesLabyrinthe[id1], labyrintheLive.piecesLabyrinthe[id2]);
						}
					}
				}
			}
		}

		genererRapport(aventureLive);

	}

	public static String genererRapport(Aventure a) {
		System.out.println("Rapport:");
		System.out.println("Donjon avec " + a.getLabyrinthe().nombreDePieces() + " pieces:");
		for (Piece piece: a.getLabyrinthe().getPieces()) {
			if (piece != null) {
				Piece[] piecesConnectees = a.getLabyrinthe().getPiecesConnectees(piece);
				System.out.println(piece + " : " + Arrays.toString(piecesConnectees));
			}
		}
		return null;
	}

	public static String genererScenario(Aventure a) {
		// TODO: à remplir!
		return null;
	}

}
