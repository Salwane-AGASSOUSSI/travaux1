package rencontres;

public class Monstre extends Rencontre {
    protected String nomMonstre;
    public Monstre() {
        //constructeur vide
        
    }
    @Override
    public String rencontrer() {
        String retour = "Un " + nomMonstre + " affreux!";
        return retour;
    }
}
