package log3430.source;
/**
 * Interface permettant d'impl�menter le patron Commande. Elle permet d'obtenir
 * un r�sultat num�rique � partir de deux entr�es enti�res.
 *
 * @author Chunxia Zhang
 * @author Nicolas Richard
 * @author Adrien Budet
 */
public interface Calculator {

    /**
     * Effectue un calcul � partir des valeurs fournies.
     *
     * @param val1 Premi�re valeur
     * @param val2 Seconde valeur
     * @return Un nombre calcul� � partir des arguments.
     */
    int operate(int val1, int val2);
}

