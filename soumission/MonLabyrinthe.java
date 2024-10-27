package soumission;

import code_squelette.Exterieur;
import code_squelette.Labyrinthe;
import code_squelette.Piece;
import code_squelette.Aventure;

public class MonLabyrinthe implements Labyrinthe {
    // Attribut de la class labyrinthe
    // Tableau de pieces pour initialiser les piece
    // lexterieur pour initualiser le premier element du tableau de pieces
    //listAdjacence qui est une tab dans laquelle chaque indice est lier a une piece
    // le tableau present a cet indice est un tableau de taille 8 qui contient les pieces
    // adjacente a la piece indiquer par lindice, et / ou des null (voir Constructeur)

    private Piece[] pieces;
    private Exterieur lexterieur = Exterieur.getExterieur();
    private Piece[][] listAdjacence;

    // Constructeur de la class Monlabyrinthe
    public MonLabyrinthe(Piece[] pieces) {
        this.pieces = new Piece[pieces.length +1];
        this.pieces[0] = lexterieur;
        for (int i = 1; i < this.pieces.length; i++) {
            this.pieces[i] = pieces[i-1];

        }
        this.listAdjacence = new Piece[this.pieces.length][8];
    }

    // methodes de la class Monlabyrinthe

    // methode retourne le tableau des pieces du labyrinthe
    @Override
    public Piece[] getPieces() {
        return pieces;
    }

    // methode qui retourne le nombre de piece dans le labyrintyhe
    @Override
    public int nombreDePieces() {
        return pieces.length;
    }

    // methode pour verifier si un type piece appartient au labyrinthe
    public boolean pInLab(Piece[] tableau, Piece element) {
        for (int i = 0; i < tableau.length; i++) {
            if (tableau[i] == element) {
                return true; // L'élément est trouvé
            }
        }
        return false; // L'élément n'est pas trouvé
    }

    // methode pour ajouter a un tableau de type Piece , un element Piece
    // retourne le tableau  + un element piece
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

    // mehode qui retourne lindex dune piece dans le tableau de pieces du labyrinthe
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


    // methode pour ajouter une liaison entre lexterieur et une piece lambda...
    public void ajouteEntree(Exterieur out, Piece e) {
        ajouteCorridor(out, e);
    }

    // methode pour ajouter une liaison entre 2 piece quelconque du labyrinthe
    // On veille a ajouter les pieces si elles existe pas

    public void ajouteCorridor(Piece e1, Piece e2) {
        boolean a = pInLab(this.pieces,e1) == true;
        boolean b = pInLab(this.pieces,e2) == true;
        // Si lun de a ou b nest pas dans le labyrinthe
        //ajouter lelement qui nes pas dedans
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
        // on recueille les index des 2 pieces
        int e1dex = findIndex(this.pieces,e1);
        int e2dex = findIndex(this.pieces,e2);
        int pos1 = 0;
        int pos2 = 0;
        // On verifie si la premiere piece na pas atteint son max de liaison
        for (int i = 0; i < listAdjacence[e1dex].length; i++) {
            if (listAdjacence[e1dex][i] == null) {
                pos1 = i;
                break;
            }

        }

        // si ce nombre est atteint alors on envoie un message et on cree pas de liason
        if (pos1 == listAdjacence[e1dex].length  && listAdjacence[e1dex][pos1 -1] != null ) {
            System.out.println("Le nombre de liaison max est atteint deja");
            return;
        }

        //pareil pour la seconde
        for (int j = 0; j < listAdjacence[e2dex].length; j++) {
            if (listAdjacence[e2dex][j] == null) {
                pos2 = j;
                break;
            }

        }

        //pareil pour la seconde
        if (pos2 == listAdjacence[e2dex].length  && listAdjacence[e2dex][pos1 -1] != null ) {
            System.out.println("Le nombre de liaison max est atteint deja");
            return;
        }
        // Si il y a de lespace dans les 2 tab de adjacence on les lie..
        listAdjacence[e1dex][pos1] = e2;
        listAdjacence[e2dex][pos1] = e1;


    }

    // methode pour verifier sil y a une liason deja
    // On verifie juste si lun a appartient a la list dajacence de lautre et vice-versa
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

    // methode pour retourner les pieces lier a une piece du lab
    // On retourne juste la list dadjacence de la piece
    public Piece[] getPiecesConnectees(Piece e) {
        if (pInLab(this.pieces,e) == true) {
            return listAdjacence[findIndex(this.pieces,e)];
        }
        return null;
    }

    // Methode pour trouver une piece par son id
    // tres util pour des manipulation future
    // on reourne la piece trouver
    public Piece findPieceById(int id) {
        for ( int i =0 ; i< this.pieces.length;i++) {
            if (pieces[i].getID() == id) {
                return pieces[i];
            }
        }
        return null;
    }
}
