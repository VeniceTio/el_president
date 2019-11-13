package Model;

import Uttilities.Observable;

public class Semestre extends Observable implements Time{
    /**
     * Le nombre de semestre en cours dans l'application
     */
    private int _nbrSemestre = 0;
    /**
     * Instance de la classe Semestre
     */
    private static Semestre Instance = null;
    /**
     * Méthode permettant de créer une instance de class Semestre
     */
    private Semestre(){}
    /**
     * Méthode permettant de retourner l'instance de la classe Semestre
     * @return
     */
    public static Semestre getInstance(){
        if(Instance == null){
            Instance = new Semestre();
        }
        return Instance;
    }

    /**
     * Méthode permettant de renvoyer le nombre de semestre en cours
     * @return le nombre de semestre
     */
    public int getSemestre(){return _nbrSemestre;}

    /**
     * Méthode permettant de passer au semestre suivant
     */
    @Override
    public void ClockForvard() {
    _nbrSemestre++;
    notifyObservers();
    }

    /**
     * Méthode permettant d'afficher le nombre de semestre en cours
     * @return la chaîne affichant le nom et la valeur
     */
    public String toString(){return "Semestre n°"+_nbrSemestre;}
}
