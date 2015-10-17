package ca.polymtl.log3430;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Cas de test de type boite noire permettant de tester le constructeur de la
 * classe @{link SuiteChainee} selon le critère AC. 
 * 
 * @author David Kanaa
 * @author Nicolas Richard
 * @author Adrien Budet
 */
@RunWith(Parameterized.class)
public class SuiteChaineeTestAC {
	
    // *************************************************************************
    // Attributs statiques
    // *************************************************************************
	
	private static List<List<Choix>> choix = new ArrayList<List<Choix>>(6);
	private static final File fichier = new File("f_sauvegarde.properties");
	
    // *************************************************************************
    // Attributs
    // *************************************************************************
	
	private String chemin;
	private String operateur;
	private int val1;
	private int val2;
	private int taille;
	private boolean etatVide;
	private boolean estErreur;	
	
    // *************************************************************************
    // Méthodes statiques
    // *************************************************************************
	
	/**
	 * Génère des données de tests. Chaque paire de données correspond à un cas
	 * de test. Les données sont générées à la manière d'un produit cartésien.
	 * 
	 * @return Les données de test.
	 */
	@Parameters(name = "AC{index}: {0},{1},{2},{3},{4}-{5}-{6}")
    public static Collection<Object[]> donnees() {    	
    	ArrayList<Object[]> donnees = new ArrayList<Object[]>();
    	for (Collection<Choix> c : choix) {
    		Iterator<Choix> it = c.iterator();
    		while (it.hasNext()) {
    			Choix element = it.next();
    			
    		}
    	}
    }
    
    /**
     * Génère les différents choix spécifiés par le tableau de spécification de
     * manière à ce que la méthode {@link donnees} ait pour seule responsabilité
     * la combinaison des choix.
     */
    @BeforeClass
    public static void instantierChoix() {
    	Object propC = new Object();
    	Object propV = new Object();
    	Object propId = new Object();
    	Object propDiff = new Object();
    	choix.get(0).add(new Choix(fichier.getName(), Arrays.asList(propC), 
    			                Arrays.asList()));
    	choix.get(0).add(new Choix("", false));
    	choix.get(1).add(new Choix("Addition", Arrays.asList(propV), 
    			                Arrays.asList()));
    	choix.get(1).add(new Choix("Soustraction", Arrays.asList(propV), 
                Arrays.asList()));
    	choix.get(1).add(new Choix("Multiplication", Arrays.asList(propV), 
                Arrays.asList()));
    	choix.get(1).add(new Choix("Division", Arrays.asList(propV), 
                Arrays.asList()));
    	choix.get(1).add(new Choix("", true));
    	choix.get(2).add(new Choix(4, Arrays.asList(propId), Arrays.asList(propV)));
    	choix.get(2).add(new Choix(8, Arrays.asList(propDiff), Arrays.asList(propV)));
    	choix.get(3).add(new Choix(4, Arrays.asList(), Arrays.asList(propId, propV)));
    	choix.get(3).add(new Choix(3, Arrays.asList(), Arrays.asList(propDiff, propV)));
    	choix.get(4).add(new Choix(1, true));
    	choix.get(4).add(new Choix(11, true));
    	choix.get(4).add(new Choix(2, Arrays.asList(), Arrays.asList(propV)));
    	choix.get(4).add(new Choix(7, Arrays.asList(), Arrays.asList(propV)));
    	choix.get(4).add(new Choix(10, Arrays.asList(), Arrays.asList(propV)));
    	choix.get(5).add(new Choix(true, Arrays.asList(), Arrays.asList(propV)));
    	choix.get(5).add(new Choix(true, Arrays.asList(), Arrays.asList(propV, propC)));
    }
    
    private static void combiner(Object[] c, int index) {
    	Collection choixDispos = choix.get(index);
    	Iterator<Choix> it = choixDispos.iterator();
    	while (it.hasNext()) {
    		Choix selection = it.next();
    		if 
    	}
    }
    
    // *************************************************************************
    // Constructeur
    // *************************************************************************
    
    /**
     * @param chemin    Chemin menant vers le fichier
     * @param operateur Opérateur à utiliser
     * @param val1      Première valeur de la suite.
     * @param val2      Seconde valeur de la suite.
     * @param taille    Taille maximale de la suite originale
     * @param etatVide  Si l'on doit charger les anciennes valeurs
     * @param erreur    Si une erreur est attendue.
     */
    public SuiteChaineeTestAC(String chemin, String operateur, int val1, int val2,
            int taille, boolean etatVide, boolean erreur) {
    	this.chemin = chemin;
    	this.operateur = operateur;
    	this.val1 = val1;
    	this.val2=val2;
    	this.taille=taille;
    	this.etatVide = etatVide;
    	this.estErreur = erreur;
    }
    
    // *************************************************************************
    // Préparation des tests
    // *************************************************************************
    
    /**
     * Créer un nouveau fichier contenant une liste valide
     */
    @Before
    public void creerFichier() {
    	new SuiteChainee("liste.properties", "addition", 1, 2, 5, true);
    }
    
