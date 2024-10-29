package soumission;             // importation de package
import java.util.Scanner;
import code_squelette.Aventure;
import code_squelette.Exterieur;
import code_squelette.Labyrinthe;
import code_squelette.Piece;
import code_squelette.RencontreType;
import rencontres.*;
import java.util.Random;

public class DIROgue {

	public static void main(String[] args) {
		// Initialise un scanner pour lire les entrées utilisateur
		Scanner scanner = new Scanner(System.in);
		// Initialise un tableau de pièces vide
		Piece[] pieces = new Piece[0];

		// Crée un labyrinthe et une aventure
		MonLabyrinthe labyrinthe = new MonLabyrinthe(pieces);
		MonAventure aventure = new MonAventure(labyrinthe);

		// Instruction pour l'utilisateur de créer le labyrinthe
		System.out.println("Entrez les commandes pour créer votre labyrinthe (exemple: piece 1 monstre):");

		// Boucle de saisie des pièces
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine().trim();

			// Condition de fin de saisie des pièces
			if (line.equals("FIN")) {
				System.out.println("Création du labyrinthe terminée.");
				break;
			}

			// Traitement de l'ajout des pièces
			if (line.startsWith("piece")) {
				String[] parts = line.split(" ");

				// Vérifie le format correct de la commande
				if (parts.length == 3) {
					// Conversion de l'ID et du type de rencontre
					int id = Integer.parseInt(parts[1]);
					String rencontreTypeStr = parts[2].toUpperCase();
					RencontreType rencontreType;

					try {
						// Convertit le type de rencontre en enum
						rencontreType = RencontreType.valueOf(rencontreTypeStr);
					} catch (IllegalArgumentException e) {
						// Gestion des erreurs de type de rencontre invalide
						System.out.println("Erreur: Type de rencontre non reconnu.");
						continue;
					}

					// Créer et ajouter une nouvelle pièce au labyrinthe
					Piece piece = new Piece(id,rencontreType);
					labyrinthe.ajouterPiece(piece);

				} else {
					// Message d'erreur pour format incorrect
					System.out.println("Format incorrect. Utilisez : piece <id> <rencontre>");
				}
			}
			// Condition pour passer à la configuration des corridors
			if (line.equals("CORRIDORS")) {
				break;
			}
		}

		// Boucle de configuration des corridors
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine().trim();
			// Condition de fin de saisie des corridors
			if (line.equals("FIN")) {
				break;
			}

			// Traitement de l'ajout des corridors
			if (line.startsWith("corridor")) {
				String[] parts = line.split(" ");
				if (parts.length == 3) {
					// Conversion des ID des pièces
					int id1 = Integer.parseInt(parts[1]);
					int id2 = Integer.parseInt(parts[2]);
					// Vérification de l'existence des pièces avant d'ajouter un corridor
					if (labyrinthe.findPieceById(id1) != null && labyrinthe.findPieceById(id2) != null) {
						labyrinthe.ajouteCorridor(labyrinthe.findPieceById(id1), labyrinthe.findPieceById(id2));
					} else {
						System.out.println("L'une des pieces nexiste pas");
					}
				}
			}
		}

		// Fermeture du scanner
		scanner.close();
		// Génération et affichage du rapport
		String rapport = genererRapport(aventure);
		System.out.println(rapport);
	}

	/*
	 * Méthode de démonstration pour utiliser la classe Exterieur
	 */
	private static void expliquerQuelquesChoses() {
		// Récupération de l'instance unique d'Exterieur
		Exterieur lExterieur = Exterieur.getExterieur();
		// Vérification que l'extérieur a le type de rencontre RIEN
		System.out.println(lExterieur.getRencontreType() == RencontreType.RIEN);
	}

	// Méthode de génération de rapport sur l'aventure
	public static String genererRapport(Aventure a) {
		String texte = "";
		// Récupération du chemin jusqu'au boss
		Piece[] chemin = a.cheminJusquAuBoss();

		// Construction du rapport avec différentes informations
		texte += "Rapport:" + "\n";
		texte += "Donjon avec " + a.getLabyrinthe().nombreDePieces() + " pieces:" + "\n";

		// Conversion du labyrinthe et récupération des pièces
		MonLabyrinthe labyrinthe = (MonLabyrinthe) a.getLabyrinthe();
		Piece[] pieces = labyrinthe.getPieces();

		// Parcours des pièces et de leurs connexions
		for (int i = 0; i < pieces.length; i++) {
			texte += pieces[i]+ ":[";
			Piece[] temp = labyrinthe.getPiecesConnectees(pieces[i]);

			// Ajout des pièces connectées
			for (int j = 0; j < temp.length -1; j++) {
				if (temp[j] == null){
					texte += "";
				}else {
					texte += temp[j];
					if (j != temp.length - 2) {
						texte += ",";
					}
				}
			}

			// Gestion des cas de pièces nulles
			if (temp[temp.length - 1] == null){
				texte += "]" + "\n";
				texte += "" + "\n";
			} else {
				texte += "," + temp[temp.length-1] + "]";
				texte += "" + "\n";
			}
		}

		// Ajout d'informations sur le caractère pacifique et la présence de boss
		if (a.estPacifique()) {
			texte += "Pacifique" + "\n";
		} else {
			texte += "Non pacifique" + "\n";
		}
		if (a.contientBoss()) {
			texte += "Contient un Boss" + "\n";
		} else {
			texte += "Ne Contient pas de boss" + "\n";
		}

		// Ajout du chemin jusqu'au boss
		texte += "Chemin jusqu'au Boss :" + "\n";
		for (int i = 0; i < chemin.length; i++) {
			texte += chemin[i].toString() + "\n";
		}

		return texte;
	}

	// Méthode de génération de scénario
	public static String genererScenario(Aventure a) {
		// TODO: à remplir!
		return null;
	}
}