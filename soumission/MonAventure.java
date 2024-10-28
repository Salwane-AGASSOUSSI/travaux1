package soumission;

import code_squelette.Aventure;
import code_squelette.Labyrinthe;
import code_squelette.Piece;
import code_squelette.RencontreType;

public class MonAventure extends Aventure {
    //Constructeur de MonAventure
    /**
     * Initialize une Aventure avec un Labyrinthe
     *
     * @param c - la carte de l'Aventure
     */
    public MonAventure(Labyrinthe c) {
        super(c);
    }

    /**
     * Nous dit si l'aventure contient des méchants.
     *
     * @return true si et seulement si la carte de l'Aventure ne contient pas de
     *         Pieces avec des RencontreType.MONSTRE ou RencontreType.BOSS.
     */
    @Override
    public boolean estPacifique() {
        Piece[] pieces = this.carte.getPieces(); // Récupère les pièces sous forme de tableau
        int nombreDePieces = this.carte.nombreDePieces(); // Obtient le nombre de pièces

        for (int i = 0; i < nombreDePieces; i++) {
            RencontreType rencontre = pieces[i].getRencontreType(); // Type de rencontre de la pièce actuelle
            if (rencontre == RencontreType.MONSTRE || rencontre == RencontreType.BOSS) {
                return false; // L'aventure n'est pas pacifique
            }
        }
        return true; // Toutes les pièces sont pacifiques
    }

    /**
     * Nous dit si l'aventure est rentable, en d'autre termes si elle contient du
     * tresor du tout.
     *
     * @return true si et seulement si la carte de l'Aventure contient au minimum une
     *         Piece avec RencontreType.TRESOR
     */
    @Override
    public boolean contientDuTresor() {
        Piece[] pieces = this.carte.getPieces(); // Récupère les pièces sous forme de tableau
        int nombreDePieces = this.carte.nombreDePieces(); // Obtient le nombre de pièces

        for (int i = 0; i < nombreDePieces; i++) {
            RencontreType rencontre = pieces[i].getRencontreType(); // Type de rencontre de la pièce actuelle
            if (rencontre == RencontreType.TRESOR) {
                return true; // L'aventure contient au moins un trésor
            }
        }
        return false; // Aucun trésor trouvé dans toutes les pièces
    }

    /**
     * Nous dit combien de RencontreType.TRESOR existent dans l'Aventure.
     *
     * @return le nombre de RencontreType.TRESOR dans l'Aventure.
     */

    @Override
    public int getTresorTotal() {
        int tresorTotal = 0;
        Piece[] pieces = this.carte.getPieces();

        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i] != null && pieces[i].getRencontreType() == RencontreType.TRESOR) {
                tresorTotal++;
            }
        }

        return tresorTotal;
    }

    /**
     * Nous dit si l'Aventure contient un boss (ou plusieurs).
     *
     * @return true si et seulement si l'Aventure contient une Rencontre.BOSS ou plus.
     */
    @Override
    public boolean contientBoss() {
        Piece[] pieces = this.carte.getPieces(); // suppose que getPieces() retourne un tableau de Piece

        for (int i = 0; i < pieces.length; i++) {
            if (pieces[i] != null && pieces[i].getRencontreType() == RencontreType.BOSS) {
                return true; // On a trouvé un boss, on peut retourner true directement
            }
        }

        return false; // Aucun boss trouvé après avoir vérifié toutes les pièces
    }

    /**
     * Une façon de determiner si le donjon est gagnable. Retourne un chemin en partant
     * de l'Exterieur jusqu'au Boss. Nous faisons l'hypothèse qu'un Labyrinthe sera
     * toujours construit de façon à ce qu'en commençant depuis l'Extérieur (ID=0) et en
     * suivant des Pieces avec des ID en ordre croissant, nous devrions trouver le Boss,
     * tomber dans une boucle, ou arreter à une Piece sans sortie.
     *
     * @return - une chaine de Pieces telle que chaine[0].getID()==0,
     *         chaine[i+1].getID()=chaine[i].getID()+1 et
     *         chaine[chaine.length-1].getRencontreType==Rencontre.BOSS; s'il n'est
     *         pas possible de trouver le Boss, retourne un tableau vide.
     */

    @Override
    public Piece[] cheminJusquAuBoss() {
        MonLabyrinthe labyrinthe = (MonLabyrinthe) this.carte;
        Piece[] pieces = this.carte.getPieces();
        Piece tempPiece;
        Piece tempPiecePrecedant;
        Piece[] chemin = new Piece[1];
        chemin[0] = pieces[0];

        for (int i = 1; i < 50; i++) {
            tempPiece = labyrinthe.findPieceById(i);
            tempPiecePrecedant = labyrinthe.findPieceById(i-1);
            // si la piece est dans le labyrinthe et quil est connectet a la piece d'id avant lui
            // alors on lajoute au chemin.
            if (labyrinthe.pInLab(pieces,tempPiece) && labyrinthe.existeCorridorEntre(tempPiece,tempPiecePrecedant)) {

                chemin = labyrinthe.ajouterElement(chemin, tempPiece);
                if (tempPiece.getRencontreType() == RencontreType.BOSS){
                    break;
                }

            }else {
                Piece[] cheminvide = new Piece[chemin.length];
                chemin = cheminvide;
                break;

            }


        }
        return chemin;

    }
}