    /**
     * Détruit le fichier générer par l'appel au constructeur de la suite.
     */
    @After
    public void detruireFichier() {
    	File f = new File(chemin);
    	if (f.exists()) {
    		f.delete();
    	}
    }
    
    // *************************************************************************
    // Méthodes de test
    // *************************************************************************
    
    /**
     * Vérifie qu'un fichier existe suite à l'exécution du constructeur et qu'il est bien formé"
     */
    @Test
    public void verifierFichier() {
    	try {
    		File f = new File(chemin);
    		InputStream s = null;
    		Properties p = null;;
    		String pOp = operateur;
    		String pVal1 = Integer.toString(val1);
    		String pVal2 = Integer.toString(val2);
    		if (f.exists() && !etatVide) {
        		s = new FileInputStream(f);
        		p = new Properties();
        		p.load(s);
        		pOp = p.getProperty("Parametre3");
        		pVal1 = p.getProperty("Parametre1");
        		pVal2 = p.getProperty("Parametre2");
        		s.close();
    		}
    		p = new Properties();
    		SuiteChainee suite = new SuiteChainee(chemin,operateur,val1,val2,taille,etatVide);
    		s = new FileInputStream(f);
    		p.load(s);
    		s.close();
    		assertEquals(p.getProperty("Parametre1"), pVal1);
    		assertEquals(p.getProperty("Parametre2"), pVal2);
    		assertEquals(p.getProperty("Parametre3"), pOp);
    		assertEquals(p.getProperty("Parametre4"), Integer.toString(suite.getSize()));
    		assertEquals(p.getProperty("Parametre5"), Integer.toString(taille));
    		String[] vals = p.getProperty("Parametre6").split(", ");
    		for (int i = 0; i < vals.length; i++) {
    			assertEquals(vals[i], Integer.toString(suite.getAt(i)));
    		}
    	} catch(Exception e){
    		assertTrue(estErreur);
		}
    }    
    
    /**
     * Vérifie si la suite est correctement bâtie et valide.
     */
    @Test
	public void test() {    	
    	SuiteChainee suite = null;
		try{
			suite = new SuiteChainee(chemin,operateur,val1,val2,taille,etatVide);	
			assertTrue(suite.isValide());
			File f = new File(chemin);
			assertTrue(f.exists() && !f.isDirectory());
		} catch(Exception e){
			assertTrue(e instanceof IllegalArgumentException &&(operateur.equals("dsfsf")||taille < 2 || taille > 10));
		}
	}    
    
    // *************************************************************************
    // Classes imbriquées
    // *************************************************************************
    
    /**
     * Classe représantant un choix au sein du tableau de spécification.
     * Permet de lier des contraintes à un choix.
     * 
     * @author Nicolas Richard
     */
    private static class Choix {
    	
        // *********************************************************************
        // Attributs privés
        // *********************************************************************
    	private Collection<Object> contraintes;
    	private boolean estErreur;
    	private Collection<Object> proprietes;
    	private Object valeur;
    	
        // *********************************************************************
        // Constructeurs
        // *********************************************************************
    	
    	/**
    	 * Construit un choix sans propriété ni contrainte qui peut être une 
    	 * erreur.
    	 * 
    	 * @param v         Valeur du choix
    	 * @param estErreur Si le choix est une erreur ou non
    	 */
    	public Choix(Object v, boolean estErreur) {
    		this.valeur = v;
    		this.estErreur = estErreur;
    	}
    	
    	/**
    	 * Construit un choix avec des propriétés et des contraintes qui n'est 
    	 * pas une erreur.
    	 * 
    	 * @param v Valeur du choix
    	 * @param p Propriétés du choix
    	 * @param c Contraintes du choix
    	 */
    	public Choix(Object v, Collection<Object> p, Collection<Object> c) {
    		this(v, false);
    		this.contraintes = contraintes;
    		this.proprietes = p;
    	}
    	
        // *********************************************************************
        // Méthodes
        // *********************************************************************
    	
    	/**
    	 * Indique si une propriété du choix fourni n'est pas une contrainte de 
    	 * ce choix.
    	 * 
    	 * @param c Choix dont on veut vérifier la compatibilité avec celui-ci.
    	 * @return Si les deux choix peuvent être combinés dans un cas de test.
    	 */
    	public boolean estComptatible(Choix c) {
    		boolean estCompatible = true;
    		for (Object p : c.proprietes) {
    			if (contraintes.contains(p)) {
    				estCompatible = false;
    			}
    		}
    		return estCompatible;
    	}
    	
    	/**
    	 * Indique si le choix est une erreur ou non.
    	 * 
    	 * @return Si le choix est une erreur.
    	 */
    	public boolean estErreur() {
    		return estErreur;
    	}
    	
    	/**
    	 * Indique la valeur de ce choix.
    	 * 
    	 * @return La valeur du choix.
    	 */
    	public Object obtenirValeur() {
    		return valeur;
    	}
    }
}