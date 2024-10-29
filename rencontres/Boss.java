package rencontres;

public class Boss extends Gargouille {
    public Boss() {
        super();
    }

    @Override
    public String rencontrer() {
        String retour = "La bataille finale!";
        return retour;
    }
}
