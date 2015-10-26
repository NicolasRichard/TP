package ca.polymtl.log3430;

import java.io.*;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Properties;
import java.util.TreeSet;

/**
 * Classe représentant une suite numérique et qui permet de sauvegarder celle-ci
 * au sein d'un fichier .properties.
 * La suite est implémentée sous la forme d'une liste chainée.
 *
 * @author Chunxia Zhang
 * @author Nicolas Richard
 * @author Adrien Budet
 */
public class SuiteChainee implements Suite
{
    private File       file;
    private Liste      liste = new ListeChainee();
    private int        maxSize = 0;
    private Calculator operator;
    private String     operatorName = null;

    /**
     * Méthode permettant de construire une suite depuis l'interpréteur de
     * commande. Ne fait qu'appeler le constructeur.
     *
     * @param args       Arguments de la ligne de commande.
     * @throws Exception Si une erreur survient.
     */
    public static void main(String[] args) throws Exception {
        String chemin = args[0];
        String operateur = args[1];
        int val1 = Integer.parseInt(args[2]);
        int val2 = Integer.parseInt(args[3]);
        int taille = Integer.parseInt(args[4]);
        boolean etatVide = Boolean.parseBoolean(args[5]);
        SuiteChainee s = new SuiteChainee(chemin, operateur, val1, val2, taille,
                                          etatVide);
        System.out.print(chemin + " : ");
        for (int i = 0; i < s.getSize(); i++) {
            System.out.print(s.getAt(i));
            if (i != s.getSize() - 1) {
                System.out.print(", ");
            }
        }
    }

    /**
     * Constructeur construisant une suite à partir des informations fournies.
     * Si une suite existe précédemment à l'endroit indiqué par l'argument
     * @link{chemin}, les valeurs de cette dernière ne sont prises en
     * compte que si l'argument @link{etatVide} a pour valeur @code{true}. Dans
     * le cas ou @link{etatVide} a pour valeur @code{false}, les valeurs de
     * l'ancienne suite sont incluses au début de celle-ci et les arguments
     * @link{val1} et @link{val2} sont ajoutés à leur suite. Finalement, les
     * valeurs supplémentaires sont calculées en utilisant l'opérateur de
     * l'ancienne suite jusqu'à ce le nombre d'éléments dans la suite ait
     * atteint la taille définie par @link{taille}. Sauvegarde la suite.
     *
     * @param chemin    Chemin dans l'arborescence de fichier localisant
     * @param operateur Opérateur mathématique permettant de déterminer les
     *                  valeurs supplémentaires. Valeurs acceptées: addition,
     *                  soustraction, multiplication et division.
     * @param val1      Première valeur à ajouter à la suite.
     * @param val2      Seconde valeur à ajouter à la suite.
     * @param taille    Taille initiale de la suite à générer.
     * @param etatVide  Si la suite doit être construite en tenant en compte
     *                  l'ancienne suite, si elle existe. @see{Description de la
     *                  méthode}
     *
     * @throws IllegalArgumentException   Si @link{taille} est inférieure à 2 ou
     *                                    supérieure à 10.
     */
    public SuiteChainee(String chemin, String operateur, int val1, int val2,
                        int taille, boolean etatVide) {
        if (taille < 2 || taille > 10) {
            throw new IllegalArgumentException("La taille est invalide");
        }
        file = new File(chemin);
        operatorName = operateur;
        operator = createOperator(operateur);
        if (!etatVide && file.exists()) {
            loadFile(file);
        }
        add(val1);
        add(val2);
        while (liste.getSize() < taille) {
            int preprevious = getAt(liste.getSize() - 2);
            int previous = getAt(liste.getSize() - 1);
            add(operator.operate(preprevious, previous));
        }
        maxSize = taille;
        saveFile(file);
    }

    /**
     * Ajoute la valeur passée en paramètre à la suite de la dernière valeur
     * actuellement comprise dans la suite.
     *
     * @param element Valeur à ajouter à la fin de la suite.
     */
    public void add(int element) {
        liste.add(element);
    }

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
    public int getAt(int index) {
        return liste.getAt(index);
    }

    /**
     * Indique le nombre d'éléments contenus dans cette suite.
     *
     * @return Le nombre d'éléments au sein de la suite.
     */
    public int getSize() {
        return liste.getSize();
    }

    /**
     * Retire l'élément se situant à la position pointée par l'index spécifié.
     *
     * @param index Position de l'élément à retirer de la suite.
     *
     * @throws java.lang.IndexOutOfBoundsException Si aucun élément n'existe à
     *                                             la position spécifiée.
     */
    public void removeAt(int index) {
        liste.removeAt(index);
    }

