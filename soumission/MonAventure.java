package soumission;

import code_squelette.Aventure;
import code_squelette.Labyrinthe;
import code_squelette.Piece;
import code_squelette.RencontreType;


public class MonAventure extends Aventure {

    /**
     * Constructeur necessaire.
     * @param c le labyrinthe de l'aventure.
     */
    public MonAventure(Labyrinthe c) {
        super(c);
    }

    /**
     * On va chercher le labyrinthe.
     */
    Labyrinthe labyrintheActif = this.getLabyrinthe();

    @Override
    public boolean estPacifique(){
        for (Piece piece : labyrintheActif.getPieces()){
            if (piece.getRencontreType() == RencontreType.MONSTRE || piece.getRencontreType() == RencontreType.BOSS){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean contientDuTresor() {
        for (Piece piece : labyrintheActif.getPieces()){
            if (piece.getRencontreType() == RencontreType.TRESOR){
                return true;
            }
        }
        return false;
    }

    @Override
    public int getTresorTotal(){
        int compteurTresor = 0;
        for (Piece piece : labyrintheActif.getPieces()){
            if (piece.getRencontreType() == RencontreType.TRESOR){
                compteurTresor++;
            }
        }
        return compteurTresor;
    }

    @Override
    public boolean contientBoss(){
        for (Piece piece : labyrintheActif.getPieces()){
            if (piece.getRencontreType() == RencontreType.BOSS){
                return true;
            }
        }
        return false;
    }

    @Override
    public Piece[] cheminJusquAuBoss() {
        Piece[] cheminBoss = new Piece[labyrintheActif.nombreDePieces()];
        Piece[] piecesLabyrintheActif = labyrintheActif.getPieces();
        Piece[] tabVide = new Piece[0];

        /**
         * Condition de depart.
         */
        if (piecesLabyrintheActif[0] != null){
            cheminBoss[0] = piecesLabyrintheActif[0];
        }
        else{
            return tabVide;
        }

        /**
         * Remplissage du tableau cheminBoss. On initialise une variable incrementente i pour naviguer a travers
         * le labyrinthe.
         */
        int i = 0;
        boolean cheminTermine = false;
        while (true){
            Piece[] piecesConnecteesActive = labyrintheActif.getPiecesConnectees(piecesLabyrintheActif[i]);
            /**
             * On regarde chaque piece connectee.
             */
            for (Piece piece : piecesConnecteesActive){
                /**
                 * Condition pour que la piece soit active et soit ajoutee. Si pas valide, ne sera pas ajoute et ne se
                 * passe rien.
                 */
                if (piece.getID() == piecesLabyrintheActif[i].getID() + 1){
                    if (piece.getRencontreType() == RencontreType.BOSS){
                        cheminBoss[i+1] = piece;
                        cheminTermine = true;
                    }
                    else{
                        cheminBoss[i+1] = piece;
                    }
                    break;
                }
            }
            /**
             * On veut sortir du while loop aussi car le chemin est fini si on a trouve un boss.
             */
            if (cheminTermine){
                break;
            }
            /**
             * On verifie si une piece a ete ajoutee. Si il n'y a en pas eu, alors cela veut
             * dire que la condition (b) ou (c) est satisfaite, donc pas de chemin. Alors on retourne un tab vide.
             */
            if (cheminBoss[i+1] == null){
                return tabVide;
            }
            /**
             * Si aucun de ses deux if n'a fait briser le while, alors nous avons une piece qui fonctionne en i+1 et nous
             * devons continuer a chercher un chemin a partir de cette derniere. Il faut donc simplement incrementer le i.
             */
            i++;
        }
        /**
         * Nous avons donc maintenant un tableau cheminBoss, mais a moins que tous les elements ont ete remplis, il
         * y a necessairement des espaces vides. Suprrimons les en copiant les bons elements dans un autre tableau.
         */
        int compteurPiecePleine = 0;
        for (Piece piece : cheminBoss){
            if (piece != null){
                compteurPiecePleine++;
            }
        }
        /**
         * Creeons le nouveau tableau
         */
        Piece [] tabFinalCheminBoss = new Piece[compteurPiecePleine];
        for (int j = 0; j < compteurPiecePleine; j++){
            tabFinalCheminBoss[j] = cheminBoss[j];
        }
        return tabFinalCheminBoss;
    }
}
