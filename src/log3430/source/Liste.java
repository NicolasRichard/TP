package log3430.source;

/**
 * Interface permettant de représenter une liste
 * 
 * @author Nicolas Richard
 * @author Adrien Budet
 * @author David Kanaa
 */
public interface Liste {
	/**
     * Ajoute la valeur passée en paramètre à la suite de la dernière valeur
     * actuellement comprise dans la suite.
     *
     * @param element Valeur à ajouter à la fin de la suite.
     */
    public void add(int element);
    
    /**
     * Obtient la valeur de la suite présente à la position fournie par l'index.
     *
     * @param index Position de la valeur à obtenir.
     *
     * @return Valeur présente à la position voulue.
     *
     * @throws IndexOutOfBoundsException Si aucune valeur n'existe à l'index
     *                                   fourni.
     */
    public int getAt(int index);
    
    /**
     * Indique le nombre d'éléments contenus dans cette suite.
     *
     * @return Le nombre d'éléments au sein de la suite.
     */
    public int getSize();
    
    /**
     * Retire l'élément se situant à la position pointée par l'index spécifié.
     *
     * @param index Position de l'élément à retirer de la suite.
     *
     * @throws java.lang.IndexOutOfBoundsException Si aucun élément n'existe à
     *                                             la position spécifiée.
     */
    public void removeAt(int index);
    
    /**
     * Supprime de la liste le premier élément ayant la même valeur que
     * @link{element}.
     *
     * @param element Element dont on doit supprimer la première occurence.
     *
     * @throws java.lang.IllegalArgumentException Si l'élément n'existe pas.
     */
    public void removeItem(int element);
    
    /**
     *  Vide la suite de son contenu.
     */
    public void reset();
    
    /**
     * Remplace la valeur située à la position indiquée par @link{index} par
     * @link{value}.
     *
     * @param value Nouvelle valeur.
     * @param index Position de la valeur à remplacer.
     *
     * @throws java.lang.IndexOutOfBoundsException Si aucun élément n'existe à
     *                                             la position spécifiée.
     */
    public void setAt(int value, int index);
}
