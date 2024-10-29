package rencontres;

public class Tresor extends Rencontre {
    protected String nomButin;
    public Tresor() {
            //Constructeur...
    }

    @Override
    public String rencontrer() {
        String retour = nomButin + " ! Quelle chance!";
        return retour;
    }
}
