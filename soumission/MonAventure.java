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
}
