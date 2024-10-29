package soumission;

import code_squelette.Labyrinthe;
import code_squelette.Piece;
import code_squelette.Exterieur;

public class MonLabyrinthe implements Labyrinthe { //TODO: extends ? implements?

    int maxPieceLab = 50;
    int maxPieceAdj = 8;
    Piece[] piecesLabyrinthe = new Piece[maxPieceLab];
    Piece[][] listesAdj = new Piece[maxPieceLab][maxPieceAdj];

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
        for (int i = 0; i < 8; i++){
            if (listesAdj[0][i] == null){
                listesAdj[0][i] = e;
                break;
            }
        }
        for (int i = 0; i < 8; i++){
            if (listesAdj[idPiece][i] == null){
                listesAdj[idPiece][i] = out;
                break; //hihi
            }
        }
    }
}
