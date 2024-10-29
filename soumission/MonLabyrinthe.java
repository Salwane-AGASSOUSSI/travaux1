package soumission;

import code_s1quelette.Labyrinthe;
import code_squelette.Piece;
import code_squelette.Exterieur;
import code_squelette.RencontreType;

public class MonLabyrinthe implements Labyrinthe { //TODO: extends ? implements?

    int maxPiece = 50;
    Piece[] piecesLabyrinthe = new Piece[maxPiece];

    @Override
    public Piece[] getPieces(){
        return piecesLabyrinthe;
    }

    @Override
    public int nombreDePieces(){

        /**On assume que tout est rempli initialement.
         *
         */
        int nombrePieces = 50;

        for (int i = 0; i < 50; i ++) {
            if (piecesLabyrinthe[i] == null) {
                nombrePieces = i;
                break;
            }
        }
        return nombrePieces;
    }

    @Override
    public void ajouteEntree(Exterieur out, Piece e) {
        if (piecesLabyrinthe[0] == null) {
            out = Exterieur.getExterieur(); //aurait deja du etre instancie dans le main?
            piecesLabyrinthe[0] = out;
        }
        int idPiece = e.getID();
        if (piecesLabyrinthe[idPiece] == null){
            piecesLabyrinthe[idPiece] = e;
        }

    }
}