    /**
     * Supprime de la liste le premier élément ayant la même valeur que
     * @link{element}.
     *
     * @param element Element dont on doit supprimer la première occurence.
     *
     * @throws java.lang.IllegalArgumentException Si l'élément n'existe pas.
     */
    public void removeItem(int element) {
        liste.removeItem(element);
    }

    /**
     *  Vide la suite de son contenu.
     */
    public void reset() {
        liste.reset();
    }

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
    public void setAt(int value, int index) {
        liste.setAt(value, index);
    }

    /**
     * Vérifie que les éléments de la suite ont tous été construits à l'aide de
     * l'opérateur fourni lors de la construction de cette suite.
     *
     * @return Si la suite est valide considérant l'opérateur fourni.
     */
    public boolean isValide() {
        // Si la taille est inférieure à deux, il pourrait s'agir des valeurs
        // fournie à la construction et qui ne sont pas tenues de suivre
        // l'opérateur.
        boolean isValid = true;
        if (liste.getSize() > 2) {
            for (int i = 2; i < liste.getSize() && isValid; i++) {
            	int v1 = liste.getAt(i - 2);
            	int v2 = liste.getAt(i - 1);
                int value = operator.operate(v1, v2);
                isValid = value == liste.getAt(i);
            }
        } else {
        	return false;
        }
        return isValid;
    }

    /**
     * Factory method pour instantier l'opérateur adéquat.
     *
     * @param operation Le type d'opérateur désiré.
     *
     * @return L'instance de l'opérateur désiré.
     *
     * @throws java.lang.IllegalArgumentException Si l'opération n'est pas supportée.
     */
    private Calculator createOperator(String operation) {
        Calculator returnedValue = null;
        switch (operation) {
            case "addition":
                returnedValue = new Addition();
                break;
            case "soustraction":
                returnedValue = new Soustraction();
                break;
            case "multiplication":
                returnedValue = new Multiplication();
                break;
            case "division":
                returnedValue = new Division();
                break;
            default:
                throw new IllegalArgumentException("Opérateur invalide");
        }
        return returnedValue;
    }

    /**
     * Charge la suite contenue au sein du fichier spécifié par @link{filePath}
     * si ce dernier existe.
     *
     * @param file Chemin menant au fichier .properties à charger.
     */
    private void loadFile(File file) {
        Properties properties = new Properties();
        InputStream stream = null;
        try
        {
            stream = new FileInputStream(file);
            properties.load(stream);
        } catch (IOException e) {
            // Ne rien faire. Agir comme si le fichier n'existait pas.
        } finally {
            try {
                if (stream != null)
                    stream.close();
            } catch (IOException e) {
                // Ne rien faire.
            }
        }
        String content = properties.getProperty("Parametre6", "");
        for (String value : content.split(", "))
        {
            try {
                int castedValue = Integer.parseInt(value);
                add(castedValue);
            } catch (NumberFormatException e) {
                // La valeur est rejetée et ne sera pas ajouté à la liste.
            }
        }
        operatorName = properties.getProperty("Parametre3");
        operator = createOperator(operatorName);
    }

    /**
     * Sauvegarde le fichier à l'endroit désiré.
     *
     * @param file Emplacement du fichier à sauvegarder.
     */
    private void saveFile(File file) {
        // Ordonnancement des propriétés.
        @SuppressWarnings("serial")
		Properties properties = new Properties() {
            @Override
            public synchronized Enumeration<Object> keys() {
                return Collections.enumeration(
                        new TreeSet<Object>(super.keySet()));
            }
        };
        properties.put("Parametre1", Integer.toString(liste.getAt(0)));
        properties.put("Parametre2", Integer.toString(liste.getAt(1)));
        properties.put("Parametre3", operatorName);
        properties.put("Parametre4", Integer.toString(liste.getSize()));
        properties.put("Parametre5", Integer.toString(maxSize));
        String content = "";
        for (int i = 0; i < liste.getSize(); i++) {
            if (i != liste.getSize() - 1) {
                content += Integer.toString(liste.getAt(i)) + ", ";
            } else {
                content += Integer.toString(liste.getAt(i));
            }
        }
        properties.put("Parametre6", content);
        OutputStream stream = null;
        try
        {
            stream = new FileOutputStream(file);
            properties.store(stream, "");
        } catch (IOException e) {
            // Ne rien faire. Agir comme si le fichier n'existait pas.
        } finally {
            try {
                if (stream != null)
                    stream.close();
            } catch (IOException e) {
                // Ne rien faire.
            }
        }
    }
}