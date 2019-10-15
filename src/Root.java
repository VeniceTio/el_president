import Control.ElementControl;
import Model.FamilleLevier;
import Model.Indicateur;
import Model.Levier;
import View.PresidentView;
import View.ProvisoryView;

public class Root {
    public Root(){
        ElementControl EC = new ElementControl();
        /** Création Indicateur **/
        Indicateur argent_disponible = EC.createIndicateur("argent_disponible",0);

        /** Création famille de levier **/
        FamilleLevier Central = new FamilleLevier("Central");
        FamilleLevier Immobilier = new FamilleLevier("Immobilier");
        FamilleLevier Formation = new FamilleLevier("Formation");
        FamilleLevier Recherche = new FamilleLevier("Recherche");

        /** Création Levier categorie Central **/

        /** Création Levier categorie Immobilier **/

        /** Création Levier categorie formation **/
        Levier fContractuel = EC.createLevier("fContractuel", 20000000);
        Levier fTitulaire = EC.createLevier("fTitulaire", 18000000);
        Levier fDotRecur = EC.createLevier("fDotRecur", 180000);
        Levier fDotSpe = EC.createLevier("fDotSpe", 180000);
        Levier fPrimes = EC.createLevier("fPrimes", 10000);
        Levier fPartenariats = EC.createLevier("fPartenaria", 200);
        Formation.addLevier(fContractuel);
        Formation.addLevier(fTitulaire);
        Formation.addLevier(fDotRecur);
        Formation.addLevier(fDotSpe);
        Formation.addLevier(fPrimes);
        Formation.addLevier(fPartenariats);

        /** Création Levier categorie recherche **/
        Levier rContractuel = EC.createLevier("rContractuel", 20000000);
        Levier rTitulaire = EC.createLevier("rTitulaire", 18000000);
        Levier rPrimes = EC.createLevier("rPrimes", 20000000);
        Recherche.addLevier(rContractuel);
        Recherche.addLevier(rTitulaire);
        Recherche.addLevier(rPrimes);

        Levier subEtat = EC.createLevier("subEtat",283000000);

        /** Ajout des facteurs de l'argent_disponible **/
        argent_disponible.addFacteur(fContractuel,"-");
        argent_disponible.addFacteur(fTitulaire,"-");
        argent_disponible.addFacteur(fPrimes,"-");

        argent_disponible.addFacteur(rContractuel,"-");
        argent_disponible.addFacteur(rTitulaire,"-");
        argent_disponible.addFacteur(rPrimes,"-");

        argent_disponible.addFacteur(subEtat,"+");

        /** Ajout Famille Levier au ElementControl **/
        EC.addGroupe(Central);
        EC.addGroupe(Immobilier);
        EC.addGroupe(Formation);
        EC.addGroupe(Recherche);

        /** Ajout des listener **/
        fContractuel.addInfluencer(argent_disponible);
        fTitulaire.addInfluencer(argent_disponible);
        fPrimes.addInfluencer(argent_disponible);
        rContractuel.addInfluencer(argent_disponible);
        rTitulaire.addInfluencer(argent_disponible);
        rPrimes.addInfluencer(argent_disponible);

        /** Calcul valeur indicateur**/
        argent_disponible.MAJALLValue();

        /** View **/
        ProvisoryView view = new ProvisoryView(EC);
    }
    public static void main(String[] args){
        new Root();
    }
}
