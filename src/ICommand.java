/**
 * Interface permettant d'implémenter le patron Commande. Elle permet d'obtenir
 * un résultat numérique à partir de deux entrées entières.
 *
 * @author Chunxia Zhang
 * @author Nicolas Richard
 * @author Adrien Budet
 */
public interface ICommand {

    /**
     * Effectue un calcul à partir des valeurs fournies.
     *
     * @param val1 Première valeur
     * @param val2 Seconde valeur
     * @return Un nombre calculé à partir des arguments.
     */
    int operate(int val1, int val2);
}
