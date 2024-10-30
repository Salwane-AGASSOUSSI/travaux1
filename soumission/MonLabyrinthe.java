package soumission;

import code_squelette.Labyrinthe;
import code_squelette.Piece;
import code_squelette.Exterieur;

public class MonLabyrinthe implements Labyrinthe {

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
        int compteur = 0;

        for (int i = 0; i < maxPieceLab; i ++) {
            if (piecesLabyrinthe[i] != null) {
                compteur++;
            }
        }
        return compteur;
    }

    @Override
    public void ajouteEntree(Exterieur out, Piece e) {
        if (piecesLabyrinthe[0] == null) {
            piecesLabyrinthe[0] = out;
        }
        int idPiece = e.getID();
        if (piecesLabyrinthe[idPiece] == null){
            piecesLabyrinthe[idPiece] = e;
        }
        for (int i = 0; i < maxPieceAdj; i++){
            if (listesAdj[0][i] == null){
                listesAdj[0][i] = e;
                break;
            }
        }
        for (int i = 0; i < maxPieceAdj; i++){
            if (listesAdj[idPiece][i] == null){
                listesAdj[idPiece][i] = out;
                break;
            }
        }
    }

    @Override
    public void ajouteCorridor(Piece e1, Piece e2){
        int idPiece1 = e1.getID();
        int idPiece2 = e2.getID();
        if (piecesLabyrinthe[idPiece1] == null) {
            piecesLabyrinthe[idPiece1] = e1;
        }
        if (piecesLabyrinthe[idPiece2] == null) {
            piecesLabyrinthe[idPiece2] = e2;
        }
        for (int i = 0; i < maxPieceAdj; i++){
            if (listesAdj[idPiece1][i] == null){
                listesAdj[idPiece1][i] = e2;
                break;
            }
        }
        for (int i = 0; i < maxPieceAdj; i++){
            if (listesAdj[idPiece2][i] == null){
                listesAdj[idPiece2][i] = e1;
                break;
            }
        }
    }

    @Override
    public boolean existeCorridorEntre(Piece e1, Piece e2){
        int idPiece1 = e1.getID();
        int idPiece2 = e2.getID();

        for (int i = 0; i < maxPieceAdj; i++) {
            Piece pieceComparee = listesAdj[idPiece1][i];
            if (pieceComparee != null && pieceComparee.getID() == idPiece2) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Piece[] getPiecesConnectees(Piece e){
        int idPiece = e.getID();
        if (piecesLabyrinthe[idPiece] == null) {
            return null;
        }
        int compteur = 0;
        for (int i = 0; i < maxPieceAdj; i++){
            if (listesAdj[idPiece][i] != null) {
                compteur++;
            }
        }
        Piece[] piecesConnectees = new Piece[compteur];
        int indexCon = 0;
        for (int i = 0; i < maxPieceAdj; i++){
            if (listesAdj[idPiece][i] != null) {
                piecesConnectees[indexCon++] = listesAdj[idPiece][i];
            }
        }
        return piecesConnectees;
    }
}
