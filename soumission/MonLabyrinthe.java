package soumission;

import code_squelette.Exterieur;
import code_squelette.Labyrinthe;
import code_squelette.Piece;

public class MonLabyrinthe implements Labyrinthe {
    //TODO: extends ? implements?
    private Piece[] pieces;
    private Exterieur lexterieur = Exterieur.getExterieur();
    private Piece[][] listAdjacence;


    public MonLabyrinthe(Piece[] pieces) {
        this.pieces = new Piece[pieces.length +1];
        this.pieces[0] = lexterieur;
        for (int i = 1; i < this.pieces.length; i++) {
            this.pieces[i] = pieces[i-1];
        }
        this.listAdjacence = new Piece[this.pieces.length][8];
    }

    // methodes de la class Monlabyrinthe


    @Override
    public Piece[] getPieces() {
        return pieces;
    }

    @Override
    public int nombreDePieces() {
        return pieces.length;
    }

    // pour verifier si un type piece appartient au labyrinthe
    public boolean pInLab(Piece[] tableau, Piece element) {
        for (int i = 0; i < tableau.length; i++) {
            if (tableau[i] == element) {
                return true; // L'élément est trouvé
            }
        }
        return false; // L'élément n'est pas trouvé
    }

    public Piece[] ajouterElement(Piece[] tableau, Piece element) {
        // Créer un nouveau tableau de taille supérieure
        Piece[] nouveauTableau = new Piece[tableau.length + 1];

        // Copier les éléments existants dans le nouveau tableau
        for (int i = 0; i < tableau.length; i++) {
            nouveauTableau[i] = tableau[i];
        }

        // Ajouter le nouvel élément à la fin
        nouveauTableau[nouveauTableau.length - 1] = element;

        return nouveauTableau; // Retourner le nouveau tableau
    }

    // Pour ajouter des pieces au pieces existante du labyrinthe, pas de retour mais prend
    // un type piece en entrée
    public  void ajouterPiece(Piece element) {

        if (ajouterElement(this.pieces, element).length <= 50 ) {
            this.pieces = ajouterElement(this.pieces, element);
            // On cree un nouveau tab 2d pour copier les element de tab2 et avoir de la place pour la liste de la nouvelle piece
            Piece[][] tableau = new Piece[this.listAdjacence.length+1][8];
            for (int i = 0; i < this.listAdjacence.length; i++) {
                tableau[i] = this.listAdjacence[i];
            }
            this.listAdjacence = tableau;


        } else {
            System.out.println("Nombre Max de pieces atteint deja");

        }

    }

    public int findIndex(Piece[] tableau, Piece element) {
        int index = 0;
        for (int i = 0; i < tableau.length; i++) {
            if (tableau[i] == element) {
                index = i;
                break;
            }
        }
        return index;
    }


    @Override
    public void ajouteEntree(Exterieur out, Piece e) {
        ajouteCorridor(out, e);
    }

    @Override
    public void ajouteCorridor(Piece e1, Piece e2) {
        boolean a = pInLab(this.pieces,e1) == true;
        boolean b = pInLab(this.pieces,e2) == true;
        if (!(a && b)) {
            if (a==false && b==false) {
                ajouterPiece(e1);
                ajouterPiece(e2);
            } else if (a==true && b==false) {
                ajouterPiece(e2);
            } else if (a==false && b==true) {
                ajouterPiece(e1);
            }
        }
        int e1dex = findIndex(this.pieces,e1);
        int e2dex = findIndex(this.pieces,e2);

        for (int i = 0; i < listAdjacence[e1dex].length; i++) {
            if (listAdjacence[e1dex][i] == null) {
                listAdjacence[e1dex][i] = e2;
                break;
            }
            System.out.println("Le nombre de liaison max est atteint deja");
            return;
        }

        for (int i = 0; i < listAdjacence[e2dex].length; i++) {
            if (listAdjacence[e2dex][i] == null) {
                listAdjacence[e2dex][i] = e1;
                break;
            }
            System.out.println("Le nombre de liaison max est atteint deja");
            return;
        }

    }

    @Override
    public boolean existeCorridorEntre(Piece e1, Piece e2) {
        if (pInLab(this.pieces,e1) == true && pInLab(this.pieces,e2) == true) {
            int e1dex = findIndex(this.pieces,e1);
            int e2dex = findIndex(this.pieces,e2);
            if (pInLab(listAdjacence[e1dex],e2) == true && pInLab(listAdjacence[e2dex],e1) == true) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Piece[] getPiecesConnectees(Piece e) {
        if (pInLab(this.pieces,e) == true) {
            return listAdjacence[findIndex(this.pieces,e)];
        }
        return null;
    }
}
