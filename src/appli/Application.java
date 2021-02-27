package appli;

public class Application {
    public static void main(String[] args) {

        Table jeux = new Table();

        while (jeux.verifDefaiteJ1() || jeux.verifVictoireJ1()) {

            jeux.afficherInfoj1();
            jeux.lectureEntrerJ1();
            if(!jeux.verifDefaiteJ1() || jeux.verifVictoireJ1()) {
                jeux.afficherInfoj2();
                jeux.afficherVictoireJ2();
                break;
            }

            jeux.afficherInfoj2();
            jeux.lectureEntrerJ2();
            if(!jeux.verifDefaiteJ2() || jeux.verifVictoireJ2()) {
                jeux.afficherInfoj1();
                jeux.afficherVictoireJ1();
                break;
            }

        }

    }

}