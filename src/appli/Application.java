package appli;

import java.util.Scanner;

/**
 * L'application regroupe toutes les classes ainsi que les objets pour rendre possible
 * l'utilisation du programme.
 * on peut donc jouer au jeu THE GAME - Le duel.
 *
 * @author Sellam Zakaria
 * @author Magalhaes Fabio
 * @version 2.1 voir Github
 * */
public class Application {
    public static void main(String[] args) {
        //table de jeux
        Table jeux = new Table();
        // premier joueur (NORD)
        Joueur j1 = new Joueur();
        // deuxieme joueur (SUD)
        Joueur j2 = new Joueur();

        while (true) {
            System.out.println(jeux.infoJoueurToString(j1, j2, '1'));
            lectureEntrer(jeux, j1, j2); // Premier joueur joue

            if (jeux.verifVictoire(j1)) {
                System.out.println(jeux.infoJoueurToString(j2, j1, '2'));
                System.out.println(jeux.victoireToString(j1));
                break;
            }

            if (!jeux.verifDefaite(j1, j2)) {
                System.out.println(jeux.infoJoueurToString(j2, j1, '2'));
                lectureEntrer(jeux, j2, j1);

                if(!jeux.verifDefaite(j1, j2)) {
                    System.out.println(jeux.victoireToString(j1));
                } else {
                    System.out.println(jeux.infoJoueurToString(j1, j2, '1'));
                    System.out.println(jeux.victoireToString(j2));
                }
                break;
            }

            System.out.println(jeux.infoJoueurToString(j2, j1, '2'));
            lectureEntrer(jeux, j2, j1); // Deuxième joueur joue

            if (jeux.verifVictoire(j2)) {
                System.out.println(jeux.infoJoueurToString(j1, j2, '2'));
                System.out.println(jeux.victoireToString(j2));
                break;
            }

            if (!jeux.verifDefaite(j2, j1)) {
                System.out.println(jeux.infoJoueurToString(j1, j2, '1'));
                lectureEntrer(jeux, j1, j2);

                if(!jeux.verifDefaite(j1, j2)) {
                    System.out.println(jeux.victoireToString(j2));
                } else {
                    System.out.println(jeux.infoJoueurToString(j2, j1, '2'));
                    System.out.println(jeux.victoireToString(j1));
                }
                break;
            }
        }
    }

    /**
     * Lis les entrées des joueurs et applique différente vérification
     * Récupère le nombre de cartes du joueur dans sa main avant de jouer
     * Si la verification ne passe pas, la table est nettoyé
     * et la saisie est relancé avec "#>"
     * Affiche les cartes jouées et cartes piochées.
     */
    public static void lectureEntrer(Table jeux, Joueur joueur, Joueur joueurAdv)
    {
        Scanner sc = new Scanner(System.in);
        int nbCarte = joueur.nbDeCarteEnMain();

        System.out.print("> ");
        jeux.supEntrees();
        String entreesJoueur = sc.nextLine();
        jeux.decompose(entreesJoueur, joueur);
        if (!jeux.verifSyntaxique(joueur, joueurAdv)) {
            jeux.supEntrees();
            while (!jeux.verifSyntaxique(joueur, joueurAdv)) {
                jeux.supEntrees();
                if (!jeux.verifSyntaxique(joueur, joueurAdv)) {
                    System.out.print("#> ");
                    entreesJoueur = sc.nextLine();
                    jeux.decompose(entreesJoueur, joueur);
                }
            }
        }
        System.out.println(jeux.cartesJoueToString(joueur, nbCarte));
    }
}